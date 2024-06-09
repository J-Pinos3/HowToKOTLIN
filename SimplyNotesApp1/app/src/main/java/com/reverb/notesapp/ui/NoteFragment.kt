package com.reverb.notesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.reverb.notesapp.R
import com.reverb.notesapp.adapters.NoteAdapter
import com.reverb.notesapp.adapters.OnNoteClickListener
import com.reverb.notesapp.data.entity.Note
import com.reverb.notesapp.databinding.FragmentNotesBinding
import com.reverb.notesapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NoteFragment: Fragment(R.layout.fragment_notes), OnNoteClickListener {

    val viewModel by viewModels<NoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentNotesBinding.bind(requireView())
        binding.apply {
            rvNotes.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            rvNotes.setHasFixedSize(true)

            fabAddNote.setOnClickListener {
                val action = NoteFragmentDirections.actionNoteFragmentToAddEditNoteFragment(null)
                findNavController().navigate(action )
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.notes.collect{
                    val notesAdapter = NoteAdapter(it, this@NoteFragment)
                    rvNotes.adapter = notesAdapter
                }
            }

            viewLifecycleOwner.lifecycleScope.launch{
                viewModel.notesEvents.collect{ event ->
                    if (event is NoteViewModel.NotesEvents.ShowUndoSnackBar){
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).setAction("Undo"){
                            viewModel.insertNote(event.note)
                        }.show()
                    }
                }
            }
        }

    }

    override fun onNoteClick(note: Note) {
        val action = NoteFragmentDirections.actionNoteFragmentToAddEditNoteFragment(note)
        findNavController().navigate(action)
    }

    override fun onNoteLongClick(note: Note) {
        viewModel.deleteNote(note)
    }

}