package com.mediafocusadmin.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mediafocusadmin.Pages.MainPage
import com.mediafocusadmin.Pages.NewUserPage
import com.mediafocusadmin.Pages.ViewAllCollections
import com.mediafocusadmin.Pages.ViewAllExp

@Composable
fun NavHost(navHostController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navHostController,
        startDestination = Screen.mainPage.route
    ) {
        composable(route = Screen.mainPage.route){
            MainPage(navController = navHostController)
        }
        composable(route = Screen.newUserPage.route){
            NewUserPage(navController = navHostController)
        }
        composable(route = Screen.viewAllCollectionPage.route){
            ViewAllCollections(navController = navHostController)
        }
        composable(route = Screen.viewAllExpPage.route){
            ViewAllExp(navController = navHostController)
        }
    }
}