package com.karamlyy.noteapp.data

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.karamlyy.noteapp.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table")
    fun getAllNotes(): Flow<List<NoteModel>>

    @Query("SELECT * FROM note_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchNote(searchQuery: String) : Flow<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteModel: NoteModel)

    @Update
    suspend fun updateNote(noteModel: NoteModel)

    @Query("SELECT * FROM note_table WHERE id=:noteId")
    suspend fun getNote(noteId: Int): NoteModel

    @Delete
    suspend fun deleteNote(noteModel: NoteModel)

}