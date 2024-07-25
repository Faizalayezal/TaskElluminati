package com.example.faizaltaskelluminati.LocalData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Items::class], version = 1)
abstract class AppDatabase:RoomDatabase(){
    abstract fun itemDao(): ItemDao
}