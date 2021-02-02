package com.example.gb_material.fragment.notes.adapter.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_material.fragment.notes.adapter.Adapter

class ItemTouchHelperCallback (private val adapter: Adapter) :
        ItemTouchHelper.Callback() {

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(
                dragFlags,
                swipeFlags
        )
    }

    override fun onMove(
            recyclerView: RecyclerView,
            source: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(source.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }
}
