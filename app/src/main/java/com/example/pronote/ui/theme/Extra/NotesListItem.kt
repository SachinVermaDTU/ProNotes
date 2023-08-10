package com.example.pronote.ui.theme.Extra

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pronote.db.Note
import com.example.pronote.ui.theme.body1
import com.example.pronote.ui.theme.model.MainViewModel
import com.example.pronote.ui.theme.purpleD1
import com.example.pronote.ui.theme.purpleD3
import com.example.pronote.ui.theme.subtitle1

@Composable
fun NotesListItem(note: Note, editNote: (Note) -> Unit) {
    var vm :MainViewModel


    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(true) {
                editNote(note)
            }

    ) {
        Column(
            modifier = Modifier
                .background(purpleD3)
                .padding(8.dp)
        ) {
            Row {
                Text(
                    modifier = Modifier
                        .background(purpleD3)
                        .padding(8.dp)
                        .fillMaxWidth(.95f),
                    text = note.title ?: "",
                    style = body1,
                    fontSize = 20.sp,
                )

                Box(modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
                    .padding(top = 10.dp)
                    .size(25.dp)
                    .clickable {

                    }) {
                    Icon(
                        imageVector = Icons.Sharp.Delete,
                        tint = purpleD1,
                        contentDescription = "delete-note",
                        modifier = Modifier.size(40.dp)
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.body ?: "",
                style = subtitle1,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }

    }


}