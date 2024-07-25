package com.example.faizaltaskelluminati.LocalData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Items(

    val apartmentSize:String?,
    val totalAmount:String?,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
)

