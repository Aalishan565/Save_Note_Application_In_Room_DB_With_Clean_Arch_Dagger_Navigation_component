package com.demo.noteapplication.framwork.di

import android.app.Application
import com.demo.core.repository.NoteRepository
import com.demo.noteapplication.framwork.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule() {

    @Provides
    fun provideApp(app: Application) = NoteRepository(RoomNoteDataSource(app))
}