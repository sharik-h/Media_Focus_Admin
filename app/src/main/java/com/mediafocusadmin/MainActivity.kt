package com.mediafocusadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mediafocusadmin.Navigation.NavHost
import com.mediafocusadmin.ui.theme.MediaFocusAdminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            MediaFocusAdminTheme {
                NavHost(navHostController = navHostController)
            }
        }
    }
}