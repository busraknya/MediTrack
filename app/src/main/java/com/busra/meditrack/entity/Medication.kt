package com.busra.meditrack.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medications")
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dose: String,
    val timeInMillis: Long,
    val repeatInterval: Long?,
    val isTaken: Boolean = false
) {
}