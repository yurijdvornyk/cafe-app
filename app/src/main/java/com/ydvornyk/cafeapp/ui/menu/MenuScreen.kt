package com.ydvornyk.cafeapp.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.ydvornyk.cafeapp.model.FoodCategory
import com.ydvornyk.cafeapp.model.FoodItem
import com.ydvornyk.cafeapp.ui.UiUtils
import com.google.accompanist.coil.rememberCoilPainter

val viewModel = MenuViewModel()

@Composable
fun MenuScreen() = Scaffold {
    val categories: List<FoodCategory>? by viewModel.menu.observeAsState()
    viewModel.loadMenu(LocalContext.current)
    categories?.let {
        MenuList(it)
    } ?: UiUtils.Loader()
}

@Composable
private fun MenuList(categories: List<FoodCategory>) = LazyColumn {
    categories.forEach {
        item {
            MenuCategoryRow(category = it)
        }
    }
}

@Composable
private fun MenuCategoryRow(category: FoodCategory) = Column {
    Text(text = category.title)
    LazyRow {
        category.items.forEach {
            item {
                MenuItem(it)
            }
        }
    }
}

@Composable
private fun MenuItem(item: FoodItem) = Card {
    Column {
        Image(
            painter = rememberCoilPainter(
                request = item.imagePath,
            ),
            contentDescription = item.title
        )
        Text(item.title)
    }
}

@Preview(showSystemUi = true)
@Composable
fun MenuScreenPreview() = MenuScreen()