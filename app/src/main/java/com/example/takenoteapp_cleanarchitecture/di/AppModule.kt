package com.example.takenoteapp_cleanarchitecture.di

import android.app.Application
import androidx.room.Room
import com.example.takenoteapp_cleanarchitecture.feature_take_note.data.data_source.NoteDatabase
import com.example.takenoteapp_cleanarchitecture.feature_take_note.data.repository.NoteRepositoryImpl
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.repository.NoteRepository
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case.DeleteNoteUseCase
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case.GetNoteUseCase
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case.GetNotesUseCase
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case.InsertNoteUseCase
import com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {

        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()

    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository {

        return NoteRepositoryImpl(noteDatabase.noteDao)

    }

    @Provides
    @Singleton
    fun provideGetNotesUseCase(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            GetNotesUseCase(repository),
            DeleteNoteUseCase(repository),
            InsertNoteUseCase(repository),
            GetNoteUseCase(repository)
        )
    }

}