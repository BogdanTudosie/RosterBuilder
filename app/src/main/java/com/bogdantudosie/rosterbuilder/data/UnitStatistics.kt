package com.bogdantudosie.rosterbuilder.data

data class UnitStatistics(
    val id: Int,
    val parentUnitId: Int, // unique id associated with the parent unit
    val movement: Int,
    val weaponSkill: Int,
    val ballistics: Int,
    val strength: Int,
    val toughness: Int,
    val initiative: Int,
    val wounds: Int,
    val leadership: Int
)
