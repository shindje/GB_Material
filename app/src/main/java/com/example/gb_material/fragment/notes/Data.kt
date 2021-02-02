package com.example.gb_material.fragment.notes

data class Data (val id: Int, val headerText: String?, val descriptionText: String, val isTask: Boolean, var isHighPriority: Boolean?) {
    fun clone(): Data {
        return Data (id, headerText, descriptionText, isTask, isHighPriority)
    }
}