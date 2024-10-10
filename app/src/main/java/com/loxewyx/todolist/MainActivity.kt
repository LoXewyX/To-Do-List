package com.loxewyx.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.loxewyx.todolist.ui.pages.TodoListPage
import com.loxewyx.todolist.ui.theme.ToDoListTheme
import com.loxewyx.todolist.data.viewmodel.TodoViewModel
import com.loxewyx.todolist.domain.TodoManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TodoManager.init(applicationContext)

        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        enableEdgeToEdge()

        setContent {
            ToDoListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoListPage(todoViewModel)
                }
            }
        }
    }
}
