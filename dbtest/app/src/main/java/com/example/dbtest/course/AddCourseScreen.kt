package com.example.dbtest.course

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dbtest.R
import com.example.dbtest.TaskViewModel
import com.example.dbtest.database.Course
import com.example.dbtest.database.Task
import com.example.dbtest.task.Addtask
import com.example.dbtest.task.MyTextInput
import com.example.dbtest.task.TaskApp
import com.example.dbtest.task.addTask
import com.example.dbtest.task.showDatePicker
import com.example.dbtest.task.showTimePicker
import com.example.dbtest.ui.theme.BasicsCodelabTheme
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar
import java.util.Date
// DropdownMenu used to get the course type and building code
@Composable
fun DropdownMenu(options:List<String>, selectedOption: MutableState<String>) {
    Box(modifier = Modifier){
        var expanded by remember { mutableStateOf(false) }
        Button(onClick = { expanded = true }) {
            Text(selectedOption.value)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    {Text(text = option)},
                    onClick = {
                        selectedOption.value = option
                        expanded = false
                    })
            }
        }

    }
}

// MultiSelectCheckBox for the frequency
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiSelectCheckBox(options:List<String>,checkedState: SnapshotStateMap<String, Boolean>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
    ) {
        Text("Select Frequency:")

        FlowRow(
            modifier = Modifier.padding(1.dp)
        ) {
            options.forEach { option ->
                Row(modifier = Modifier.padding(1.dp), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState[option] ?: false,
                        onCheckedChange = { checked ->
                            checkedState[option] = checked
                        }
                    )
                    Text(text = option)
                }
            }
        }
    }
}
// This one assembled all the course input and insert the new course after click the add button
@Composable
fun AddCourse(
    context: Context,
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier,
    onCourseChange: () -> Unit
) {
    var title by remember { mutableStateOf("")}
    var type = remember { mutableStateOf(context.getString(R.string.Lecture)) }
    var code = remember { mutableStateOf("Select your room") }
    var room by remember { mutableStateOf("") }
    var start = remember { mutableStateOf("") }
    var end = remember { mutableStateOf("") }
    val options0 = listOf(
        stringResource(id = R.string.Lecture),
        stringResource(id = R.string.Independent),
        stringResource(id = R.string.Lab),
        stringResource(id = R.string.Dis),
        stringResource(id = R.string.Meeting)
    )
    val options1 = taskViewModel.allBuildings.collectAsState(initial = emptyList()).value
    val options = listOf(
        stringResource(id = R.string.Monday),
        stringResource(id = R.string.Tuesday),
        stringResource(id = R.string.Wednesday),
        stringResource(id = R.string.Thursday),
        stringResource(id = R.string.Friday)
    )
    val checkedState = remember { mutableStateMapOf<String, Boolean>().apply {
        options.forEach { this[it] = false }
    }}
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyTextInput("Title", title) { title = it }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            DropdownMenu(options0,type)
            DropdownMenu(options1,code)
        }
        MyTextInput("Room", room) { room = it }
        showTimePicker(context, start,stringResource(id = R.string.course_select_start))
        showTimePicker(context, end,stringResource(id = R.string.course_select_end))
        MultiSelectCheckBox(options,checkedState)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    if (title ==""){
                        val warn = context.getString(R.string.task_noTitle)
                        Toast.makeText(context,warn,Toast.LENGTH_SHORT).show()}
                    else if( type.value == "Select your room"){
                        val warn = context.getString(R.string.course_noType)
                        Toast.makeText(context,warn,Toast.LENGTH_SHORT).show()}
                    else if(start.value ==""||end.value ==""){
                        val warn = context.getString(R.string.course_noStart)
                        Toast.makeText(context,warn,Toast.LENGTH_SHORT).show()}
                    else {
                        var mon = checkedState[context.getString(R.string.Monday)]
                        Log.d("Mon", "$mon")
                        var tue = checkedState[context.getString(R.string.Tuesday)]
                        var wed = checkedState[context.getString(R.string.Wednesday)]
                        var thu = checkedState[context.getString(R.string.Thursday)]
                        var fri = checkedState[context.getString(R.string.Friday)]
                        if(mon == false && tue == false && wed == false && thu == false && fri == false  ){

                        }else{
                            addCourse(context, taskViewModel, title, type.value,code.value,room,start.value,end.value,mon!!,tue!!,wed!!,thu!!,fri!!)
                            val text = context.getString(R.string.task_add) + " " + title
                            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            ) {
                val add = stringResource(id = R.string.task_add)
                Text(add)
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = onCourseChange
            ) {
                val back = stringResource(id = R.string.course_add_back)
                Text(back)
            }
        }

            }
        }
// The function to add the course
fun addCourse(context: Context,taskViewModel: TaskViewModel,title: String, type: String, code: String, room: String,
               start: String,end:String,mon: Boolean,tue: Boolean, wed:Boolean, thu: Boolean, fri: Boolean) {
    Log.d("course", "reach here")
    val course = Course(0,title,type,code,room,start,end,mon,tue,wed,thu,fri)
    Log.d("course", "$course")
    taskViewModel.addCourse(course)
}
// This is the final one for thr AddCourse Screen, combine the addcourse function and also return to course function
@Composable
fun AddCoursePage(context: Context,
                taskViewModel: TaskViewModel,
                modifier: Modifier = Modifier){
    var shouldShowStackList by rememberSaveable { mutableStateOf(false) }
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (!shouldShowStackList) {
            AddCourse(context = context, taskViewModel = taskViewModel, onCourseChange = { shouldShowStackList = true })
        } else {
            CoursePage(context,taskViewModel,modifier)
        }
    }

}





