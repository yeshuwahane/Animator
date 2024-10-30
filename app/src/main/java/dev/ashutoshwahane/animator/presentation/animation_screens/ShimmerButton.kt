package dev.ashutoshwahane.animator.presentation.animation_screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.HapticFeedbackConstantsCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ShimmerButton() {

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), contentAlignment = Alignment.Center){

        ShimmerCrouchingButton()

    }


}


@Composable
fun ShimmerCrouchingButton() {
    val view = LocalView.current


    var isPressed by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Animate shimmer and crouching effect
    val buttonOffsetY by animateDpAsState(targetValue = if (isPressed) 6.dp else 0.dp)  // Crouch effect
    val shadowHeight by animateDpAsState(targetValue = if (isPressed) 2.dp else 6.dp)  // Adjust shadow

    // Shimmer animation using infinite transition
    val infiniteTransition = rememberInfiniteTransition()
    val shimmerOffsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Shimmer brush with linear gradient
    val shimmerBrush = Brush.linearGradient(
        colors = listOf(Color.Yellow, Color.Red, Color.Yellow),
        start = Offset(shimmerOffsetX, -10f),
        end = Offset(shimmerOffsetX + 300f, 0f)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Bottom shadow (shown behind the button)
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(shadowHeight)
                .offset(y = buttonOffsetY + 4.dp)  // Slight shadow offset
                .background(
                    Color.DarkGray,
                    shape = RoundedCornerShape(12.dp)  // Corner shape for shadow
                )
        )

        // Main button container with shimmer effect
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(60.dp)
                .offset(y = buttonOffsetY)
                .background(
                    brush = shimmerBrush, // Shimmer brush
                    shape = RoundedCornerShape(12.dp)  // Button shape
                )
                .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                .clickable {
                    view.performHapticFeedback(HapticFeedbackConstantsCompat.KEYBOARD_TAP)

                    isPressed = true
                    scope.launch {
                        delay(150) // Press animation delay
                        isPressed = false
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            // Text inside the button
            Text(
                text = "Play now →",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}






