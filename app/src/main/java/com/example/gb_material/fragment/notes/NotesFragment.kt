package com.example.gb_material.fragment.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_material.R
import com.example.gb_material.fragment.notes.adapter.Adapter
import kotlinx.android.synthetic.main.notes_fragment.*

class NotesFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.notes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arrayListOf(
                Data(1, "NOte1", "text Note1", false),
                Data(2, "NOte2", "text Note2", false),
                Data(3, null, "Task1", true),
                Data(4, null, "Task2", true),
        )
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = Adapter(
                object : OnListItemClickListener {
                    override fun onItemClick(data: Data) {
                        Toast.makeText(context, data.descriptionText, Toast.LENGTH_SHORT).show()
                    }
                },
                data
        )
    }
}