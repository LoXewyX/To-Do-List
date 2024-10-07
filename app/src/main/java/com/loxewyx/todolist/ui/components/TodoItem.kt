package com.loxewyx.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loxewyx.todolist.R
import com.loxewyx.todolist.data.model.Todo

@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit, onUpdate: (String) -> Unit) {
    var isEditing by remember { mutableStateOf(false) }
    var editedText by remember { mutableStateOf(item.title) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = if (item.isUpdated) "${item.createdAt} (Updated)"
                else item.createdAt.toString(),
                fontSize = 10.sp,
                color = Color.LightGray
            )
            if (isEditing) {
                OutlinedTextField(
                    value = editedText,
                    onValueChange = { editedText = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        onUpdate(editedText)
                        isEditing = false
                    }
                ) {
                    Text("Save")
                }
            } else {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }

        IconButton(onClick = { isEditing = !isEditing }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_edit_24),
                contentDescription = "Edit",
                tint = Color.White
            )
        }

        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_close_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}