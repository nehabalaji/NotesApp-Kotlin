package com.example.notesapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Notes(

    @ColumnInfo(name = "ID")
    var id: Int,

    @ColumnInfo(name = "Title")
    var title: String,

    @ColumnInfo(name = "Content")
    var content: String
) : Parcelable