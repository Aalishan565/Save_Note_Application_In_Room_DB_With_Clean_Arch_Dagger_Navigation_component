package com.demo.core.use_case

import com.demo.core.data.Note
import com.demo.core.repository.NoteRepository

class RemoveNote(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.removeNote(note)
}