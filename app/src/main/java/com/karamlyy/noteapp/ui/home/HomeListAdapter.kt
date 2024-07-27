package com.karamlyy.noteapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karamlyy.noteapp.databinding.ItemHomeFragmentNoteBinding
import com.karamlyy.noteapp.model.NoteModel

class HomeListAdapter(private val noteClickListener: NoteClickListener) : ListAdapter<NoteModel, HomeListAdapter.ViewHolder>(NoteDiffCallBack()){
    class ViewHolder(private val binding: ItemHomeFragmentNoteBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(noteClickListener: NoteClickListener, noteModel: NoteModel) {
            binding.noteModel = noteModel
            binding.noteClickListener = noteClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemHomeFragmentNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }

    private class NoteDiffCallBack: DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.areContentTheSame(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(noteClickListener, currentList[position])
    }
}

interface NoteClickListener {
    fun onNoteClick(id: Int)
    fun onNoteChecked(noteModel: NoteModel)
}