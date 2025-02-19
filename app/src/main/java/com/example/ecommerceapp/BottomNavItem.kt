package com.example.ecommerceapp

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val title : String,
    @DrawableRes val icon : Int
)
val bottomNavItems =listOf(
    BottomNavItem(
        title = "Explore",
        icon = R.drawable.btn_1
    ),
    BottomNavItem(
        title = "Cart",
        icon = R.drawable.btn_2
    ),
    BottomNavItem(
        title = "Favorite",
        icon = R.drawable.btn_3
    ),
    BottomNavItem(
        title = "Profile",
        icon = R.drawable.btn_4
    )
    ,
    BottomNavItem(
        title = "Profile",
        icon = R.drawable.btn_5
    )
)
