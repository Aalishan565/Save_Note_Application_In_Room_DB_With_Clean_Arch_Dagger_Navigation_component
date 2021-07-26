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

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var useCases: UseCases
    private var saved = MutableLiveData<Boolean>()
    private var currentNoteMutableLiveData = MutableLiveData<Note?>()
    val currentNoteLiveData: LiveData<Note?> get() = currentNoteMutableLiveData
    val saveLiveData: LiveData<Boolean> get() = saved

    init {
        DaggerViewModelComponent.builder().applicationModule(ApplicationModule(application)).build()
            .inject(this)
    }

    fun saveNote(note: Note) {
        viewModelScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(noteId: Long) {
        viewModelScope.launch {
            val note = useCases.getNote(noteId)
            currentNoteMutableLiveData.postValue(note)
        }
    }
}