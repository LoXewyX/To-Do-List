package com.loxewyx.todolist.domain

import android.content.Context
import com.loxewyx.todolist.data.local.TodoLocalStorage
import com.loxewyx.todolist.data.model.Todo
import java.time.Instant
import java.util.Date
import java.util.concurrent.atomic.AtomicInteger

object TodoManager {
    private lateinit var localStorage: TodoLocalStorage
    private val todoList = mutableListOf<Todo>()
    private val idGenerator = AtomicInteger()

    fun init(context: Context) {
        localStorage = TodoLocalStorage(context)
        todoList.clear()
        todoList.addAll(localStorage.loadTodos())
        idGenerator.set(todoList.size)
    }

    fun getAllTodos(): List<Todo> {
        return todoList.toList()
    }

    fun addTodoIfNotEmpty(title: String) {
        if (title.isNotBlank()) {
            val newTodo = Todo(
                id = idGenerator.incrementAndGet(),
                title = title,
                createdAt = Date.from(Instant.now()),
                isUpdated = false
            )
            todoList.add(newTodo)
            localStorage.saveTodos(todoList)
        }
    }

    fun updateTodoTitle(id: Int, newTitle: String) {
        val index = todoList.indexOfFirst { it.id == id }
        if (index != -1 && newTitle.isNotBlank()) {
            val oldTodo = todoList[index]
            todoList[index] = oldTodo.copy(
                title = newTitle,
                createdAt = Date.from(Instant.now()),
                isUpdated = true
            )
            localStorage.saveTodos(todoList)
        }
    }

    fun deleteTodoById(id: Int) {
        todoList.removeIf { it.id == id }
        localStorage.saveTodos(todoList)
    }
}
