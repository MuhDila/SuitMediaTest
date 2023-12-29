package com.muhdila.suitmediatest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muhdila.suitmediatest.ui.navigation.Screen
import com.muhdila.suitmediatest.ui.screen.first.FirstScreen
import com.muhdila.suitmediatest.ui.screen.second.SecondScreen
import com.muhdila.suitmediatest.ui.screen.third.ThirdScreen
import com.muhdila.suitmediatest.ui.screen.third.UsersViewModel

@Composable
fun SuitMediaTestApp(
    navController: NavHostController = rememberNavController(),
) {
    val usersViewModel = remember { UsersViewModel() }

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = Screen.First.route
        ) {
            composable(Screen.First.route) {
                FirstScreen(navController = navController)
            }
            composable(Screen.Second.route + "/{name}/{userId}") { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val userId = backStackEntry.arguments?.getString("userId") ?: "0"
                SecondScreen(navController = navController, name = name, userId = userId)
            }
            composable(Screen.Third.route + "/{name}") { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                ThirdScreen(navController = navController, viewModel = usersViewModel, name = name)
            }
        }
    }
}