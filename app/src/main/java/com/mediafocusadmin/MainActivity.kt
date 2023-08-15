package com.mediafocusadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.mediafocusadmin.Navigation.NavHost
import com.mediafocusadmin.data.MainViewModel
import com.mediafocusadmin.ui.theme.MediaFocusAdminTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            MediaFocusAdminTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                NavHost(navHostController = navHostController, viewModel = viewModel)
            }
        }
    }
}