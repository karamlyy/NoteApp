package com.karamlyy.noteapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
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
    var searchNoteList: LiveData<List<NoteModel>> = MutableLiveData()
    val searchQuery = MutableLiveData("")

    fun updateNote(noteModel: NoteModel) {
        val updatedNoteModel = noteModel.copy(isFavorite = noteModel.isFavorite?.not())
        viewModelScope.launch {
            repository.localDataSource.updateNote(updatedNoteModel)
        }
    }

    fun searchNote(searchQuery: String) {
        searchNoteList = repository.localDataSource.searchNote("%$searchQuery%").asLiveData()
        this.searchQuery.value = searchQuery
    }
}