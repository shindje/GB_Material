package com.example.gb_material.fragment.notes.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_material.fragment.notes.Data
import kotlinx.android.synthetic.main.recycler_item_task.view.*

class TaskViewHolder(view: View) : BaseViewHolder(view) {

    override fun bind(data: Data) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            itemView.text.text = data.descriptionText
            showPriority(data.isHighPriority)
        }
    }

    fun showPriority(isHigh: Boolean?) {
        if (isHigh == true) {
            itemView.iv_priority_high.visibility = View.VISIBLE
            itemView.iv_priority_low.visibility = View.GONE
        } else {
            itemView.iv_priority_high.visibility = View.GONE
            itemView.iv_priority_low.visibility = View.VISIBLE
        }
    }
}