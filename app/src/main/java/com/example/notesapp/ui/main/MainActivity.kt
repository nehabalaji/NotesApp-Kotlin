package com.example.notesapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.Notes
import com.example.notesapp.ui.adapter.NotesPagingAdapter
import com.example.notesapp.ui.adapter.NotesViewHolder
import com.example.notesapp.ui.add.AddActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
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

        val clickListener: NotesPagingAdapter.ClickListener = object : NotesPagingAdapter.ClickListener{
            override fun onItemClick(position: Int, view: View) {
                val currentNotes: Notes? = notesPagingAdapter.getNotesAtPosition(position)
                if (currentNotes!=null){
                    launchUpdateActivity(currentNotes)
                }
            }

        }

        val constraintLayout = constraint_layout
        val snackbar = Snackbar.make(constraintLayout, "Notes Deleted", BaseTransientBottomBar.LENGTH_LONG)
            .setAction("UNDO"){
                mainViewModel.insertNotes(notes)
            }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position: Int = viewHolder.adapterPosition
                notes = notesPagingAdapter.getNotesAtPosition(position)!!
                mainViewModel.deleteNotes(notes)
                snackbar.show()
            }

        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
        notesPagingAdapter.setItemClickListener(clickListener)
    }

    fun launchUpdateActivity(notes: Notes){
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra(extraDataNotesId, notes.id)
        intent.putExtra(extraDataNotesTitle, notes.title)
        intent.putExtra(extraDataNotesContent, notes.content)
        startActivityForResult(intent, updateActivityCode)
    }
}