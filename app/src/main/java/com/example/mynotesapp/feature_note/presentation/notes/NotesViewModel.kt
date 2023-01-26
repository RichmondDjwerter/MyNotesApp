package com.example.mynotesapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.feature_note.domain.module.Note
import com.example.mynotesapp.feature_note.domain.use_case.NoteUseCases
import com.example.mynotesapp.feature_note.domain.util.NoteOrder
import com.example.mynotesapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteUseCases: NoteUseCases) : ViewModel() {
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(events: NotesEvents) {
        when (events) {
            is NotesEvents.Order -> {
                if (state.value.noteOrder::class == events.noteOrder::class &&
                    state.value.noteOrder.orderType == events.noteOrder.orderType
                ) {
                    return
                }
                getNotes(events.noteOrder)
            }
            is NotesEvents.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(events.note)
                    recentlyDeletedNote = events.note
                }
            }
            is NotesEvents.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvents.ToggleOrderSelection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder = noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }
}