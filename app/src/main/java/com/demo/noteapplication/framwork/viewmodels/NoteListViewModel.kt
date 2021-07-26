package com.demo.noteapplication.framwork.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.demo.core.data.Note
import com.demo.noteapplication.framwork.UseCases
import com.demo.noteapplication.framwork.di.ApplicationModule
import com.demo.noteapplication.framwork.di.DaggerViewModelComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var useCases: UseCases
    private var noteListMutableLiveData = MutableLiveData<List<Note>>()
    val noteListLiveData: LiveData<List<Note>> get() = noteListMutableLiveData

    init {
        DaggerViewModelComponent.builder().applicationModule(ApplicationModule(application)).build()
            .inject(this)
    }

    // private val repository = NoteRepository(RoomNoteDataSource(application))
    /*private val useCases = UseCases(
        AddNote(repository), GetAllNotes(repository), GetNote(repository),
        RemoveNote(repository))
*/
    fun getNotes() {
        viewModelScope.launch {
            val noteList = useCases.getAllNotes()
            noteListMutableLiveData.postValue(noteList)
        }
    }
}
