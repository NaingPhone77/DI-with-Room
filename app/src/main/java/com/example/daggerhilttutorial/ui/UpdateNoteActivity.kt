package com.example.daggerhilttutorial.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.daggerhilttutorial.R
import com.example.daggerhilttutorial.adapter.NoteAdapter
import com.example.daggerhilttutorial.databinding.ActivityUpdateNoteBinding
import com.example.daggerhilttutorial.model.NoteEntity
import com.example.daggerhilttutorial.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateNoteBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteAdapter: NoteAdapter

    @Inject
    lateinit var noteEntity: NoteEntity

    private var noteId = 0
    private var defaultTitle = ""
    private var defaultDesc = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?. let {
            noteId = it.getInt("NOTE_ID")
        }

        binding.apply {
            defaultTitle = repository.getNote(noteId).noteTitle
            defaultDesc = repository.getNote(noteId).noteDesc

            etTitle.setText(defaultTitle)
            etDesc.setText(defaultTitle)

            btnDelete.setOnClickListener {
                noteEntity = NoteEntity(noteId,defaultTitle,defaultDesc)
                repository.deleteNote(noteEntity)
                finish()
            }

            btnSave.setOnClickListener {
                val title = etTitle.text.toString()
                val desc = etDesc.text.toString()


                if(title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntity = NoteEntity(noteId,title,desc)
                    repository.updateNote(noteEntity)
                    finish()
                }
                else{
                    Toast.makeText(this@UpdateNoteActivity, "Please fill Title and Description ", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}