package com.example.notesapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.Notes
import com.example.notesapp.ui.adapter.NotesPagingAdapter
import com.example.notesapp.ui.adapter.NotesViewHolder
import com.example.notesapp.ui.add.AddActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var notes: Notes
    lateinit var mainViewModel: MainViewModel

    private val updateActivityCode : Int = 0
    private val addActivityCode : Int = 1

    private val extraDataNotesTitle : String = "notes_title"
    private val extraDataNotesContent : String = "notes_content"
    private val extraDataNotesId : String = "notes_id"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, addActivityCode)
        }

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val recyclerView = recycler_view

        val notesPagingAdapter = NotesPagingAdapter()
        recyclerView.adapter = notesPagingAdapter

        mainViewModel.noteslist.observe(this, Observer {
            notesPagingAdapter.submitList(it)
        })
    }
}