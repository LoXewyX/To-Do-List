package com.loxewyx.todolist.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loxewyx.todolist.data.model.Todo

class TodoLocalStorage(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "todo_prefs",
        Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun saveTodos(todos: List<Todo>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(todos)
        editor.putString("todos", json)
        editor.apply()
    }

    fun loadTodos(): List<Todo> {
        val json = sharedPreferences.getString("todos", null)
        val type = object : TypeToken<List<Todo>>() {}.type
        return if (json != null) gson.fromJson(json, type)
        else emptyList()
    }
}
