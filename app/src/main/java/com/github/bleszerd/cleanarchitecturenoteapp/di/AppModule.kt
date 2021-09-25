package com.github.bleszerd.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDao
import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase.Companion.DATABASE_NAME
import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.domain.use_case.AddNoteUseCase
import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.domain.use_case.DeleteNotesUserCase
import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNotesUseCase
import com.github.bleszerd.cleanarchitecturenoteapp.feature_note.domain.use_case.NoteUseCases
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
    fun provideNoteDatabase(app: Application) : NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNotesUserCase = DeleteNotesUserCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
        )
    }
}