package com.karamlyy.noteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class NoteModel (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String?,
    val description: String?,
    val priority: Priority?,
    val isFavorite: Boolean?
) {
    fun areContentTheSame(newItem: NoteModel): Boolean {
        return this.title == newItem.title && this.description == newItem.description && this.priority == newItem.priority && this.isFavorite == newItem.isFavorite
    }
}