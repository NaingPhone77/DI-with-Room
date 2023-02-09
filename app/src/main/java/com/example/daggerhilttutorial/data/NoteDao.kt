package com.example.daggerhilttutorial.data

import androidx.room.*
import com.example.daggerhilttutorial.model.NoteEntity
import com.example.daggerhilttutorial.utils.Constant.NOTE_TABLE

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntity: NoteEntity)

    @Update
    fun updateNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM $NOTE_TABLE WHERE noteId LIKE :id")
    fun getNote(id: Int): NoteEntity

    @Query("SELECT * FROM $NOTE_TABLE ORDER BY noteId DESC")
    fun getAllNotes(): MutableList<NoteEntity>


}