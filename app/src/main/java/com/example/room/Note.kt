package com.example.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Note")
data class Note (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0, // this is how can include id if needed
    @ColumnInfo(name = "Notes") val note: String

)