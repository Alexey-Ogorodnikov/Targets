package com.alexey.targets2.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexey.targets2.R
import com.alexey.targets2.data.model.Priority

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(
    onDismiss: () -> Unit,
    onTaskAdded: (title: String, description: String, priority: Priority) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(Priority.MEDIUM) }
    var isTitleError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.add_new_target)) },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { 
                        title = it
                        isTitleError = false
                    },
                    label = { Text(stringResource(R.string.target_title)) },
                    isError = isTitleError,
                    supportingText = if (isTitleError) {
                        { Text(stringResource(R.string.title_required)) }
                    } else null,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(stringResource(R.string.target_description_optional)) },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )

                Column {
                    Text(
                        text = stringResource(R.string.target_priority),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Priority.values().forEach { priorityOption ->
                            FilterChip(
                                selected = priority == priorityOption,
                                onClick = { priority = priorityOption },
                                label = { 
                                    Text(
                                        when (priorityOption) {
                                            Priority.LOW -> stringResource(R.string.priority_low)
                                            Priority.MEDIUM -> stringResource(R.string.priority_medium)
                                            Priority.HIGH -> stringResource(R.string.priority_high)
                                        }
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = when (priorityOption) {
                                        Priority.LOW -> MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                        Priority.MEDIUM -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                                        Priority.HIGH -> MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
                                    }
                                )
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isBlank()) {
                        isTitleError = true
                        return@Button
                    }
                    onTaskAdded(title.trim(), description.trim(), priority)
                }
            ) {
                Text(stringResource(R.string.add_target_button))
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
} 