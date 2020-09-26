package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.data.Notes
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract val notesDao: NotesDao

    companion object{
        val excecutor: ExecutorService = Executors.newSingleThreadExecutor()

        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase{
            synchronized(this){
                var instance: NotesDatabase? = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, "NotesDatabase")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}