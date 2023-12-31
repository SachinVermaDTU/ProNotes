package com.example.pronote.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String?,
    var body: String?,
    val author: String = "",



    ) : Parcelable
