package com.karamlyy.noteapp.data

import com.karamlyy.noteapp.model.NoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val noteDao: NoteDao
){
    fun getAllNotes(): Flow<List<NoteModel>> {
        return noteDao.getAllNotes()
    }

    fun searchNote(searchQuery: String): Flow<List<NoteModel>> {
        return noteDao.searchNote(searchQuery)
    }

    suspend fun insertNote(noteModel: NoteModel) {
        noteDao.insertNote(noteModel)
    }

    suspend fun getNote(noteId: Int): NoteModel {
        return noteDao.getNote(noteId)
    }

    suspend fun updateNote(noteModel: NoteModel) {
        noteDao.updateNote(noteModel)
    }

    suspend fun deleteNote(noteModel: NoteModel) {
        noteDao.deleteNote(noteModel)
    }
}