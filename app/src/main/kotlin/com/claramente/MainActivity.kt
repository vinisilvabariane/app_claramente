package com.claramente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.claramente.core.designsystem.theme.ClaramenteTheme
import com.claramente.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    private val container get() = (application as ClaramenteApplication).container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClaramenteTheme {
                AppNavigation(container = container)
            }
        }
    }
}
