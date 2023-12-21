package com.example.dbtest


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dbtest.course.AddCourse
import com.example.dbtest.course.CourseApp
import com.example.dbtest.course.CoursePage
import com.example.dbtest.database.Building
import com.example.dbtest.database.ProfilePage
import com.example.dbtest.database.Task
import com.example.dbtest.database.TaskRepository
import com.example.dbtest.task.Addtask
import com.example.dbtest.task.TaskApp
import com.example.dbtest.task.TaskPage
import com.example.dbtest.task.showDatePicker
import com.example.dbtest.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    private val taskViewModel: TaskViewModel by viewModels() {
        TaskViewModelFactory((application as TaskApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme{
                MyScreen(this,taskViewModel)
                //TaskPage(this,taskViewModel, modifier=Modifier)
                //CoursePage(this,taskViewModel, modifier=Modifier)
            }
        }
    }
}
/*
fun initBuilding(taskViewModel: TaskViewModel){
    var b0 = Building("AAS","138 Mountfort Street")
    var b1 = Building("AGG","925 Commonwealth Avenue")
    var b2 = Building("ASC","300 Babcock Street")
    var b3 = Building("BRB","5 Cummington Mall")
    var b4 = Building("BSC","2 Cummington Mall")
    var b5 = Building("CAS","685 Commonwealth Avenue")
    var b6 = Building("CDS","665 Commonwealth Avenue")
    var b7 = Building("CFA","855 Commonwealth Avenue")
    var b8 = Building("CGS","871 Commonwealth Avenue")
    var b9 = Building("CLN","900 Commonwealth Avenue")
    var b10 = Building("CNS","677 Beacon Street")
    var b11 = Building("COM","640 Commonwealth Avenue")
    var b12 = Building("CRW","619 Memorial Dr, Cambridge, MA 02139")
    var b13 = Building("CSE","285 Babcock Street")
    var b14 = Building("EGL","236 Bay State Road")
    var b15 = Building("EIB","143 Bay State Road")
    var b16 = Building("EIL","285 Babcock Street")
    var b17 = Building("EMA","730 Commonwealth Ave, Boston, MA 02215")
    var b18 = Building("EMB","15 St. Mary’s Street")
    var b19 = Building("ENG","112 Cummington Mall")
    var b20 = Building("EOP","890 Commonwealth Avenue")
    var b21 = Building("EPC","750 Commonwealth Avenue, Boston, MA 02215")
    var b22 = Building("ERA","48 Cummington Mall")
    var b23 = Building("ERB","44 Cummington Mall")
    var b24 = Building("FAB","180 Riverway, Boston, MA 02215")
    var b25 = Building("FCB","25 Pilgrim Road")
    var b26 = Building("FCC","150 Riverway")
    var b27 = Building("FLR","808 Commonwealth Avenue")
    var b28 = Building("FOB","704 Commonwealth Avenue")
    var b29 = Building("FRC","915 Commonwealth Avenue")
    var b30 = Building("GDP","53 Bay State Road")
    var b31 = Building("GMS","72 East Concord Street")
    var b32 = Building("GRS","705 Commonwealth Avenue")
    var b33 = Building("GSU","775 Commonwealth Avenue, Boston, MA 02215")
    var b34 = Building("HAR","595 Commonwealth Avenue")
    var b35 = Building("HAW","43 Hawes Street, Brookline")
    var b36 = Building("HIS","226 Bay State Road")
    var b37 = Building("IEC","888 Commonwealth Avenue")
    var b38 = Building("IRB","154 Bay State Road")
    var b39 = Building("","")
    var b40 = Building("","")
    var b41 = Building("","")
    var b42 = Building("","")
    var b43 = Building("","")
    var b44 = Building("","")
    var b45 = Building("","")
    var b46 = Building("","")
    var b47 = Building("","")
    var b48 = Building("","")
    var b49 = Building("","")
    var b50 = Building("","")
    var b51 = Building("","")
    var b52 = Building("","")
    var b53 = Building("","")
    var b54 = Building("","")
    var b55 = Building("","")
    var b56 = Building("","")
    var b57 = Building("","")
    var b58 = Building("","")
    var b59 = Building("","")
    var b60 = Building("","")
    var b61 = Building("","")
    var b62 = Building("","")
    var b63 = Building("","")
    var b64 = Building("","")
    var b65 = Building("","")
    var b66 = Building("","")
    var b67 = Building("","")
    var b68 = Building("","")
    var b69 = Building("","")
    var b70 = Building("","")
    var b71 = Building("","")
    var b72 = Building("","")
    var b73 = Building("","")
    var b74 = Building("","")
    var b75 = Building("","")
    var b76 = Building("","")
    var b77 = Building("","")
    var b78 = Building("","")
}
*/
// bar set
@Composable
private fun BottomNavigation(selectedPage: String, onNavigation: (String) -> Unit) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = selectedPage == "course",
            onClick = {onNavigation("course")}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_task)
                )
            },
            selected = selectedPage == "task",
            onClick = {onNavigation("task")}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = selectedPage == "profile",
            onClick = {onNavigation("profile")}
        )
    }
}

@Composable
fun MyScreen(context: Context,taskViewModel: TaskViewModel) {
    val currentPage by taskViewModel.currentPage.collectAsState()
    Scaffold(
        bottomBar = { BottomNavigation(currentPage, onNavigation = { page ->
            taskViewModel.setCurrentPage(page)
        }) }
    ) { innerPadding ->
        when (currentPage) {
            "course" -> CoursePage(context,taskViewModel, modifier=Modifier)
            "task" -> TaskPage(context,taskViewModel, modifier=Modifier)
            "profile" -> ProfilePage(context, taskViewModel, modifier=Modifier)
            else -> CoursePage(context,taskViewModel, modifier=Modifier)
        }
    }
}