package com.example.madlevel5task1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "NoteTable")
data class Note(

    var title: String,

    var lastUpdated: Date,

    var text: String,

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

)