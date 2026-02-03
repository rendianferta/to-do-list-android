package com.example.uasmobileapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.uasmobileapp.data.repository.TaskRepository
import com.example.uasmobileapp.ui.list.Task
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val repository = TaskRepository()

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = repository.getTasks()
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
            loadTasks()
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
            loadTasks()
        }
    }
}
