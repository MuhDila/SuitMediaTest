package com.muhdila.suitmediatest.ui.navigation

sealed class Screen(val route: String) {
    object First : Screen("first")
    object Second : Screen("second") {
        fun createRoute(name: String, userId: String): String =
            "second/$name/$userId"
    }
    object Third : Screen("third") {
        fun createRoute(name: String): String =
            "third/$name"
    }
}