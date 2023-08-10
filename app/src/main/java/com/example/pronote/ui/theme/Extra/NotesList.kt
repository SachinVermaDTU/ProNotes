package com.example.pronote.ui.theme.Extra

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pronote.db.Note
import com.example.pronote.ui.theme.Extra.NotesListItem
import com.example.pronote.ui.theme.model.MainViewModel
import com.example.pronote.ui.theme.proNoteComposeTheme
import com.example.pronote.ui.theme.purpleD1
import com.example.pronote.ui.theme.subtitle2


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesList(vm: MainViewModel, editNote: (Note) -> Unit) {

    val notes by vm.notes.observeAsState()
    val searchParam by vm.searchParam.observeAsState("")
  proNoteComposeTheme{
        Scaffold(
            topBar = {
                NotesListTopBar(vm, searchParam)
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = {
                        Text(
                            text = "New Note",
                            style = subtitle2,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .wrapContentHeight(Alignment.CenterVertically)
                                .padding(bottom = 2.dp)
                        )
                    },
                    onClick = { editNote(Note(id = 5, title = "", body = "")) },
                    icon = { Icon(imageVector = Icons.Sharp.Add, contentDescription = "Add Icon") },

                )
            }
        ) {
            LazyColumn {
                itemsIndexed(
                    items = notes.orEmpty().filter { it.title?.contains(searchParam)!! }) { _, note ->
                    NotesListItem(note, editNote)
                }
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListTopBar(vm: MainViewModel, searchParam: String) {

    val isSearchBarVisible by vm.searchViewVisible.observeAsState(false)

    Crossfade(targetState = isSearchBarVisible) { isVisible ->
        when (isVisible) {
            true -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                ) {
                    OutlinedTextField(
                        placeholder = { Text(text = "Enter Search Query") },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .padding(start = 8.dp),
                        value = searchParam, onValueChange = {
                            vm.updateSearchParam(it)
                        }
                    )
                    Icon(
                        imageVector = Icons.Sharp.Clear,
                        tint = purpleD1,
                        contentDescription = "clear-icon",
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                            .padding(end = 6.dp, top = 16.dp)
                            .size(30.dp)
                            .clickable {
                                vm.searchViewVisibility(false)
                                vm.updateSearchParam("")
                            }
                    )
                }

            }
            false -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "📝 Notes",
                        style = subtitle2,
                        fontSize = 24.sp,
                    )
                    Icon(
                        imageVector = Icons.Sharp.Search,
                        tint = purpleD1,
                        contentDescription = "Add Icon",
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                            .padding(top = 16.dp, end = 8.dp)
                            .size(30.dp)
                            .clickable {
                                vm.searchViewVisibility(true)
                            }
                    )
                }
            }
        }

    }
}