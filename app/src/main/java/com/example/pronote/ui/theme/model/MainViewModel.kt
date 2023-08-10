package com.example.pronote.ui.theme.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pronote.MainRepository.MainRepository
import com.example.pronote.db.Note
import com.example.pronote.db.NoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(
    private val notesDao: NoteDao,
    val repository: MainRepository
) : ViewModel() {

    private val _title: MutableLiveData<String> = MutableLiveData()
    val title: LiveData<String> = _title

    fun onTitleChanged(newName: String) {
        _title.value = newName
    }

    private val _body: MutableLiveData<String> = MutableLiveData()
    val body: LiveData<String> = _body

    fun onBodyChanged(newName: String) {
        _body.value = newName
    }

    var _searchViewVisible = MutableLiveData(false)
    val searchViewVisible: LiveData<Boolean> = _searchViewVisible

    fun searchViewVisibility(isVisible: Boolean) {
        _searchViewVisible.value = isVisible
    }

    var _searchParam = MutableLiveData("")
    val searchParam: LiveData<String> = _searchParam


    fun updateSearchParam(newValue: String) {
        _searchParam.value = newValue
    }


    val notes: LiveData<List<Note>> = repository.getAllNotes().asLiveData()


    //move local db operation to repository layer
    fun insertNote(note: Note) {
        viewModelScope.launch {
            notesDao.insertNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            notesDao.updateNote(note)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            notesDao.deleteAllNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesDao.deleteNote(note)
        }
    }


    }


