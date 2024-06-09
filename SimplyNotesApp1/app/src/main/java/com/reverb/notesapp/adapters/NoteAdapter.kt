package com.reverb.notesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reverb.notesapp.R
import com.reverb.notesapp.data.entity.Note
import com.reverb.notesapp.databinding.ItemNotesBinding
import com.reverb.notesapp.viewholders.NoteViewholder


class NoteAdapter(
    private val notesList: List<Note>,
    private val listener: OnNoteClickListener
): RecyclerView.Adapter<NoteViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewholder {
        val view = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewholder(view, notesList, listener)
    }

    override fun onBindViewHolder(holder: NoteViewholder, position: Int) {
        holder.render( notesList[position] )
    }

    override fun getItemCount(): Int = notesList.size
}

interface OnNoteClickListener{
    fun onNoteClick (note: Note)
    fun onNoteLongClick(note:Note)
}