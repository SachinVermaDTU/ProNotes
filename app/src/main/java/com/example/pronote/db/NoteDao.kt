package com.example.pronote.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

   //    One-shot queries are database operations that
   //    only run once and grab a snapshot of data at the
   //    time of execution.

   //Suspending functions are at the center of everything coroutines.
   // A suspending function is simply a function that can be paused and
   // resumed at a later time. They can execute a long running operation
   // and wait for it to complete without blocking.

   //    Observable queries are read operations that emit new values
   //    whenever there are changes to any of the tables that are
   //    referenced by the query. One way you might use this is to
   //    help you keep a displayed list of items up to date as the
   //    items in the underlying database are inserted, updated, or
   //    removed

   //   Note: Observable queries in Room have one important limitation:
   //   the query reruns whenever any row in the table is updated,
   //   whether or not that row is in the result set. You can ensure
   //   that the UI is only notified when the actual query results
   //   change by applying the distinctUntilChanged()


   // flow
   //In coroutines, a flow is a type that can
   // emit multiple values sequentially, as opposed to suspend
   // functions that return only a single value.
   //Flows are built on top of coroutines and can provide multiple
   // values. A flow is conceptually a stream of data that can be
   // computed asynchronously. The emitted values must be of the same
   // type. For example, a Flow<Int> is a flow that emits integer values.

   //A flow is very similar to an Iterator that produces a sequence of
   // values, but it uses suspend functions to produce and consume values
   // asynchronously. This means, for example, that the flow can safely
   // make a network request to produce the next value without blocking
   // the main thread.

    //However, this behaviour of the database also means that if we
    // update an unrelated row, like Bandit, our Flow will emit again,
    // with the same result: (Frida, 11, 3). Because SQLite database
    // triggers only allow notifications at table level and not at
    // row level, Room can’t know what exactly has changed in the
    // table data, therefore it re-triggers the query defined in the DAO.

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Note>>


    @Query("SELECT * FROM notes WHERE title LIKE '%' || :titleParam || '%' ")
    fun searchQuery(titleParam: String): Flow<List<Note>>

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
     suspend fun updateNote(note: Note)

    @Query("SELECT * FROM notes WHERE id = :noteId")
      fun findNoteById(noteId: Int): Flow<Note>

}