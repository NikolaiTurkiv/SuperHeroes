package com.test_kode.data.repository


import com.test_kode.data.database.models.SuperHero
import com.test_kode.data.database.dao.SuperHeroesDao
import kotlinx.coroutines.flow.Flow


class SuperHeroRepositoryImpl(private val superHeroesDao: SuperHeroesDao) : SuperHeroRepository {

    //    val allHeroes : Flow<List<SuperHero>> = superHeroesDao.getAllHeroes()
//
//    suspend fun getAllStudioHeroes(comixStudio:String) : Flow<List<SuperHero>>{
//       return superHeroesDao.getAllStudioHeroes(comixStudio)
//    }
//    suspend fun removeRedundant(marvelStudio:String,dcStudio:String) : Flow<List<SuperHero>>{
//        return superHeroesDao.removeRedundant(marvelStudio,dcStudio)
//    }
//    val removeAll = superHeroesDao.removeAll()

    override fun getAllHeroes(): Flow<List<SuperHero>> {
        return superHeroesDao.getAllHeroes()
    }

    override suspend fun insert(superHeroesList: List<SuperHero>) {
        superHeroesDao.insert(superHeroesList)
    }

    override fun getAllStudioHeroes(comixStudio: String): Flow<List<SuperHero>> {
        return superHeroesDao.getAllStudioHeroes(comixStudio)
    }

    override suspend fun removeRedundant(
        marvelStudio: String,
        dcStudio: String
    ) {
        superHeroesDao.removeRedundant(marvelStudio, dcStudio)
    }

    override fun getSuperHero(name: String): SuperHero {
        return superHeroesDao.getSuperHero(name)
    }

    override fun removeAll() {
        superHeroesDao.removeAll()
    }

}