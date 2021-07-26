package com.demo.noteapplication.framwork

import android.content.Context
import com.demo.core.data.Note
import com.demo.core.repository.NoteDataSource
import com.demo.noteapplication.framwork.db.DatabaseService
import com.demo.noteapplication.framwork.db.NoteEntity

class RoomNoteDataSource(val context: Context) : NoteDataSource {

    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) {
        noteDao.addNoteEntity(NoteEntity.fromNote(note))
    }

    override suspend fun get(id: Long): Note? {
        return noteDao.getNoteEntity(id)?.toNote()
    }

    override suspend fun getAll(): List<Note> {
        return noteDao.getAllNoteEntity().map { it.toNote() }
    }

    override suspend fun remove(note: Note) {
        noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
    }

}