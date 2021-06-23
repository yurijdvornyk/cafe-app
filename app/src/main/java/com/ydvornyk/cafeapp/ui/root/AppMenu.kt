package com.ydvornyk.cafeapp.ui.root

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FoodBank
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.RestaurantMenu
import androidx.compose.ui.graphics.vector.ImageVector
import com.ydvornyk.cafeapp.R
import com.ydvornyk.cafeapp.ui.Route

sealed class AppMenu(@StringRes val titleId: Int, val icon: ImageVector, val route: String) {
    object MyOrders : AppMenu(R.string.myOrders, Icons.Rounded.FoodBank, Route.MyOrders.route)
    object Menu : AppMenu(R.string.menu, Icons.Rounded.RestaurantMenu, Route.Menu.route)
    object Profile : AppMenu(R.string.profile, Icons.Rounded.Menu, Route.Profile.route)

    companion object {
        val items = listOf(MyOrders, Menu, Profile)
    }
}