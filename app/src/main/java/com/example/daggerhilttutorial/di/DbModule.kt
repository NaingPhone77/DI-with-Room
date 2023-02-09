package com.example.daggerhilttutorial.di

import android.content.Context
import androidx.room.Room
import com.example.daggerhilttutorial.data.NoteDatabase
import com.example.daggerhilttutorial.model.NoteEntity
import com.example.daggerhilttutorial.utils.Constant.NOTE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, NoteDatabase::class.java, NOTE_DATABASE
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: NoteDatabase) = db.noteDao()


    @Provides
    @Singleton
    fun provideEntity() = NoteEntity()
}