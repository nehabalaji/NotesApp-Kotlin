package com.example.notesapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.notesapp.data.Notes
import com.example.notesapp.database.NotesRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    var notesRepository = NotesRepository.getRepository(application)!!
    var noteslist: LiveData<PagedList<Notes>>

    init{
        noteslist = notesRepository.getAllNotes()
    }

    fun insertNotes(notes: Notes){
        notesRepository.InsertNotes(notes)
    }

    fun deleteNotes(notes: Notes){
        notesRepository.DeleteNotes(notes)
    }
}