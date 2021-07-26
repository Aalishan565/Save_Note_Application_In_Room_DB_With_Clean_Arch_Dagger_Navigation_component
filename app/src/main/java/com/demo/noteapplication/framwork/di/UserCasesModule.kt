package com.demo.noteapplication.framwork.di

import com.demo.core.repository.NoteRepository
import com.demo.core.use_case.AddNote
import com.demo.core.use_case.GetAllNotes
import com.demo.core.use_case.GetNote
import com.demo.core.use_case.RemoveNote
import com.demo.noteapplication.framwork.UseCases
import dagger.Module
import dagger.Provides

@Module
class UserCasesModule() {

    @Provides
    fun provideUseCases(repository: NoteRepository): UseCases {
        return UseCases(
            AddNote(repository), GetAllNotes(repository), GetNote(repository),
            RemoveNote(repository)
        )
    }
}