package com.example.uasmobileapp.ui.list

data class Task(
    val id: String = "",          // ✅ DEFAULT VALUE
    val title: String,
    val description: String,
    val category: String,
    val status: String,
    val createdTime: Long,
    val takenTime: Long? = null,
    val doneTime: Long? = null
)

