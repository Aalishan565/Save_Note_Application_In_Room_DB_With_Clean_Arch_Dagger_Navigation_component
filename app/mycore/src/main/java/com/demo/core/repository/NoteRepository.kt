package com.demo.core.repository

import com.demo.core.data.Note

class NoteRepository(private val dataSource: NoteDataSource) {
    suspend fun add(note: Note) = dataSource.add(note)
    suspend fun removeNote(note: Note) = dataSource.remove(note)
    suspend fun getNote(id: Long) = dataSource.get(id)
    suspend fun getAllNote() = dataSource.getAll()
}