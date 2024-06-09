package com.reverb.notesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import com.reverb.notesapp.R
import com.reverb.notesapp.data.entity.Note
import com.reverb.notesapp.databinding.FragmentAddeditNotesBinding
import com.reverb.notesapp.viewmodel.NoteViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class AddEditNoteFragment:Fragment(R.layout.fragment_addedit_notes) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<NoteViewModel>()
        val binding = FragmentAddeditNotesBinding.bind(requireView())

        val args: AddEditNoteFragmentArgs by navArgs()
        val note = args.noteArg

        if(note != null){
            binding.apply {
                etTitle.setText(note.title)
                etContent.setText(note.content)

                fabSaveNote.setOnClickListener {
                    val title = etTitle.text.toString()
                    val content = etContent.text.toString()
                    val updatedNote = note.copy(title = title, content = content, date = System.currentTimeMillis())
                    viewModel.updateNote(updatedNote)

                }
            }
        }else{
            binding.apply {
                fabSaveNote.setOnClickListener {
                    val title = etTitle.text.toString()
                    val content = etContent.text.toString()
                    val note = Note(title = title, content = content, date =  System.currentTimeMillis())
                    viewModel.insertNote(note)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.notesEvents.collect{
                if( it is NoteViewModel.NotesEvents.NavigateToNotesFragment ){
                    val action = AddEditNoteFragmentDirections.actionAddEditNoteFragmentToNoteFragment()
                    findNavController().navigate(action)
                }
            }
        }

    }

}