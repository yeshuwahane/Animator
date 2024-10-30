package dev.ashutoshwahane.animator.presentation.nav



data class AnimatorScreen(
    val route:String,
    val title:String
)


val AnimatorScreens = listOf(
    AnimatorScreen(route = "BouncyAnimation", title = "Bouncy Animation"),
    AnimatorScreen(route = "CircularProgressAnimation",title = "Circular Progress Animation"),
    AnimatorScreen(route = "HeartBeatAnimation", title = "HeartBeatAnimation"),
    AnimatorScreen(route = "SliderAnimation", title = "Slider Animation"),
    AnimatorScreen(route = "SwipeSlider", title = "Swipe Slider"),
    AnimatorScreen(route = "ElevatedButton", title = "Elevated Button"),
    AnimatorScreen(route = "AdjacentButtons", title = "Adjacent Buttons"),
    AnimatorScreen(route = "ThreeDButton", title = "ThreeD Button"),
    AnimatorScreen(route = "ShimmerButton", title = "Shimmer Button"),
)