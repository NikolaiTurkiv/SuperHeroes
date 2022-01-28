package com.test_kode.data.repository


import com.test_kode.data.database.models.SuperHero
import com.test_kode.data.database.dao.SuperHeroesDao
import kotlinx.coroutines.flow.Flow


class SuperHeroRepositoryImpl(private val superHeroesDao: SuperHeroesDao) : SuperHeroRepository {

    override fun getAllHeroes(): Flow<List<SuperHero>> {
        return superHeroesDao.getAllHeroes()
    }

    override fun insertList(superHeroesList: List<SuperHero>) {
        superHeroesDao.insertList(superHeroesList)
    }

    override fun getAllStudioHeroes(comixStudio: String): Flow<List<SuperHero>> {
        return superHeroesDao.getAllStudioHeroes(comixStudio)
    }

    override fun getSuperHero(name: String): SuperHero {
        return superHeroesDao.getSuperHero(name)
    }

    override fun removeAll() {
        superHeroesDao.removeAll()
    }

}