package com.example.pronote.db
import androidx.room.Database
import androidx.room.RoomDatabase

//24
//
//Abstract classes permit providing a partial set
// of default implementations of methods in a class.
// Since they're incomplete, they can't be instantiated
// and used as they stand, but they can be subclassed to
// add the missing details in a way that's specific to
// that particular implementations, and those subclasses can be instantiated.
@Database(entities = [Note::class], version = 2)
 abstract class NoteDatabase: RoomDatabase()
{
     abstract fun getNoteDao(): NoteDao
}