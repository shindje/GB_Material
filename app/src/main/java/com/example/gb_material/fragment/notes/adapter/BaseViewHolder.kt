package com.example.gb_material.fragment.notes.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_material.fragment.notes.Data

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: Data)
}