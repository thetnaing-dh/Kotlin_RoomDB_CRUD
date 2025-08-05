package com.success.kotlin_roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: MenuItem)

    @Update
    suspend fun update(item: MenuItem)

    @Delete
    suspend fun delete(item: MenuItem)

    @Query("SELECT * FROM Item ORDER BY code ASC")
    fun getItems(): LiveData<List<MenuItem>>

    @Query("SELECT COUNT(*) FROM Item WHERE name = :name")
    suspend fun checkDuplicateName(name: String): Int

    @Query("SELECT COUNT(*) FROM Item WHERE name = :name AND id != :id")
    suspend fun checkDuplicateUpdate(name: String,id: Int): Int
}