package com.demo.noteapplication.framwork.di

import com.demo.noteapplication.framwork.viewmodels.NoteListViewModel
import com.demo.noteapplication.framwork.viewmodels.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UserCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(noteListViewModel: NoteListViewModel)
}