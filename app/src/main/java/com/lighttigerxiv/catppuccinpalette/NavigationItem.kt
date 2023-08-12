package com.lighttigerxiv.catppuccinpalette

data class NavigationItem(
    val name: String,
    val route: String,
    val activeIcon: Int,
    val inactiveIcon: Int
)

fun getNavigationItems(): List<NavigationItem>{
    return listOf(
        NavigationItem(
            name = "Mocha",
            route = "mocha",
            activeIcon = R.drawable.mocha_filled,
            inactiveIcon = R.drawable.mocha
        ),
        NavigationItem(
            name = "Macchiato",
            route = "macchiato",
            activeIcon = R.drawable.macchiato_filled,
            inactiveIcon = R.drawable.macchiato
        ),
        NavigationItem(
            name = "Frappe",
            route = "frappe",
            activeIcon = R.drawable.frappe_filled,
            inactiveIcon = R.drawable.frappe
        ),
        NavigationItem(
            name = "Latte",
            route = "latte",
            activeIcon = R.drawable.latte_filled,
            inactiveIcon = R.drawable.latte
        )
    )
}