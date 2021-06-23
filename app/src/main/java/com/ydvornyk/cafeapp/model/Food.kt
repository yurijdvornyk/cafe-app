package com.ydvornyk.cafeapp.model

data class FoodCategory(val id: String, val title: String, val items: List<FoodItem>)

data class FoodItem(val id: String, val title: String, val imagePath: String)