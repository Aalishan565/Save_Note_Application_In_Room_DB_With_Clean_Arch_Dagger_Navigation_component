package com.demo.noteapplication.framwork

import com.demo.core.use_case.AddNote
import com.demo.core.use_case.GetAllNotes
import com.demo.core.use_case.GetNote
import com.demo.core.use_case.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)