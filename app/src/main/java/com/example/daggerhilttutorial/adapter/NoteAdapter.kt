package com.example.daggerhilttutorial.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerhilttutorial.databinding.ItemNoteBinding
import com.example.daggerhilttutorial.model.NoteEntity
import com.example.daggerhilttutorial.ui.UpdateNoteActivity
import javax.inject.Inject

class NoteAdapter @Inject constructor()
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : NoteEntity) {
            binding.apply {
                tvTitle.text = item.noteTitle
                tvDesc.text = item.noteDesc
                root.setOnClickListener {
                    val intent = Intent(context,UpdateNoteActivity::class.java)
                    intent.putExtra("NOTE_ID",item.noteId)
                    context.startActivity(intent)
                }
            }

        }

    }

    object DifferCallBack : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,DifferCallBack)

}