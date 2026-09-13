const express = require('express');
const http = require('http');
const WebSocket = require('ws');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 8080;

// Serve static frontend files
app.use(express.static(path.join(__dirname, 'public')));

const server = http.createServer(app);
const wss = new WebSocket.Server({ server });

const rooms = {};

wss.on('connection', (ws) => {
    let boundRoom = null;

    ws.on('message', (raw) => {
        let msg;
        try { msg = JSON.parse(raw); } catch (e) { return; }

        if (msg.type === 'PING') {
            ws.send(JSON.stringify({ type: 'PONG' }));
            return;
        }

        // --- Room Creation & Hosting ---
        if (msg.type === 'HOST_ROOM' || msg.type === 'CREATE_ROOM') {
            boundRoom = msg.code;
            ws.clientName = msg.name || 'Host';
            ws.accountId = msg.accountId;
            ws.role = 'HOST';

            if (!rooms[boundRoom]) {
                rooms[boundRoom] = { host: ws, peers: [], currentMedia: null };
            } else {
                rooms[boundRoom].host = ws;
            }

            ws.send(JSON.stringify({ type: 'ROOM_HOSTED_SUCCESS', code: boundRoom }));
            ws.send(JSON.stringify({ type: 'ROOM_CREATED', code: boundRoom }));
            broadcastPeers(boundRoom);
            return;
        }

        // --- Joining Rooms ---
        if (msg.type === 'JOIN_ROOM') {
            const roomCode = msg.code;
            if (!rooms[roomCode] || !rooms[roomCode].host) {
                ws.send(JSON.stringify({ type: 'ROOM_NOT_FOUND' }));
                return;
            }

            boundRoom = roomCode;
            ws.clientName = msg.name || 'Member';
            ws.accountId = msg.accountId;
            ws.role = 'MEMBER';

            rooms[roomCode].peers = rooms[roomCode].peers.filter(p => p.accountId !== ws.accountId);
            rooms[roomCode].peers.push(ws);

            ws.send(JSON.stringify({ type: 'ROOM_JOIN_SUCCESS', code: roomCode }));
            ws.send(JSON.stringify({ type: 'ROOM_JOINED', code: roomCode }));

            if (rooms[roomCode].currentMedia) {
                ws.send(JSON.stringify(rooms[roomCode].currentMedia));
            }
            broadcastPeers(roomCode);
            return;
        }

        // --- Media Control, Scrubbing & Synchronization ---
        if ([
            'PLAY_IFRAME', 'PLAY_COUNTDOWN', 'ANNOUNCE_FILE', 
            'PLAY_YOUTUBE', 'LOAD_VIDEO', 'PLAY_PLAYLIST', 
            'MEDIA_SYNC', 'SYNC_TIME', 'SEEK_TIME', 'CONTROL'
        ].includes(msg.type)) {
            if (!boundRoom || !rooms[boundRoom]) return;
            if (['PLAY_IFRAME', 'PLAY_YOUTUBE', 'LOAD_VIDEO', 'ANNOUNCE_FILE'].includes(msg.type)) {
                rooms[boundRoom].currentMedia = msg;
            }
            broadcastToRoom(boundRoom, msg, msg.type === 'CONTROL' || msg.type === 'SEEK_TIME' ? null : ws);
            return;
        }

        // --- Real-time Chat & Reactions ---
        if (['CHAT', 'CHAT_MESSAGE', 'REACTION', 'ANIMATED_BLAST'].includes(msg.type)) {
            if (!boundRoom || !rooms[boundRoom]) return;
            broadcastToRoom(boundRoom, msg, ws);
            return;
        }

        // --- Room Teardown ---
        if (msg.type === 'CLOSE_ROOM' || msg.type === 'ROOM_CLOSED') {
            if (boundRoom && rooms[boundRoom] && rooms[boundRoom].host === ws) {
                broadcastToRoom(boundRoom, { type: 'ROOM_CLOSED' });
                delete rooms[boundRoom];
                boundRoom = null;
            }
        }
    });

    ws.on('close', () => {
        if (!boundRoom || !rooms[boundRoom]) return;
        if (rooms[boundRoom].host === ws) {
            broadcastToRoom(boundRoom, { type: 'ROOM_CLOSED' });
            delete rooms[boundRoom];
        } else {
            rooms[boundRoom].peers = rooms[boundRoom].peers.filter(p => p !== ws);
            broadcastPeers(boundRoom);
        }
    });
});

function broadcastToRoom(roomCode, data, excludeWs = null) {
    const r = rooms[roomCode];
    if (!r) return;
    const all = [r.host, ...r.peers].filter(Boolean);
    all.forEach(client => {
        if (client !== excludeWs && client.readyState === WebSocket.OPEN) {
            client.send(JSON.stringify(data));
        }
    });
}

function broadcastPeers(roomCode) {
    const r = rooms[roomCode];
    if (!r) return;
    const list = [];
    if (r.host) list.push({ name: r.host.clientName, accountId: r.host.accountId, role: 'HOST' });
    r.peers.forEach(p => list.push({ name: p.clientName, accountId: p.accountId, role: 'MEMBER' }));
    broadcastToRoom(roomCode, { type: 'PEER_LIST', peers: list, count: list.length });
}

server.listen(PORT, () => {
    console.log(`SyncBeat Server listening on port ${PORT}`);
});
