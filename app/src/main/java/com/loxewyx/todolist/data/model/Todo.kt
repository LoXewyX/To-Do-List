package com.loxewyx.todolist.data.model

import java.util.Date

data class Todo(
    val id: Int,
    val title: String,
    val createdAt: Date,
    val isUpdated: Boolean
)
