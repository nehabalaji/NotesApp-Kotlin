package com.example.notesapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.Notes
import kotlinx.android.synthetic.main.list_item.view.*

class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var titleTV = itemView.title
    private var contentTV = itemView.content

    fun bind(notes: Notes){
        titleTV.text = notes.title
        contentTV.text = notes.content
    }

    companion object{
        fun from(parent: ViewGroup): NotesViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item, parent, false)
            return NotesViewHolder(view)
        }
    }
}