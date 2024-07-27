package com.karamlyy.noteapp.ui.newAndEdit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karamlyy.noteapp.data.Repository
import com.karamlyy.noteapp.model.NoteModel
import com.karamlyy.noteapp.model.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewAndEditViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
): AndroidViewModel(application) {

    val noteModel = MutableLiveData<NoteModel>()

    fun insertNote(title: String, description: String, isFavorite: Boolean, priority: Priority) {
        viewModelScope.launch {
            repository.localDataSource.insertNote(
                NoteModel(
                    title = title,
                    description = description,
                    priority = priority,
                    isFavorite = isFavorite
                )
            )
        }
    }

    fun getNote(noteId: Int) {
        viewModelScope.launch {
            val note = repository.localDataSource.getNote(noteId)
            noteModel.value = note
        }
    }

    fun updateNote(title: String, description: String, isFavorite: Boolean, priority: Priority) {
        viewModelScope.launch {
            repository.localDataSource.updateNote(
                NoteModel(
                id = noteModel.value?.id ?: 0,
                title = title,
                description = description,
                isFavorite = isFavorite,
                priority = priority
            )
            )
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            noteModel.value?.let {
                repository.localDataSource.deleteNote(it)
            }
        }
    }
}