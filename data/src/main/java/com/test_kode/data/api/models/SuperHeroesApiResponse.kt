package com.test_kode.data.api.models

import com.google.gson.annotations.SerializedName

data class SuperHeroesApiResponse(
    @SerializedName("id")
    val id : Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("powerstats")
    val powerstats: Powerstats? = null,

    @SerializedName("appearance")
    val appearance: Appearance? = null,

    @SerializedName("biography")
    val biography: Biography? = null,

    @SerializedName("work")
    val work: Work? = null,

    @SerializedName("images")
    val images: Images? = null
)


data class Appearance(
    @SerializedName("race")
    val race: String? = null,
)

data class Biography(
    @SerializedName("fullName")
    val fullName: String? = null,

    @SerializedName("placeOfBirth")
    val placeOfBirth: String? = null,

    @SerializedName("publisher")
    val publisher: String? = null,
)

data class Images(
    @SerializedName("lg")
    val lg: String? = null
)

data class Powerstats(
    @SerializedName("intelligence")
    val intelligence: Int? = null,

    @SerializedName("strength")
    val strength: Int? = null,

    @SerializedName("speed")
    val speed: Int? = null,

    @SerializedName("durability")
    val durability: Int? = null,

    @SerializedName("power")
    val power: Int? = null,

    @SerializedName("combat")
    val combat: Int? = null
)

data class Work(
    @SerializedName("occupation")
    val occupation: String? = null,
)