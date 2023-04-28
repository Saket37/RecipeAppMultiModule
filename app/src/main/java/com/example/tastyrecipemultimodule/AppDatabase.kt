package com.example.tastyrecipemultimodule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.room.RecipeDAO
import com.example.details_data.room.RecipeDetailsDAO
import com.example.details_domain.model.RecipeDetails
import com.example.domain.model.RecipeResult

@Database(entities = [RecipeResult::class,RecipeDetails::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
                .fallbackToDestructiveMigration().build()
        }
    }

    abstract fun getRecipeDao(): RecipeDAO
    abstract fun getRecipeDetailDao(): RecipeDetailsDAO
}