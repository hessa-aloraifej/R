package com.example.room

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyVM(application: Application): AndroidViewModel(application) {
   var db= NoteDatabase.getInstance(application)
   var update= db.NoteDao().getAllNote()
   fun add(s:Note){
    CoroutineScope(Dispatchers.IO).launch {
         db.NoteDao().insertNote(s)
         update
      }
      //Toast.makeText(application, "data saved successfully! ", Toast.LENGTH_SHORT)
        // .show();
   }

   fun remove(note:Note){
      db.NoteDao().delete(note)
      update

   }
   fun edit(note:Note){
      db.NoteDao().edit(note)
      update

   }





}