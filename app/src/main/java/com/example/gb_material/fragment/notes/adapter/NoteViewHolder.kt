package com.example.gb_material.fragment.notes.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_material.fragment.notes.Data
import kotlinx.android.synthetic.main.recycler_item_note.view.*

class NoteViewHolder(view: View) : BaseViewHolder(view) {

    override fun bind(data: Data) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            itemView.header.text = data.headerText
            itemView.description.text = data.descriptionText
        }
    }
}