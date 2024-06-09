package com.reverb.notesapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.reverb.notesapp.adapters.OnNoteClickListener
import com.reverb.notesapp.data.entity.Note
import com.reverb.notesapp.databinding.ItemNotesBinding
import java.text.DateFormat
import java.text.SimpleDateFormat

class NoteViewholder(
    binding: ItemNotesBinding,
    private val notesList: List<Note>,
    private val listener: OnNoteClickListener
): RecyclerView.ViewHolder(binding.root) {

    private var binding: ItemNotesBinding?  = null

    init {
        this.binding = binding
        binding.apply {
            root.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val note = notesList[position]
                    listener.onNoteClick(note)
                }
            }

            root.setOnLongClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val note = notesList[position]
                    listener.onNoteLongClick(note)
                }
                true
            }
        }
    }


    fun render(note: Note){
        binding?.apply {
            tvNoteTitle.text = note.title
            tvNoteContent.text = note.content
            val formatter = SimpleDateFormat("dd-mm-yyyy")
            tvNoteDate.text =formatter.format(note.date)
        }
    }

}