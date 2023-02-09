package com.example.daggerhilttutorial.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerhilttutorial.adapter.NoteAdapter
import com.example.daggerhilttutorial.databinding.ActivityMainBinding
import com.example.daggerhilttutorial.model.NoteEntity
import com.example.daggerhilttutorial.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteadapter: NoteAdapter

    @Inject
    lateinit var noteEntity: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this,AddNoteActivity::class.java))
        }

        checkItem()

    }

    override fun onResume() {
        super.onResume()
        checkItem()

    }

     private fun checkItem() {
        binding.apply {
            if(repository.getAllNotes().isNotEmpty()){
                rvNoteList.visibility = View.VISIBLE
                tvEmptyText.visibility = View.GONE

                //we want to attach this adapter into my recycler view
                noteadapter.differ.submitList(repository.getAllNotes())

                setupRecyclerView()
            }
            else{
                rvNoteList.visibility = View.GONE
                tvEmptyText.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvNoteList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteadapter
        }
    }
}