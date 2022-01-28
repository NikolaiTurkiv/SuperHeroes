package com.test_kode.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test_kode.data.database.dao.SuperHeroesDao
import com.test_kode.data.database.models.SuperHero

@Database(entities = [SuperHero::class], version = 2, exportSchema = false)
abstract class SuperHeroDatabase : RoomDatabase() {
    abstract fun superHeroDao(): SuperHeroesDao

    companion object {
        @Volatile
        private var INSTANCE: SuperHeroDatabase? = null

        fun getDatabase(context: Context): SuperHeroDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuperHeroDatabase::class.java,
                    "super_heroes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}