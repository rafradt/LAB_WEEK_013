package com.example.test_lab_week_12.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test_lab_week_12.model.Movie
import com.example.test_lab_week_12.database.MovieDao

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie-db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
