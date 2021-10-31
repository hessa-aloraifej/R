package com.example.room

import androidx.lifecycle.LiveData


class NoteRepository(private val noteDao: NoteDao) {

    val getNotes: LiveData<List<Note>> = noteDao.getAllNote()

    suspend fun addNote(note: Note){
        noteDao.insertNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.edit(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.delete(note)
    }

}