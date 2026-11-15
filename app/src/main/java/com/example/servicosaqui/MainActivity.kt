package com.example.servicosaqui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.servicosaqui.ui.navigation.AppNavigation
import com.example.servicosaqui.ui.theme.ServicosAquiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ServicosAquiTheme {
                AppNavigation()
            }
        }
    }
}
