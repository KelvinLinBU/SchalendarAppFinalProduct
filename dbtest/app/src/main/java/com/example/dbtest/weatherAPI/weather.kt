package com.example.dbtest.weatherAPI

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.dbtest.R
import com.example.dbtest.TaskViewModel
import com.example.dbtest.course.AddCoursePage
import com.example.dbtest.course.CourseApp
import com.example.dbtest.course.CoursePage
import com.example.dbtest.ui.theme.BasicsCodelabTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// code for showing weather information from the API
@Composable
fun weatherShow(taskViewModel: TaskViewModel,onChange:()->Unit){
    BasicsCodelabTheme {
        val tem = taskViewModel.roundedFahrenheitTemp.collectAsState().value+"°F"
        Column(Modifier.fillMaxSize(),
            verticalArrangement= Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = tem,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold))
            Text(text = taskViewModel.windspeed.collectAsState().value,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold))
            Text(text = taskViewModel.weatherdescription.collectAsState().value,
                style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold))
            Text(text = taskViewModel.recom.collectAsState().value,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold))
            Button(onClick = onChange) {
                Text(stringResource(id = R.string.course_add_back))
            }
        }
    }
}
@Composable
fun weatherSet(context:Context,modifier: Modifier,taskViewModel: TaskViewModel){
    var shouldBackCourse by rememberSaveable { mutableStateOf(false) }
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldBackCourse) {
            CoursePage(context,taskViewModel,modifier)
        }else{
            weatherShow(taskViewModel,onChange ={shouldBackCourse = true})
        }


    }
}
