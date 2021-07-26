package com.demo.core.use_case

import com.demo.core.data.Note
import com.demo.core.repository.NoteRepository

class GetNote(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Long) = repository.getNote(id)
}