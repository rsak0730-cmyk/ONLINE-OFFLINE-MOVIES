package com.syncbeat.app.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncbeat.app.ui.theme.AppThemeColors
import com.syncbeat.app.ui.theme.LocalAppTheme

@Composable
fun Modifier.neuRaised(
    shape: Shape = RoundedCornerShape(20.dp),
    elevation: Dp = 8.dp
): Modifier {
    val theme = LocalAppTheme.current
    return if (theme.isNeumorphic) {
        this
            .shadow(
                elevation = elevation,
                shape = shape,
                ambientColor = theme.darkShadow.copy(alpha = 0.45f),
                spotColor = theme.darkShadow.copy(alpha = 0.55f),
                clip = false
            )
            .shadow(
                elevation = (elevation.value * 0.6f).dp,
                shape = shape,
                ambientColor = theme.lightShadow.copy(alpha = 0.9f),
                spotColor = theme.lightShadow.copy(alpha = 0.7f),
                clip = false
            )
    } else {
        this.shadow(
            elevation = (elevation.value * 0.4f).dp,
            shape = shape,
            ambientColor = Color.Black.copy(alpha = 0.25f),
            spotColor = Color.Black.copy(alpha = 0.3f),
            clip = false
        )
    }
}

@Composable
fun NeuCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 20.dp,
    elevation: Dp = 10.dp,
    inverted: Boolean = false,
    onClick: (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable () -> Unit
) {
    val theme = LocalAppTheme.current
    val shape = RoundedCornerShape(cornerRadius)
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val isInset = inverted || pressed

    val animElev by animateDpAsState(
        targetValue = if (isInset) 2.dp else elevation,
        animationSpec = tween(120),
        label = "elev"
    )

    Box(
        modifier = modifier
            .then(
                if (!isInset) Modifier.neuRaised(shape, animElev)
                else Modifier
            )
            .clip(shape)
            .background(
                if (isInset) {
                    // Inset look: slightly darker surface + inner border
                    if (theme.isDark) theme.background else theme.background.copy(alpha = 0.7f)
                } else {
                    theme.surfaceElevated
                }
            )
            .then(
                when {
                    isInset && theme.isNeumorphic ->
                        Modifier.border(1.5.dp, theme.darkShadow.copy(alpha = 0.35f), shape)
                    !theme.isNeumorphic ->
                        Modifier.border(1.dp, theme.border, shape)
                    else -> Modifier
                }
            )
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interaction,
                        indication = null,
                        onClick = onClick
                    )
                } else Modifier
            )
            .padding(contentPadding)
    ) {
        content()
    }
}

@Composable
fun NeuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    primary: Boolean = true,
    enabled: Boolean = true,
    fullWidth: Boolean = false
) {
    val theme = LocalAppTheme.current
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val shape = RoundedCornerShape(16.dp)

    val elev by animateDpAsState(
        targetValue = if (pressed) 2.dp else 8.dp,
        animationSpec = tween(100),
        label = "btnElev"
    )

    val bgBrush = when {
        !enabled -> Brush.linearGradient(
            listOf(theme.muted.copy(alpha = 0.25f), theme.muted.copy(alpha = 0.25f))
        )
        primary -> Brush.linearGradient(
            colors = listOf(
                theme.primary,
                theme.primary.copy(red = (theme.primary.red * 0.85f).coerceIn(0f, 1f),
                    green = (theme.primary.green * 0.85f).coerceIn(0f, 1f),
                    blue = (theme.primary.blue * 0.9f).coerceIn(0f, 1f))
            )
        )
        else -> Brush.linearGradient(listOf(theme.surfaceElevated, theme.surfaceElevated))
    }
    val fg = when {
        !enabled -> theme.muted
        primary -> theme.onPrimary
        else -> theme.onSurface
    }

    Box(
        modifier = modifier
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier)
            .then(
                if (!primary || theme.isNeumorphic) {
                    if (primary) {
                        Modifier.shadow(
                            elevation = elev,
                            shape = shape,
                            ambientColor = theme.primary.copy(alpha = 0.35f),
                            spotColor = theme.primary.copy(alpha = 0.45f),
                            clip = false
                        )
                    } else {
                        Modifier.neuRaised(shape, elev)
                    }
                } else {
                    Modifier.shadow(elev, shape, clip = false)
                }
            )
            .clip(shape)
            .background(bgBrush)
            .then(
                if (!primary && !theme.isNeumorphic) {
                    Modifier.border(1.5.dp, theme.border, shape)
                } else Modifier
            )
            .clickable(
                enabled = enabled,
                interactionSource = interaction,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 22.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = fg,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Text(
                text = text,
                color = fg,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                maxLines = 1
            )
        }
    }
}

@Composable
fun NeuIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    size: Dp = 52.dp,
    iconSize: Dp = 24.dp,
    primary: Boolean = false
) {
    val theme = LocalAppTheme.current
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val shape = CircleShape
    val elev by animateDpAsState(
        targetValue = if (pressed) 2.dp else 8.dp,
        animationSpec = tween(100),
        label = "iconElev"
    )

    Box(
        modifier = modifier
            .size(size)
            .then(
                if (primary) {
                    Modifier.shadow(
                        elevation = elev,
                        shape = shape,
                        ambientColor = theme.primary.copy(alpha = 0.4f),
                        spotColor = theme.primary.copy(alpha = 0.5f),
                        clip = false
                    )
                } else {
                    Modifier.neuRaised(shape, elev)
                }
            )
            .clip(shape)
            .background(if (primary) theme.primary else theme.surfaceElevated)
            .then(
                if (!theme.isNeumorphic && !primary) {
                    Modifier.border(1.dp, theme.border, shape)
                } else Modifier
            )
            .clickable(
                interactionSource = interaction,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = if (primary) theme.onPrimary else theme.onSurface,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Composable
fun NeuSectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null
) {
    val theme = LocalAppTheme.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = theme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    color = theme.muted,
                    fontSize = 13.sp
                )
            }
        }
        action?.invoke()
    }
}

@Composable
fun ThemePreviewSwatch(
    theme: AppThemeColors,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val current = LocalAppTheme.current
    val shape = RoundedCornerShape(16.dp)

    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(68.dp)
                .then(
                    if (selected) Modifier.neuRaised(shape, 4.dp)
                    else Modifier.neuRaised(shape, 8.dp)
                )
                .clip(shape)
                .background(theme.background)
                .border(
                    width = if (selected) 2.5.dp else 0.dp,
                    color = if (selected) current.primary else Color.Transparent,
                    shape = shape
                )
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(theme.secondary)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .fillMaxWidth()
                    .height(16.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(theme.primary)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = theme.name,
            color = if (selected) current.primary else current.onBackground,
            fontSize = 11.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(76.dp)
        )
        if (theme.id == "neu_light") {
            Text(
                text = "MAIN",
                color = current.primary,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun NeuEmptyState(
    icon: ImageVector,
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {
    val theme = LocalAppTheme.current
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .neuRaised(CircleShape, 12.dp)
                .clip(CircleShape)
                .background(theme.surfaceElevated),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = theme.muted,
                modifier = Modifier.size(44.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = title,
            color = theme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message,
            color = theme.muted,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
    }
}
