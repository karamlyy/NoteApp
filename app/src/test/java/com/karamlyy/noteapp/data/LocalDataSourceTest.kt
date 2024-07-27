package com.karamlyy.noteapp.data

import android.provider.ContactsContract.CommonDataKinds.Note
import com.karamlyy.noteapp.model.NoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows


class LocalDataSourceTest {
    @Mock
    private lateinit var noteDao: NoteDao
    private lateinit var localDataSource: LocalDataSource
    private lateinit var noteList: List<NoteModel>
    private var exceptionMessage = "Error detected"

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        localDataSource = LocalDataSource(noteDao)
        noteList = listOf<NoteModel>(mock())
    }

    @Test
    fun test_getAllNotes() = runTest {

        // when
        `when`(noteDao.getAllNotes()).thenReturn(flowOf(noteList))

        // act
        val result = localDataSource.getAllNotes()

        // assert
        result.collect { notes ->
            assertEquals(noteList, notes)
        }

        // verify
        verify(noteDao, times(1)).getAllNotes()
    }

    @Test
    fun test_getAllNotes_error() = runTest {

        // when
        `when`(noteDao.getAllNotes()).thenThrow(RuntimeException(exceptionMessage))

        // act
        val exception = assertThrows(RuntimeException::class.java) {
            localDataSource.getAllNotes()
        }

        // assert
        assertEquals(exceptionMessage, exception.message)


        // verify
        verify(noteDao, times(1)).getAllNotes()
    }

    @Test
    fun test_searchNote() = runTest {
        // given
        val query = "Test"

        // when
        `when`(noteDao.searchNote(query)).thenReturn(flowOf(noteList))

        // act
        val result = localDataSource.searchNote(query)

        // assert
        result.collect { notes ->
            assertEquals(noteList, notes)
        }

        // verify
        verify(noteDao, times(1)).searchNote(query)

    }

    @Test
    fun test_insert() = runTest {
        // given
        val note: NoteModel = mock()

        // when
        `when`(noteDao.insertNote(note)).thenReturn(Unit)

        // act
        localDataSource.insertNote(note)

        // verify
        verify(noteDao, times(1)).insertNote(note)
    }

}