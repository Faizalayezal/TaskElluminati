package com.example.faizaltaskelluminati.LocalData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM Items")
    fun getAllItems(): Flow<List<Items>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Items)


    @Update
    suspend fun updateItem(item: Items)

    @Delete
    suspend fun deleteItem(item: Items)
}