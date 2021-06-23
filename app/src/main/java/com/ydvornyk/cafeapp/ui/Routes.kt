package com.ydvornyk.cafeapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ydvornyk.cafeapp.ui.menu.MenuScreen
import com.ydvornyk.cafeapp.ui.myorders.MyOrdersScreen
import com.ydvornyk.cafeapp.ui.profile.ProfileScreen

sealed class Route(val route: String, val screen: @Composable () -> Unit) {
    object MyOrders : Route("/myOrders", { MyOrdersScreen() })
    object Menu : Route("/menu", { MenuScreen() })
    object Profile : Route("/profile", { ProfileScreen() })

    companion object {

        @Composable
        fun AppNavigation(navController: NavHostController) {
            val routes: List<Route> = listOf(MyOrders, Menu, Profile)
            val default: Route = Menu

            NavHost(navController, startDestination = default.route) {
                routes.forEach { route ->
                    composable(route.route) {
                        route.screen
                    }
                }
            }
        }
    }
}