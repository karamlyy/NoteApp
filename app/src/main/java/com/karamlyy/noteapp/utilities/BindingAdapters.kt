package com.karamlyy.noteapp.utilities

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Query
import com.karamlyy.noteapp.R
import com.karamlyy.noteapp.model.NoteModel
import com.karamlyy.noteapp.model.Priority
import com.karamlyy.noteapp.ui.home.HomeListAdapter
import com.karamlyy.noteapp.ui.home.NoteClickListener

@BindingAdapter("setItemNotePriorityTint")
fun setItemNotePriorityTint(imageView: ImageView, priority: Priority?) {
    val context = imageView.context

    val color = when(priority) {
        Priority.HIGH -> R.color.priority_high
        Priority.MEDIUM -> R.color.md_theme_light_surfaceTint
        else -> R.color.seed
    }

    ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(ContextCompat.getColor(context, color)))
}

@BindingAdapter("noteList", "setOnClickListener", "searchQuery", "searchNoteList")
fun setHomeRecyclerViewAdapter(
    recyclerView: RecyclerView,
    list: List<NoteModel>?,
    noteClickListener: NoteClickListener,
    searchQuery: String,
    searchList: List<NoteModel>?

) {
    recyclerView.apply {
        if (this.adapter == null) {
            adapter = HomeListAdapter(noteClickListener).apply { submitList(
                if (searchQuery.isEmpty()) {
                    list
                } else {
                    searchList
                }
            ) }
        } else {
            (this.adapter as HomeListAdapter).submitList(
                if (searchQuery.isEmpty()) {
                    list
                } else {
                    searchList
                }
            )
        }
    }
}