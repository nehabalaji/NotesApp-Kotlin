package com.example.notesapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.notesapp.data.Notes

class NotesPagingAdapter: PagedListAdapter<Notes, NotesViewHolder>(DIFF_CALLBACK) {

    private lateinit var clickListener: ClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNotes: Notes? = getItem(position)
        if (currentNotes!=null){
            holder.bind(currentNotes)
            holder.itemView.setOnClickListener {
                clickListener.onItemClick(position, it)
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Notes>(){
            override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                return oldItem==newItem
            }

        }
    }

    fun setItemClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }

    public interface ClickListener{
        fun onItemClick(position: Int, view: View)
    }

    fun getNotesAtPosition(position: Int): Notes? {
        return getItem(position)
    }
}