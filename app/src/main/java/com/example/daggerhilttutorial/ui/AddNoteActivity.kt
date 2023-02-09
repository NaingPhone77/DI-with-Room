package com.example.daggerhilttutorial.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.daggerhilttutorial.R
import com.example.daggerhilttutorial.databinding.ActivityAddNoteBinding
import com.example.daggerhilttutorial.model.NoteEntity
import com.example.daggerhilttutorial.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddNoteBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteEntity: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if(title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntity = NoteEntity(0,title,desc)
                    repository.saveNote(noteEntity)
                    finish()
                }
                else{
                    Toast.makeText(this@AddNoteActivity, "Please fill Title and Description ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}