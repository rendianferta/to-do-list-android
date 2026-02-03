package com.example.uasmobileapp.data.remote

import com.example.uasmobileapp.ui.list.Task
import com.example.uasmobileapp.data.dto.CreateTaskRequest
import retrofit2.Response
import retrofit2.http.*

interface TaskApiService {

    @GET("tasks")
    suspend fun getTasks(): Response<List<Task>>

    @POST("tasks")
    suspend fun addTask(
        @Body body: CreateTaskRequest
    ): Response<Task>


    @PUT("tasks/{id}")
    suspend fun updateTask(
        @Path("id") id: String,
        @Body task: Task
    ): Response<Task>

}
