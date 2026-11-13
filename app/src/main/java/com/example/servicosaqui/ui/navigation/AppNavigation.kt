package com.example.servicosaqui.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.servicosaqui.ui.screens.LoginScreen
import com.example.servicosaqui.ui.screens.CadastroScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onCadastrarClick = {
                    navController.navigate("cadastro")
                }
            )
        }

        composable("cadastro") {
            CadastroScreen(
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}