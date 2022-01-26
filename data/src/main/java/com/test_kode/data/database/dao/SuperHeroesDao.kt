package com.test_kode.data.database.dao

import androidx.room.*
import com.test_kode.data.database.models.SuperHero
import kotlinx.coroutines.flow.Flow

@Dao
interface SuperHeroesDao {
    @Query("SELECT * FROM super_hero_table")
    fun getAllHeroes(): Flow<List<SuperHero>>

    @Query("SELECT * FROM super_hero_table WHERE publisher ==:comixStudio")
    fun  getAllStudioHeroes(comixStudio : String):Flow<List<SuperHero>>

    @Query("DELETE FROM super_hero_table WHERE publisher != :marvelStudio || publisher != :dcStudio")
    fun removeRedundant(marvelStudio : String, dcStudio : String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(superHeroesList : List<SuperHero>)

    @Query("SELECT * FROM super_hero_table WHERE name ==:name")
    fun getSuperHero(name:String): SuperHero

    @Query("DELETE FROM super_hero_table")
    fun removeAll()
}