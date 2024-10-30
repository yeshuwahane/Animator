package dev.ashutoshwahane.animator.presentation.animation_screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.HapticFeedbackConstantsCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ElevatedButton() {
    var isPressed by remember { mutableStateOf(false) }

    // Animate shadow and padding
    val shadowSize by animateDpAsState(targetValue = if (isPressed) 0.dp else 6.dp)
    val offsetY by animateDpAsState(targetValue = if (isPressed) 0.dp else 4.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.Center // Centers the button in the screen
    ) {
        /*Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .shadow(shadowSize, RoundedCornerShape(10.dp))
                .offset(y = offsetY)
                .clickable {
                    isPressed = !isPressed
                }
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color(0xFF7DBA50), RoundedCornerShape(10.dp))
                    .background(Color.Black, RoundedCornerShape(10.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pay now",
                    color = Color.White, // Set text color to white for visibility
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }*/

        AnimatedPayNowButton()
    }
}



@Composable
fun AnimatedPayNowButton() {

    val view = LocalView.current

    // State to manage if the button is pressed
    var isPressed by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    // Animate shadow and offset for press and release animation
    val shadowSize by animateDpAsState(targetValue = if (isPressed) 0.dp else 10.dp)
    val offsetY by animateDpAsState(targetValue = if (isPressed) 0.dp else 6.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .shadow(shadowSize, RoundedCornerShape(12.dp))
                .offset(y = offsetY)
                .clickable {
                    // Launch a coroutine to reverse the animation
                    view.performHapticFeedback(HapticFeedbackConstantsCompat.KEYBOARD_TAP)

                    scope.launch {
                        isPressed = false  // Animate press (shadow = 0, offset = 0)
                        delay(150)  // Short delay to mimic the press effect
                        isPressed = true  // Animate release (shadow > 0, offset > 0)
                    }
                }
        ) {
            Box(
                modifier = Modifier
                    .border(3.dp, Color(0xFF7DBA50), RoundedCornerShape(12.dp))
                    .background(Color.Black, RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pay now",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}