package com.example.mynotesapp.feature_note.domain.repository

import com.example.mynotesapp.feature_note.domain.module.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNote(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

}