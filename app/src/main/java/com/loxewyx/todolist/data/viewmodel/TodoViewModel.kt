package com.loxewyx.todolist.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loxewyx.todolist.domain.TodoManager
import com.loxewyx.todolist.data.model.Todo

class TodoViewModel : ViewModel() {
    private val _todoList = MutableLiveData<List<Todo>>()
    val todoList: LiveData<List<Todo>> = _todoList

    init { loadTodos() }

    private fun loadTodos() {
        _todoList.value = TodoManager.getAllTodos()
    }

    fun addTodoIfNotEmpty(title: String) {
        TodoManager.addTodoIfNotEmpty(title)
        loadTodos()
    }

    fun updateTodoTitle(id: Int, newTitle: String) {
        TodoManager.updateTodoTitle(id, newTitle)
        loadTodos()
    }

    fun deleteTodoById(id: Int) {
        TodoManager.deleteTodoById(id)
        loadTodos()
    }
}
