package com.example.shopkaro.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.shopkaro.screens.profile.ProfileScreen
import com.example.shopkaro.screens.profile.ProfileViewModel

fun NavGraphBuilder.profileNavGraph(modifier: Modifier) {
    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreens.ProfileScreen.route,
    ) {
        composable(ProfileScreens.ProfileScreen.route) {
            val profileViewModel: ProfileViewModel = hiltViewModel()
            val profileUiState = profileViewModel.profileUiState.collectAsState()
            ProfileScreen(profileUiState.value, modifier) {
                profileViewModel.signOut()
            }
        }
    }
}

sealed class ProfileScreens(val route: String) {
    object ProfileScreen : ProfileScreens("profile")
    object SettingsScreen : ProfileScreens("settings")
}
