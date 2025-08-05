package com.success.kotlin_roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

    //Table Creating
@Entity(tableName = "Item")
data class MenuItem (
    @PrimaryKey (autoGenerate = true)  val id: Int = 0,
    @ColumnInfo (name = "code") val code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "saleprice") val saleprice : Double,
    @ColumnInfo(name = "cost") val cost : Double,
    @ColumnInfo(name = "inactive") val inactive : Boolean
)