package com.demo.noteapplication.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.demo.noteapplication.databinding.FragmentListBinding
import com.demo.noteapplication.framwork.viewmodels.NoteListViewModel
import com.demo.noteapplication.presentation.adapters.NoteListAdapter
import com.demo.noteapplication.presentation.listeners.NoteClickListener


class ListFragment : Fragment(), NoteClickListener {

    private var _listFragmentBinding: FragmentListBinding? = null
    private val listFragmentBinding get() = _listFragmentBinding!!
    private val noteListAdapter = NoteListAdapter(arrayListOf(), this)
    private lateinit var noteListViewModel: NoteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _listFragmentBinding = FragmentListBinding.inflate(inflater, container, false)
        return _listFragmentBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteListViewModel = ViewModelProvider(this).get(NoteListViewModel::class.java)
        listFragmentBinding.fabAddNote.setOnClickListener { goToNodeFragment() }
        listFragmentBinding.rvNotes.adapter = noteListAdapter
        noteListViewModel.getNotes()
        observerData()
    }

    private fun observerData() {
        noteListViewModel.noteListLiveData.observe(viewLifecycleOwner, Observer { list ->
            listFragmentBinding.progressBar.visibility = View.GONE
            noteListAdapter.updateNotes(list.sortedByDescending { it.updateTime })
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _listFragmentBinding = null
    }

    private fun goToNodeFragment(id: Long = 0L) {
        val action = ListFragmentDirections.actionListFragmentToNoteFragment(id)
        Navigation.findNavController(listFragmentBinding.root).navigate(action)
    }

    override fun onNoteClicked(id: Long) {
        goToNodeFragment(id)
    }

}