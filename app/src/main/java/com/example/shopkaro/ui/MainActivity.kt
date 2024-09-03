package com.example.shopkaro.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.shopkaro.navigation.graphs.RootNavGraph
import com.example.shopkaro.ui.theme.ShopKaroTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopKaroTheme(darkTheme = false) {
                val currentUser = firebaseAuth.currentUser
                val navController = rememberNavController()
                RootNavGraph(navController = navController, currentUser)
            }
        }
    }
}

