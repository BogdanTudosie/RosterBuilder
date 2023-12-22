package com.bogdantudosie.rosterbuilder.data

data class GameUnit(val id: Int = 0,
                    val name: String? = null,
                    val type: String? = null,
                    val isSpecific: Boolean = false,
                    var pointCost: Int? = 0)
