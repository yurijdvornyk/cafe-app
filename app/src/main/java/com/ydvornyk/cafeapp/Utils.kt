package com.ydvornyk.cafeapp

import android.content.Context
import java.io.IOException

object Utils {
    fun readAssetJson(filename: String, context: Context): String? {
        return try {
            val inputStream = context.assets.open(filename)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}