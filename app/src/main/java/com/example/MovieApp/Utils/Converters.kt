package com.example.MovieApp.Utils
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toList(data: String): List<Int> {
        if (data.isEmpty()) return emptyList()
        return data.split(",").map { it.toInt() }
    }
}
