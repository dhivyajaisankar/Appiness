package com.example.appiness

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringMap(value: String): Map<String, String> {
        val mapType = object : TypeToken<Map<String, String>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromMap(map: Map<String, String>): String {
        return Gson().toJson(map)
    }


    @TypeConverter
    fun fromProductImage(productImage: ProductImage?): String? {
        return if (productImage == null) null else Gson().toJson(productImage)
    }

    @TypeConverter
    fun toProductImage(value: String?): ProductImage? {
        return if (value == null) null else Gson().fromJson(value, ProductImage::class.java)
    }
}
