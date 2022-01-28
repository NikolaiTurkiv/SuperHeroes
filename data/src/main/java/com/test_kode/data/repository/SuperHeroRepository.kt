package com.test_kode.data.repository

import com.test_kode.data.database.models.SuperHero
import kotlinx.coroutines.flow.Flow

interface SuperHeroRepository {
    fun getAllHeroes(): Flow<List<SuperHero>>
    fun insertList(superHeroesList: List<SuperHero>)
    fun getAllStudioHeroes(comixStudio: String): Flow<List<SuperHero>>
    fun getSuperHero(name: String): SuperHero
    fun removeAll()
}