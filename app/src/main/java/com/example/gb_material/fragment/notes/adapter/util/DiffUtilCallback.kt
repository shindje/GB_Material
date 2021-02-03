package com.example.gb_material.fragment.notes.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.example.gb_material.fragment.notes.Data

class DiffUtilCallback(
        private var oldItems: List<Data>,
        private var newItems: List<Data>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].headerText == newItems[newItemPosition].headerText
            &&
            oldItems[oldItemPosition].descriptionText == newItems[newItemPosition].descriptionText
            &&
            oldItems[oldItemPosition].isTask == newItems[newItemPosition].isTask
            &&
            oldItems[oldItemPosition].isHighPriority == newItems[newItemPosition].isHighPriority


    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return Change(
                oldItem,
                newItem
        )
    }

}