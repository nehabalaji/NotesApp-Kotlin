package com.example.notesapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Notes(

    @ColumnInfo(name = "Title")
    var title: String,

    @ColumnInfo(name = "Content")
    var content: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Long = 0L
) : Parcelable