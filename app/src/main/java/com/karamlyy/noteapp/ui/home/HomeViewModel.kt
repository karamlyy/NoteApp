package com.karamlyy.noteapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.karamlyy.noteapp.data.Repository
import com.karamlyy.noteapp.model.NoteModel
import com.karamlyy.noteapp.model.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application, private val repository: Repository
) : AndroidViewModel(application){

    val noteList = repository.localDataSource.getAllNotes().asLiveData()

    fun insertNote() {
        viewModelScope.launch {
            repository.localDataSource.insertNote(NoteModel(title = "Title2", description = "Desc", priority = Priority.HIGH, isFavorite = true ))
        }
    }
}