package com.example.room

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyVM(application: Application): AndroidViewModel(application) {
   var db= NoteDatabase.getInstance(application)
    private val repository: NoteRepository
    private val notes: LiveData<List<Note>>

    init {
        val noteDao = NoteDatabase.getInstance(application).NoteDao()
        repository = NoteRepository(noteDao)
        notes = repository.getNotes
    }

    fun getNotes(): LiveData<List<Note>>{
        return notes
    }

    fun add(s:Note) {
       //
       db.NoteDao().insertNote(s)
   //}

      //Toast.makeText(application, "data saved successfully! ", Toast.LENGTH_SHORT)
        // .show();
   }

   fun remove(note:Note){
      db.NoteDao().delete(note)


   }
   fun edit(note:Note){
      db.NoteDao().edit(note)


   }





}