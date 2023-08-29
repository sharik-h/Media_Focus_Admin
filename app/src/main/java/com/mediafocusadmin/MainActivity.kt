package com.mediafocusadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.mediafocusadmin.Navigation.NavHost
import com.mediafocusadmin.Navigation.Screen
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
                Scaffold( bottomBar = {
                    bottomBar(onclick = {
                        if (it == "Home"){
                            navHostController.navigate(Screen.mainPage.route){
                                popUpTo(Screen.mainPage.route) { inclusive = true }
                            }
                        }else{
                            navHostController.navigate(Screen.allUserPage.route){
                                popUpTo(Screen.mainPage.route)
                            }
                        }
                    })
                }) {
                    NavHost(navHostController = navHostController, viewModel = viewModel, paddingValues = it)
                }
            }
        }
    }
}

@Composable
fun bottomBar(onclick: (String) -> Unit) {

    val menuItems = listOf(Pair(Icons.Default.Home, "Home"), Pair(Icons.Default.AccountCircle, "Users"))

    BottomAppBar {
        menuItems.forEach {
            Spacer(modifier = Modifier.weight(0.1f))
            bottomBarItem(icon = it.first, name = it.second, onclick = { onclick(it) })
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}

@Composable
fun bottomBarItem(icon: ImageVector, name: String, onclick: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { onclick(name) }) {
        Image(imageVector = icon, contentDescription = null)
        Text(text = name, fontFamily = FontFamily(Font(R.font.quicksand)))
    }
}