package com.alexey.targets2.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexey.targets2.R
import com.alexey.targets2.domain.model.Priority
import com.alexey.targets2.domain.model.Target
import com.alexey.targets2.presentation.state.TargetListState
import com.alexey.targets2.presentation.component.AddTargetDialog
import com.alexey.targets2.presentation.component.EditTargetDialog
import com.alexey.targets2.presentation.viewmodel.TargetViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TargetListScreen(
    viewModel: TargetViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showAddDialog by remember { mutableStateOf(false) }
    var editingTarget by remember { mutableStateOf<Target?>(null) }
    val targets = remember(state.targets) { state.targets.sortedBy { it.order }.toMutableStateList() }
    val scope = rememberCoroutineScope()

    fun moveTarget(from: Int, to: Int) {
        if (from == to || from < 0 || to < 0 || from >= targets.size || to >= targets.size) return
        val target = targets.removeAt(from)
        targets.add(to, target)
        targets.forEachIndexed { idx, t ->
            targets[idx] = t.copy(order = idx)
        }
        // Persist new order
        scope.launch {
            targets.forEachIndexed { idx, t ->
                viewModel.updateTarget(t.copy(order = idx))
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.target_manager_title)) },
                actions = {
                    IconButton(onClick = { viewModel.toggleShowCompletedTargets() }) {
                        Icon(
                            imageVector = if (state.showCompletedTargets) Icons.Default.CheckCircle else Icons.Default.Clear,
                            contentDescription = stringResource(R.string.toggle_completed_targets)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_target))
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.error != null -> {
                    ErrorContent(
                        error = state.error!!,
                        onRetry = { /* Reload targets */ },
                        onDismiss = { viewModel.clearError() }
                    )
                }
                state.targets.isEmpty() -> {
                    EmptyState(
                        showCompletedTargets = state.showCompletedTargets
                    )
                }
                else -> {
                    TargetList(
                        targets = targets,
                        onTargetClick = { /* Navigate to detail */ },
                        onTargetToggle = { viewModel.toggleTargetCompletion(it) },
                        onTargetDelete = { viewModel.deleteTarget(it) },
                        onTargetEdit = { editingTarget = it },
                        onMoveUp = { idx -> moveTarget(idx, idx - 1) },
                        onMoveDown = { idx -> moveTarget(idx, idx + 1) }
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AddTargetDialog(
            onDismiss = { showAddDialog = false },
            onTargetAdded = { title, description, priority ->
                viewModel.addTarget(title, description, priority)
                showAddDialog = false
            }
        )
    }

    editingTarget?.let { target ->
        EditTargetDialog(
            initialTitle = target.title,
            initialDescription = target.description,
            initialPriority = target.priority,
            onDismiss = { editingTarget = null },
            onTargetUpdated = { title, description, priority ->
                viewModel.updateTarget(
                    target.copy(title = title, description = description, priority = priority)
                )
                editingTarget = null
            }
        )
    }
}

@Composable
fun TargetList(
    targets: List<Target>,
    onTargetClick: (Target) -> Unit,
    onTargetToggle: (Target) -> Unit,
    onTargetDelete: (Target) -> Unit,
    onTargetEdit: (Target) -> Unit,
    onMoveUp: (Int) -> Unit,
    onMoveDown: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(targets) { index, target ->
            TargetItem(
                target = target,
                onClick = { onTargetClick(target) },
                onToggle = { onTargetToggle(target) },
                onDelete = { onTargetDelete(target) },
                onEdit = { onTargetEdit(target) },
                onMoveUp = { onMoveUp(index) },
                onMoveDown = { onMoveDown(index) },
                isFirst = index == 0,
                isLast = index == targets.lastIndex
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TargetItem(
    target: Target,
    onClick: () -> Unit,
    onToggle: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    isFirst: Boolean,
    isLast: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = target.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (target.description.isNotBlank()) {
                    Text(
                        text = target.description,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PriorityChip(priority = target.priority)
                    Text(
                        text = java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
                            .format(java.util.Date(target.createdAt)),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = onMoveUp,
                        enabled = !isFirst
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = stringResource(R.string.move_up)
                        )
                    }
                    IconButton(
                        onClick = onMoveDown,
                        enabled = !isLast
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = stringResource(R.string.move_down)
                        )
                    }
                    IconButton(onClick = onEdit) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(R.string.edit_target)
                        )
                    }
                    IconButton(onClick = onDelete) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete_target),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PriorityChip(priority: Priority) {
    val (color, text) = when (priority) {
        Priority.LOW -> MaterialTheme.colorScheme.primary to stringResource(R.string.priority_low)
        Priority.MEDIUM -> MaterialTheme.colorScheme.secondary to stringResource(R.string.priority_medium)
        Priority.HIGH -> MaterialTheme.colorScheme.error to stringResource(R.string.priority_high)
    }
    
    SuggestionChip(
        onClick = { },
        label = { Text(text) },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = color.copy(alpha = 0.1f),
            labelColor = color
        )
    )
}

@Composable
fun EmptyState(showCompletedTargets: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (showCompletedTargets) Icons.Default.CheckCircle else Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = if (showCompletedTargets) stringResource(R.string.no_completed_targets) else stringResource(R.string.no_targets_yet),
            style = MaterialTheme.typography.headlineSmall
        )
        
        Text(
            text = if (showCompletedTargets) stringResource(R.string.complete_targets_to_see_here) else stringResource(R.string.add_first_target_to_get_started),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ErrorContent(
    error: String,
    onRetry: () -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.error
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = stringResource(R.string.error_title),
            style = MaterialTheme.typography.headlineSmall
        )
        
        Text(
            text = error,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = onRetry) {
                Text(stringResource(R.string.retry))
            }
            
            OutlinedButton(onClick = onDismiss) {
                Text(stringResource(R.string.dismiss))
            }
        }
    }
} 