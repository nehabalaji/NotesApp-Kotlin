package com.example.notesapp.ui.add

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.R
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
    }
}