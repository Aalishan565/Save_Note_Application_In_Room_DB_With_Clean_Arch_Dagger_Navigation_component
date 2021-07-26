package com.demo.noteapplication.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.demo.core.data.Note
import com.demo.noteapplication.databinding.FragmentNoteBinding
import com.demo.noteapplication.framwork.viewmodels.NoteViewModel

class NoteFragment : Fragment() {

    private var _fragmentNoteBinding: FragmentNoteBinding? = null
    private val fragmentNoteBinding get() = _fragmentNoteBinding!!
    private lateinit var noteViewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)
    private var noteId = 0L
    private val args: NoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentNoteBinding = FragmentNoteBinding.inflate(inflater, container, false)
        return _fragmentNoteBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteId = args.noteId
        if (noteId != 0L) {
            noteViewModel.getNote(noteId)
        }
        fragmentNoteBinding.floatingButtonSave.setOnClickListener {
            if (fragmentNoteBinding.etTitle.text.isNotBlank() || fragmentNoteBinding.etContent.text.isNotBlank()) {
                val time = System.currentTimeMillis()
                currentNote.title = fragmentNoteBinding.etTitle.text.toString()
                currentNote.content = fragmentNoteBinding.etContent.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }
                noteViewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(fragmentNoteBinding.root).popBackStack()
            }
        }
        observerSavedData()
    }

    private fun observerSavedData() {
        noteViewModel.saveLiveData.observe(viewLifecycleOwner, { status ->
            if (status) {
                Toast.makeText(requireActivity(), "Done", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(fragmentNoteBinding.root).popBackStack()
            } else {
                Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
        noteViewModel.currentNoteLiveData.observe(viewLifecycleOwner, { note ->
            note?.let {
                currentNote = it
                fragmentNoteBinding.etTitle.setText(currentNote.title, TextView.BufferType.EDITABLE)
                fragmentNoteBinding.etContent.setText(
                    currentNote.content,
                    TextView.BufferType.EDITABLE
                )
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentNoteBinding = null
    }

}