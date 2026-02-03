package com.example.uasmobileapp.data.repository

import com.example.uasmobileapp.data.remote.RetrofitClient
import com.example.uasmobileapp.data.dto.CreateTaskRequest
import com.example.uasmobileapp.ui.list.Task

class TaskRepository {

    private val api = RetrofitClient.api

    suspend fun getTasks(): List<Task> {
        return api.getTasks().body() ?: emptyList()
    }

    suspend fun addTask(task: Task): Task? {
        val request = CreateTaskRequest(
            title = task.title,
            description = task.description,
            category = task.category,
            status = task.status,
            createdTime = task.createdTime
        )

        return api.addTask(request).body()
    }


    suspend fun updateTask(task: Task): Task? {
        return api.updateTask(task.id, task).body()
    }
}
