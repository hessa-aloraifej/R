package com.example.room

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val vm by lazy { ViewModelProvider(this).get(MyVM::class.java) }
    lateinit var myRV:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var note=findViewById<EditText>(R.id.note)
        var addbtn=findViewById<Button>(R.id.button)
        myRV=findViewById(R.id.myRV)

        vm.getNotes().observe(this, {
                notes ->  myRV.adapter = RVAdpter(this,notes)
            myRV.layoutManager = LinearLayoutManager(this)

        })
        //updateRV(vm.db.NoteDao().getAllNote())

        addbtn.setOnClickListener {

            val s = Note(0, note.text.toString())
            vm.add(s)
           // updateRV(vm.db.NoteDao().getAllNote())
            note.text.clear()


        }

    //    CoroutineScope(Dispatchers.IO).launch {
        //
        //                   list = vm.db.NoteDao().getAllNote()
        //                   updateRV(list)
        //               }
    }

 //   fun updateRV(data: List<Note>){
    //        myRV.adapter = RVAdpter(this,data)
    //        myRV.layoutManager = LinearLayoutManager(this)
    //    }

    fun customAlert(note:Note){


        val dialogBuilder = AlertDialog.Builder(this)
        val input = EditText(this)
        dialogBuilder.setMessage("Edit Your Note")
            .setPositiveButton("Ok", DialogInterface.OnClickListener {
                    dialog, id ->
                vm.edit(Note(note.id, input.text.toString()))
               // updateRV(vm.db.NoteDao().getAllNote())

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
                vm.remove(note)
               // updateRV(vm.db.NoteDao().getAllNote())
            })
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id ->dialog.cancel()
            })

        val alert = dialogBuilder.create()

        alert.setTitle("Confirmation")
        alert.show()
    }
}