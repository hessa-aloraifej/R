package com.example.room

import androidx.room.*


@Dao
interface NoteDao {

    @Query("SELECT * FROM Note  ORDER BY id ASC")
    fun getAllNote(): List<Note>

    @Delete
    fun delete(note:Note)

    @Update
    fun edit(note:Note)

    @Insert
    fun insertNote(note: Note)



}