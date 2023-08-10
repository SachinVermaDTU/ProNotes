package com.example.pronote.MainRepository

import com.example.pronote.db.Note
import com.example.pronote.db.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    val notesDao: NoteDao
) {

    fun searchQuery(query: String): Flow<List<Note>> = notesDao.searchQuery(query)
        .flowOn(Dispatchers.Main)
        .conflate()

    fun getAllNotes(): Flow<List<Note>> = notesDao.getAllNotes()
        .flowOn(Dispatchers.Main)
        .conflate()

    fun noteWithId(id: Int): Flow<Note>{
        return notesDao.findNoteById(id)
    }

}