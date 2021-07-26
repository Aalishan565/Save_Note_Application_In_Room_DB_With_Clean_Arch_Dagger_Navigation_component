package com.demo.noteapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.core.data.Note
import com.demo.noteapplication.databinding.ItemNoteBinding
import com.demo.noteapplication.presentation.listeners.NoteClickListener
import java.text.SimpleDateFormat
import java.util.*

class NoteListAdapter(var noteList: ArrayList<Note>, val noteClickListener: NoteClickListener) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    fun updateNotes(noteList: List<Note>) {
        this.noteList.clear()
        this.noteList.addAll(noteList)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(val itemNoteBinding: ItemNoteBinding) :
        RecyclerView.ViewHolder(itemNoteBinding.root) {
        init {
            itemNoteBinding.noteLayout.setOnClickListener {
                noteClickListener.onNoteClicked(noteList[absoluteAdapterPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        with(holder.itemNoteBinding) {
            tvTitle.text = noteList[position].title
            tvContent.text = noteList[position].content
            val sdf = SimpleDateFormat("MMM dd,HH:mm:ss")
            val currentDate = Date(noteList[position].updateTime)
            tvDate.text = "Last Updated: ${sdf.format(currentDate)}"
        }
    }

    override fun getItemCount() = noteList.size

}