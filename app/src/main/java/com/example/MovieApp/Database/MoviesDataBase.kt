package com.example.MovieApp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.MovieApp.Utils.Converters
import com.example.MovieApp.Dto.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MoviesDataBase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDataBase? = null

        fun getInstance(context: Context): MoviesDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDataBase::class.java,
                    "movies_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
