package com.ydvornyk.cafeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ydvornyk.cafeapp.ui.theme.CafeAppTheme
import com.ydvornyk.cafeapp.ui.root.AppMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ScreenContent(navController)
        }
    }
}

@Composable
fun ScreenContent(navController: NavHostController) = CafeAppTheme {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            //topBar = { TopBar() },
            bottomBar = { BottomNavigationBar(navController) }
        ) {
            Route.AppNavigation(navController = navController)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) = BottomNavigation {
    BottomNavigation(
//        backgroundColor = colorResource(id = R.color.colorPrimary),
//        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        AppMenu.items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(id = item.titleId)
                    )
                },
                label = { Text(text = stringResource(item.titleId)) },
//                selectedContentColor = Color.White,
//                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

//@Preview(
//    showBackground = true,
//    showSystemUi = true,
//)
//@Composable
//fun DefaultPreview() = ScreenContent(rememberNavController())