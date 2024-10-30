package dev.ashutoshwahane.animator.presentation.animation_screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.HapticFeedbackConstantsCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AdjacentButtons() {

    Box(modifier = Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.Center){

        AdjacentButtonsCompose()
    }

}

@Composable
fun AdjacentButtonsCompose() {

    val hapticFeedback = LocalHapticFeedback.current
    val view = LocalView.current

    // States to manage if the left or right button is pressed
    var isButtonPressed by remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()

    // Animating shadow and offset for both buttons
    val buttonShadowSize by animateDpAsState(targetValue = if (isButtonPressed) 4.dp else 10.dp)
    val buttonOffsetY by animateDpAsState(targetValue = if (isButtonPressed) 4.dp else 10.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        // Container with border surrounding the buttons
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f) // Make the buttons container 70% of the screen width
                .padding(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Ensure the buttons take up the full width of the parent container
                    .height(80.dp),
                horizontalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                // Left Button (with 3D effect)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .shadow(buttonShadowSize, RoundedCornerShape(12.dp), clip = false)
                        .offset(x = 2.dp, y = buttonOffsetY) // Creating the 3D bottom-right effect
                        .background(Color(0xFFEEEEEE), RoundedCornerShape(12.dp)) // Bottom-right side
                        .padding(5.dp)
                        .clickable {
                            view.performHapticFeedback(HapticFeedbackConstantsCompat.KEYBOARD_TAP)
                            scope.launch {
                                isButtonPressed = false
                                delay(150)
                                isButtonPressed = true
                            }
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .offset(x = -2.dp, y = -2.dp) // Real button layer with 3D effect
                            .shadow(2.dp, RoundedCornerShape(12.dp))
                            .background(Color.White, RoundedCornerShape(12.dp))
                            .fillMaxWidth()
                            .padding(16.dp)
                            ,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Button",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }

                /*Spacer(modifier = Modifier.width(8.dp)) // Spacing between buttons

                // Right Button (with 3D effect)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .shadow(rightShadowSize, RoundedCornerShape(12.dp), clip = false)
                        .offset(x = 2.dp, y = rightOffsetY) // Creating the 3D bottom-right effect
                        .background(Color(0xffa6a2a2), RoundedCornerShape(12.dp)) // Bottom-right side
                        .padding(5.dp)
                        .clickable {
                            scope.launch {
                                isRightPressed = false
                                delay(150)
                                isRightPressed = true
                            }
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .offset(x = -2.dp, y = -2.dp) // Real button layer with 3D effect
                            .shadow(2.dp, RoundedCornerShape(12.dp))
                            .background(Color.Black, RoundedCornerShape(12.dp))
                            .fillMaxWidth()
                            .padding(16.dp)
                            ,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Button",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }*/
            }
        }
    }
}
