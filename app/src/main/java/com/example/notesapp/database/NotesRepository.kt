package com.example.notesapp.database

import android.app.Application
import com.example.notesapp.data.Notes
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NotesRepository(application: Application) {
    private val notesDao: NotesDao
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val notesDatabase = NotesDatabase.getInstance(application)
        notesDao = notesDatabase.notesDao
    }

    companion object{
        private var notesRepository: NotesRepository? = null
        fun getRepository(application: Application): NotesRepository? {
            if (notesRepository !=null){
                synchronized(NotesRepository::class.java){
                    if (notesRepository !=null){
                        notesRepository =
                            NotesRepository(
                                application
                            )
                    }
                }
            }
            return notesRepository
        }
    }

    fun InsertNotes(notes: Notes){
        executor.execute {
            notesDao.InsertNotes(notes)
        }
    }

    fun DeleteNotes(notes: Notes){
        executor.execute {
            notesDao.DeleteNotes(notes)
        }
    }

    fun UpdateNotes(notes: Notes){
        executor.execute {
            notesDao.UpdateNotes(notes)
        }
    }

    fun getAllNotes(){
        executor.execute {
            notesDao.getAllNotes()
        }
    }
}