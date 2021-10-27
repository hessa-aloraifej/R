package com.example.room

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var myRV:RecyclerView
    lateinit var list:List<Note>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var note=findViewById<EditText>(R.id.note)
        var addbtn=findViewById<Button>(R.id.button)
        myRV=findViewById(R.id.myRV)
        NoteDatabase.getInstance(applicationContext)

        updateRV(NoteDatabase.getInstance(applicationContext).NoteDao().getAllNote())
        addbtn.setOnClickListener {

            val s = Note(0, note.text.toString())
            note.text.clear()
            

            CoroutineScope(IO).launch {
                NoteDatabase.getInstance(applicationContext).NoteDao().insertNote(s)
            }
            Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_SHORT)
                .show();
            updateRV(NoteDatabase.getInstance(applicationContext).NoteDao().getAllNote())

        }

        CoroutineScope(IO).launch {
            list = NoteDatabase.getInstance(applicationContext).NoteDao().getAllNote()
            updateRV(list)
        }
    }



    fun updateRV(data: List<Note>){
        myRV.adapter = RVAdpter(this,data)
        myRV.layoutManager = LinearLayoutManager(this)
    }





    fun remove(note:Note){
        NoteDatabase.getInstance(applicationContext).NoteDao().delete(note)
        updateRV(NoteDatabase.getInstance(applicationContext).NoteDao().getAllNote())
    }
    fun edit(note:Note){
        NoteDatabase.getInstance(applicationContext).NoteDao().edit(note)
        updateRV(NoteDatabase.getInstance(applicationContext).NoteDao().getAllNote())
    }
    fun customAlert(note:Note){


        val dialogBuilder = AlertDialog.Builder(this)
        val input = EditText(this)
        dialogBuilder.setMessage("Edit Your Note")
            .setPositiveButton("Ok", DialogInterface.OnClickListener {
                    dialog, id ->
                edit(Note(note.id, input.text.toString()))
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id ->dialog.cancel()
            })

        val alert = dialogBuilder.create()

        alert.setTitle("Edit Note")
        alert.setView(input)
        alert.show()
    }

    fun confirmAlert(note:Note){


        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Are You Sure To Delete This Note?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id ->
                remove(note)
            })
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id ->dialog.cancel()
            })

        val alert = dialogBuilder.create()

        alert.setTitle("Confirmation")
        alert.show()
    }
}