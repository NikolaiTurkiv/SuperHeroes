package com.test_kode.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "super_hero_table")
data class SuperHero(
    @PrimaryKey
    val id : Int,
    val name: String?,
    val race: String?,
    val fullName: String?,
    val placeOfBirth: String?,
    val publisher: String?,
    val images: String?,
    val intelligence: Int?,
    val strength: Int?,
    val speed: Int?,
    val durability: Int?,
    val power: Int?,
    val combat: Int?,
    val occupation: String?
)