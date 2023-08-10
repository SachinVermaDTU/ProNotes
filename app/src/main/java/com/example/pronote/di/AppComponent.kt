package com.example.pronote.di
import android.content.Context
import androidx.room.Room
import com.example.pronote.db.NoteDao
import com.example.pronote.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {


    //FallBackToDestructivelyMigration
    //Allows Room to destructively recreate database tables if
    // Migrations that would migrate old database schemas to the
    // latest schema version are not found.
    //When the database version on the device does not match the
    // latest schema version, Room runs necessary Migrations on the
    // database.
    //If it cannot find the set of Migrations that will bring the
    // database to the current version, it will throw an
    // IllegalStateException.

    @Singleton
    @Provides
    fun provideDao(noteDatabase: NoteDatabase): NoteDao =
        noteDatabase.getNoteDao()

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note-db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

}