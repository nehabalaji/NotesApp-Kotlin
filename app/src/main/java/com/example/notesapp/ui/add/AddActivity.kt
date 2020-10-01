package com.example.notesapp.ui.add

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.R
import com.example.notesapp.data.Notes
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var addViewModel: AddViewModel

    private val updateActivityCode : Int = 0
    private val addActivityCode : Int = 1

    private val extraDataNotesTitle : String = "notes_title"
    private val extraDataNotesContent : String = "notes_content"
    private val extraDataNotesId : String = "notes_id"

    private lateinit var title: String
    private lateinit var content: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        var titleET = editTextTitle
        var contentET = editTextMessage
        val button = submit

        addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)

        val extras: Bundle? = intent.extras
        if (extras!=null){
            title = extras.getString(extraDataNotesTitle, " ")
            if (title.isNotEmpty()){
                titleET.setText(title)
            }
            content = extras.getString(extraDataNotesContent, " ")
            if (content.isNotEmpty()){
                contentET.setText(content)
            }

            button.setText(getString(R.string.update))
        }

        button.setOnClickListener {
            val newNotesTitle: String = titleET.text.toString()
            val newNotesContent: String = contentET.text.toString()

            if(extras!=null && extras.containsKey(extraDataNotesId)){
                val notesId: Long = extras.getLong(extraDataNotesId)
                val notes = Notes(newNotesTitle, newNotesContent, notesId)
                addViewModel.updateNotes(notes)
                finish()
            }else{

                if (newNotesTitle.isEmpty() && newNotesContent.isEmpty()){
                    Toast.makeText(this, "Fill the values", Toast.LENGTH_SHORT)
                }else{
                    val notes = Notes(newNotesTitle, newNotesContent)
                    addViewModel.insertNotes(notes)
                    finish()
                }
            }
        }
    }
}