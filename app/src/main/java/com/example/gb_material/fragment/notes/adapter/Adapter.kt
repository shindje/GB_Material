package com.example.gb_material.fragment.notes.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_material.R
import com.example.gb_material.fragment.notes.Data
import com.example.gb_material.fragment.notes.adapter.util.*
import kotlinx.android.synthetic.main.recycler_item_note.view.iv_delete
import kotlinx.android.synthetic.main.recycler_item_note.view.iv_drag
import kotlinx.android.synthetic.main.recycler_item_task.view.*

class Adapter(
        var data: MutableList<Data>,
        private val dragListener: OnStartDragListener
) :
        RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    private var maxId: Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_NOTE) {
            NoteViewHolder(
                    inflater.inflate(R.layout.recycler_item_note, parent, false) as View
            )
        } else {
            TaskViewHolder(
                    inflater.inflate(R.layout.recycler_item_task, parent, false) as View
            )
        }
    }

    override fun onBindViewHolder(
            holder: BaseViewHolder,
            position: Int,
            payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val combinedChange =
                    createCombinedPayload(payloads as List<Change<Data>>)
            val oldData = combinedChange.oldData
            val newData = combinedChange.newData

            if (newData.isHighPriority != oldData.isHighPriority)
                (holder as TaskViewHolder).showPriority(data[position].isHighPriority)
        }
    }

    override fun onBindViewHolder (holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.iv_delete.setOnClickListener {
            data.removeAt(holder.layoutPosition)
            notifyItemRemoved(holder.layoutPosition)
        }
        holder.itemView.iv_drag.setOnTouchListener { v, event ->
            if (event.getAction() == MotionEvent.ACTION_DOWN)
                dragListener.onStartDrag(holder)
            false
        }
        if (holder is TaskViewHolder) {
            holder.itemView.iv_priority_high.setOnClickListener {
                changePriority(holder.layoutPosition, false)
            }
            holder.itemView.iv_priority_low.setOnClickListener {
                changePriority(holder.layoutPosition, true)
            }
        }
    }

    private fun changePriority(position: Int, newPriority: Boolean) {
        val newData = mutableListOf<Data>()
        newData.addAll(data)
        newData[position] = data[position].clone()
        newData[position].isHighPriority = newPriority
        setItems(newData)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].isTask) TYPE_TASK else TYPE_NOTE
    }

    private fun generateItem(isTask: Boolean)  =
        Data(maxId++,
            if (isTask) null else "Note " + maxId,
            if (isTask) "Task " + maxId else "Note text " + maxId,
            isTask,
            if (isTask) false else null
        )

    fun add(isTask: Boolean) {
        data.add(generateItem(isTask))
        notifyItemInserted(data.size - 1)
    }

    companion object {
        private const val TYPE_NOTE = 0
        private const val TYPE_TASK = 1
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setItems(newItems: List<Data>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(data, newItems))
        result.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newItems)
    }
}
