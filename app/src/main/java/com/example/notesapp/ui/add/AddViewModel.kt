package com.example.notesapp.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.data.Notes
import com.example.notesapp.database.NotesRepository

class AddViewModel(application: Application) : AndroidViewModel(application) {

    val notesRepository = NotesRepository.getRepository(application)!!

    fun insertNotes(notes: Notes){
        notesRepository.InsertNotes(notes)
    }

    fun updateNotes(notes: Notes){
        notesRepository.UpdateNotes(notes)
    }
}