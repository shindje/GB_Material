package com.example.gb_material.fragment.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_material.R
import com.example.gb_material.fragment.notes.adapter.Adapter
import com.example.gb_material.fragment.notes.adapter.ItemTouchHelperCallback
import com.example.gb_material.fragment.notes.adapter.OnStartDragListener
import kotlinx.android.synthetic.main.notes_fragment.*

class NotesFragment: Fragment() {
    lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.notes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arrayListOf(
                Data(1, "Note 1", "text Note 1", false),
                Data(2, "Note 2", "text Note 2", false),
                Data(3, null, "Task 3", true),
                Data(4, null, "Task 4", true),
        )
        recycler.layoutManager = LinearLayoutManager(context)
        val adapter = Adapter(
                data,
                object : OnStartDragListener {
                    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                        itemTouchHelper.startDrag(viewHolder)
                    }
                }
        )
        recycler.adapter = adapter
        fab_add_note.setOnClickListener { adapter.add(false) }
        fab_add_task.setOnClickListener { adapter.add(true) }
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(recycler)
    }
}