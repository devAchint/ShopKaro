package com.example.shopkaro.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
import com.example.shopkaro.data.models.CartModel
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatOrderDate(timestamp: Long): String {
    val date = Date(timestamp)
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return dateFormat.format(date)
}

fun List<CartModel>.totalCartPrice(): Double {
    var total = 0.0
    forEach {
        total += it.productQuantity * it.productPrice
    }
    return BigDecimal(total).setScale(2, RoundingMode.HALF_UP).toDouble()
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTrans(): EnterTransition {
    return fadeIn(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(300, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTrans(): ExitTransition {
    return fadeOut(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(300, easing = EaseOut),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTrans(): EnterTransition {
    return fadeIn(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(300, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.End
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTrans(): ExitTransition {
    return fadeOut(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(300, easing = EaseOut),
        towards = AnimatedContentTransitionScope.SlideDirection.End
    )
}