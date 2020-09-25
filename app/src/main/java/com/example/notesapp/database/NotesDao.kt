package com.example.notesapp.database

import androidx.paging.DataSource
import androidx.room.*
import com.example.notesapp.data.Notes


@Dao
interface NotesDao {

    @Insert
    fun InsertNotes(notes: Notes)

    @Update
    fun UpdateNotes(notes: Notes)

    @Delete
    fun DeleteNotes(notes: Notes)

    @Query("SELECT * FROM Notes")
    fun getAllNotes(): DataSource.Factory<Int, Notes>
}