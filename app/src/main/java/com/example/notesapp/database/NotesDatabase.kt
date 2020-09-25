package com.example.notesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.data.Notes
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract val notesDao: NotesDao

    companion object{
        val excecutor: ExecutorService = Executors.newSingleThreadExecutor()
    }
}