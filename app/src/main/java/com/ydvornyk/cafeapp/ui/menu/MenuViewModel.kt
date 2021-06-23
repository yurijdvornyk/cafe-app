package com.ydvornyk.cafeapp.ui.menu

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydvornyk.cafeapp.Utils
import com.ydvornyk.cafeapp.model.FoodCategory
import org.json.JSONArray

class MenuViewModel : ViewModel() {
    private val menuData = MutableLiveData<List<FoodCategory>>()

    val menu: LiveData<List<FoodCategory>> = menuData

    fun loadMenu(context: Context) {
        val jsonString = Utils.readAssetJson("menu.json", context)
        val array = JSONArray(jsonString)
        for (i in 0..array.length()) {
            print(array.get(i))
        }
        menuData.postValue(null)
    }
}