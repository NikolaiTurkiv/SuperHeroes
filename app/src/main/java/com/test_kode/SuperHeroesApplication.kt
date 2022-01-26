package com.test_kode

import android.app.Application
import com.test_kode.data.database.SuperHeroDatabase
import com.test_kode.data.repository.SuperHeroRepositoryImpl

class SuperHeroesApplication : Application() {
    val database by lazy { SuperHeroDatabase.getDatabase(this) }
    val repositoryImpl by lazy {SuperHeroRepositoryImpl(database.superHeroDao())}
}