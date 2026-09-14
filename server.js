const express = require('express');
const http = require('http');
const { Server } = require('socket.io');

const app = express();
const server = http.createServer(app);

// Enable CORS so your Vercel frontend can connect securely
const io = new Server(server, {
    cors: {
        origin: "*",
        methods: ["GET", "POST"]
    }
});

io.on('connection', (socket) => {
    console.log(`Player connected: ${socket.id}`);

    // Room Management
    socket.on('join_room', (roomCode) => {
        socket.join(roomCode);
        socket.to(roomCode).emit('receive_chat', { sys: true, msg: 'A new player joined the match.' });
    });

    // Real-Time Token Physics Sync
    socket.on('move_piece', (data) => {
        socket.to(data.room).emit('receive_move', data);
    });

    // Animated Dice Sync
    socket.on('roll_dice', (data) => {
        socket.to(data.room).emit('receive_dice', data.result);
    });

    // Chat & Floating Emojis
    socket.on('send_chat', (data) => {
        socket.to(data.room).emit('receive_chat', { sys: false, msg: data.message });
    });
    
    socket.on('send_emoji', (data) => {
        socket.to(data.room).emit('receive_emoji', data.emoji);
    });

    socket.on('disconnect', () => {
        console.log(`Player disconnected: ${socket.id}`);
    });
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`SyncBeat Game Server running on port ${PORT}`);
});
