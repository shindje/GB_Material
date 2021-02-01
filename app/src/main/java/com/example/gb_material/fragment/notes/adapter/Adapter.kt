package com.example.gb_material.fragment.notes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_material.R
import com.example.gb_material.fragment.notes.Data
import com.example.gb_material.fragment.notes.OnListItemClickListener

class Adapter(
        private var onListItemClickListener: OnListItemClickListener,
        private var data: List<Data>
) :
        RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 0) {
            NoteViewHolder(
                    inflater.inflate(R.layout.recycler_item_note, parent, false) as View
            )
        } else {
            TaskViewHolder(
                    inflater.inflate(R.layout.recycler_item_task, parent, false) as View
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].isTask) 1 else 0
    }
}
