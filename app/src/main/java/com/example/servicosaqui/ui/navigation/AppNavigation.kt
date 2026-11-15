package com.example.servicosaqui.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.servicosaqui.ui.screens.AnuncioFormScreen
import com.example.servicosaqui.ui.screens.AnunciosScreen
import com.example.servicosaqui.ui.screens.CadastroScreen
import com.example.servicosaqui.ui.screens.LoginScreen
import com.example.servicosaqui.ui.screens.TelaProvisoria

private data class Aba(
    val rota: String,
    val rotas: List<String>,
    val titulo: String,
    val icone: ImageVector
)

private val abas = listOf(
    Aba("inicio", listOf("inicio"), "Início", Icons.Filled.Home),
    Aba("anuncios", listOf("anuncios", "formulario?id={id}"), "Anúncios", Icons.AutoMirrored.Filled.List),
    Aba("login", listOf("login", "cadastro"), "Conta", Icons.Filled.Person)
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val entradaAtual by navController.currentBackStackEntryAsState()
    val destinoAtual = entradaAtual?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                abas.forEach { aba ->
                    NavigationBarItem(
                        selected = destinoAtual?.hierarchy?.any { it.route in aba.rotas } == true,
                        onClick = {
                            navController.navigate(aba.rota) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(aba.icone, contentDescription = aba.titulo) },
                        label = { Text(aba.titulo) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("inicio") {
                TelaProvisoria("Início")
            }

            composable("anuncios") {
                AnunciosScreen(
                    onNovoClick = {
                        navController.navigate("formulario")
                    },
                    onAnuncioClick = { id ->
                        navController.navigate("formulario?id=$id")
                    }
                )
            }

            composable(
                route = "formulario?id={id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) { entrada ->
                AnuncioFormScreen(
                    id = entrada.arguments?.getString("id"),
                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

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
}
