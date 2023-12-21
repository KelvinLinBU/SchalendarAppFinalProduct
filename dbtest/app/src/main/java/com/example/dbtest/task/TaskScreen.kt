package com.example.dbtest.task
import android.content.Context
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbtest.R
import com.example.dbtest.TaskApplication
import com.example.dbtest.TaskViewModel
import com.example.dbtest.TaskViewModelFactory
import com.example.dbtest.course.TopBar
import com.example.dbtest.database.Task
import com.example.dbtest.ui.theme.BasicsCodelabTheme
import com.example.dbtest.ui.theme.Purple80
import java.time.LocalDate
import java.time.LocalTime

fun delTask(taskViewModel: TaskViewModel,task: Task){
    taskViewModel.delTask(task)
}
@Composable
fun EmptyTasksImage() {
    Image(
        painter = painterResource(id = R.drawable.empty_tasks),
        contentDescription = "Empty Task"
    )
}
@Composable
fun NoTasksScreen(taskViewModel: TaskViewModel) {
    val name = taskViewModel.name.collectAsState().value
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val color = colorResource(id = R.color.grey)
            var text = stringResource(id = R.string.task_noTask)
            if(name=="Null"){
                text = stringResource(id = R.string.need_login)
            }
            EmptyTasksImage()
            Text(text = text, color = color, fontSize = 36.sp, fontWeight = FontWeight.Bold)
        }

    }
}

@Composable
fun CardContent(taskViewModel: TaskViewModel,task:Task) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = task.title, style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = task.date.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    val due = stringResource(R.string.task_due) + " " + task.due
                    Text(
                        text = due,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            if (expanded) {
                var details = task.details
                if(details == null){
                    details = stringResource(id = R.string.task_noDetail)
                }
                Column {
                    Text(
                        text = details,
                        style = MaterialTheme.typography.bodyLarge)
                    Button(
                        modifier = Modifier.padding(vertical = 30.dp),
                        onClick = {
                            delTask(taskViewModel,task)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        val del = stringResource(id = R.string.task_del)
                        Text(del)
                    }

                }
            }
            
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Composable
fun CradFinish(taskViewModel: TaskViewModel,task: Task) {
    BasicsCodelabTheme {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            CardContent(taskViewModel,task)
        }
    }
}

@Composable
private fun TasksList(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    tasks: List<Task>
) {
    val name = taskViewModel.name.collectAsState().value
    if(tasks.isEmpty()||name == "Null"){
        NoTasksScreen(taskViewModel)
    }else {
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            items(items = tasks) { task ->
                CradFinish(taskViewModel,task)
            }
        }
    }
}


@Composable
fun TaskApp(taskViewModel: TaskViewModel, modifier: Modifier = Modifier,onAddChange:()->Unit) {
    BasicsCodelabTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            var tasks = taskViewModel.allTasks.collectAsState(initial = emptyList()).value
            TasksList(modifier, taskViewModel, tasks)
            FloatingActionButton(
                onClick = onAddChange,
                modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 90.dp,end =10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    }
}
@Composable
fun TaskPage(context: Context,taskViewModel: TaskViewModel, modifier: Modifier) {
    var shouldShowAdd by rememberSaveable { mutableStateOf(false) }
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowAdd) {
            AddTaskPage(context, taskViewModel, modifier)
        }else{
            TaskApp(taskViewModel,modifier,onAddChange ={shouldShowAdd = true})
        }


    }
}


