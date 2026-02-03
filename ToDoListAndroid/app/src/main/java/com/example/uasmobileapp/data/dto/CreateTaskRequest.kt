// data/dto/CreateTaskRequest.kt
package com.example.uasmobileapp.data.dto

data class CreateTaskRequest(
    val title: String,
    val description: String,
    val category: String,
    val status: String,
    val createdTime: Long
)
