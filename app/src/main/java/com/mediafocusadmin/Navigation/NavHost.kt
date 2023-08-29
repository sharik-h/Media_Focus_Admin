package com.mediafocusadmin.Navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mediafocusadmin.Pages.MainPage
import com.mediafocusadmin.Pages.NewUserPage
import com.mediafocusadmin.Pages.ViewAllCollections
import com.mediafocusadmin.Pages.ViewAllExp
import com.mediafocusadmin.Pages.allUsersPage
import com.mediafocusadmin.data.MainViewModel

@Composable
fun NavHost(
    navHostController: NavHostController,
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    androidx.navigation.compose.NavHost(
        navController = navHostController,
        startDestination = Screen.mainPage.route
    ) {
        composable(route = Screen.mainPage.route){
            MainPage(navController = navHostController, viewModel = viewModel)
        }
        composable(route = Screen.newUserPage.route){
            NewUserPage(navController = navHostController, viewModel = viewModel)
        }
        composable(route = Screen.viewAllCollectionPage.route){
            ViewAllCollections(navController = navHostController, viewModel = viewModel)
        }
        composable(route = Screen.viewAllExpPage.route){
            ViewAllExp(navController = navHostController, viewModel = viewModel)
        }
        composable(route = Screen.allUserPage.route){
            allUsersPage(viewModel = viewModel, paddingValues = paddingValues)
        }
    }
}