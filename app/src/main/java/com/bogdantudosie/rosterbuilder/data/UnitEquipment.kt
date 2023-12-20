package com.bogdantudosie.rosterbuilder.data

data class UnitEquipment(val id: Int,
                         val parentUnitId: Int,
                         var equipmentList: List<Equipment>)
