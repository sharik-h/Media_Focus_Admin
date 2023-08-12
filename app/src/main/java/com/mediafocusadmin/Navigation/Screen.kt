package com.mediafocusadmin.Navigation

sealed class Screen(val route: String) {
    object mainPage: Screen(route = "mainPage")
    object newUserPage: Screen(route = "newUserPage")
    object viewAllExpPage: Screen(route = "viewAllExpPage")
    object viewAllCollectionPage: Screen(route = "viewAllCollectionPage")
}