package dev.ashutoshwahane.animator.presentation.animation_screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.HapticFeedbackConstantsCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ThreeDButton() {

    Box(modifier = Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.Center) {
        AnimatedThreeDButton()
    }

}

@Composable
fun AnimatedThreeDButton() {
    val view = LocalView.current

    var isPressed by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Animate button movement and shadow based on press state
    val buttonOffsetY by animateDpAsState(targetValue = if (isPressed) 6.dp else 0.dp)  // Button moves down when pressed
    val bottomShadowOffsetY by animateDpAsState(targetValue = if (isPressed) 10.dp else 4.dp)  // Shadow extends down
    val shadowHeight by animateDpAsState(targetValue = if (isPressed) 2.dp else 6.dp)
    val buttonBackgroundColor by animateColorAsState(
        targetValue = if (isPressed) Color.Gray else Color.White
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
                .offset(y = bottomShadowOffsetY)
                .background(
                    Color.DarkGray,
                    CutCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 12.dp, bottomEnd = 12.dp)
                )
        )

        // Main button container
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(60.dp)
                .offset(y = buttonOffsetY)
                .background(
                    buttonBackgroundColor,
                    shape = CutCornerShape(
                        topStart = 0.dp, topEnd = 0.dp, bottomStart = 6.dp, bottomEnd = 6.dp
                    )  // Slightly different bottom corner cuts for 3D effect
                )
                .border(2.dp, Color.Gray, /*CutCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 12.dp, bottomEnd = 12.dp)*/
                )
                .clickable {
                    view.performHapticFeedback(HapticFeedbackConstantsCompat.KEYBOARD_TAP)

                    isPressed = true
                    scope.launch {
                        delay(150) // Press animation timing
                        isPressed = false
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            // Text inside the button
            Text(
                text = "Pay now",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}





