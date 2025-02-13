package com.example.ecommerceapp.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class CategoriesModel(
    val id: Int = 0,
    val title: String = "",
    val picUrl: String = ""
)
