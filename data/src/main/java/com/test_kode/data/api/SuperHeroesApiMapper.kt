package com.test_kode.data.api

import com.test_kode.data.api.models.SuperHeroesApiResponse
import com.test_kode.data.database.models.SuperHero

class SuperHeroesApiMapper {
    fun map(superHeroesApiResponse: SuperHeroesApiResponse) = superHeroesApiResponse.id?.let {
        SuperHero(
            id = superHeroesApiResponse.id,
            name = superHeroesApiResponse.name,
            race = superHeroesApiResponse.appearance?.race,
            fullName = superHeroesApiResponse.biography?.fullName,
            placeOfBirth = superHeroesApiResponse.biography?.placeOfBirth,
            publisher = superHeroesApiResponse.biography?.publisher,
            images = superHeroesApiResponse.images?.lg,
            intelligence = superHeroesApiResponse.powerstats?.intelligence,
            strength = superHeroesApiResponse.powerstats?.strength,
            speed = superHeroesApiResponse.powerstats?.speed,
            durability = superHeroesApiResponse.powerstats?.durability,
            power = superHeroesApiResponse.powerstats?.power,
            combat = superHeroesApiResponse.powerstats?.combat,
            occupation = superHeroesApiResponse.work?.occupation
        )
    }
}