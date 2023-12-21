package com.example.dbtest

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.dbtest.database.Building
import com.example.dbtest.database.Course
import com.example.dbtest.database.Task
import com.example.dbtest.database.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository): ViewModel() {
    private val _currentPage = MutableStateFlow("course")
    val currentPage: StateFlow<String> = _currentPage
    fun setCurrentPage(page: String) {
        _currentPage.value = page
    }
    // weather data
    private val _roundedFahrenheitTemp = MutableStateFlow("")
    val roundedFahrenheitTemp: StateFlow<String> = _roundedFahrenheitTemp
    private val _windspeed = MutableStateFlow("")
    val windspeed: StateFlow<String> = _windspeed
    private val _weatherdescription = MutableStateFlow("")
    val weatherdescription: StateFlow<String> = _weatherdescription
    private val _recom = MutableStateFlow("")
    val recom: StateFlow<String> = _recom
    fun SetroundedFahrenheitTemp(data: String) {
        _roundedFahrenheitTemp.value = data
    }
    fun Setwindspeed(data: String) {
        _windspeed.value = data
    }
    fun Setweatherdescription(data: String) {
        _weatherdescription.value = data
    }
    fun Setrecom(data: String) {
        _recom.value = data
    }


    val allTasks: Flow<List<Task>> = repository.allTasks
    val allMC: Flow<List<Course>> = repository.allMCourse
    val allTuC: Flow<List<Course>> = repository.allTuCourse
    val allWC: Flow<List<Course>> = repository.allWCourse
    val allThC: Flow<List<Course>> = repository.allThCourse
    val allFC: Flow<List<Course>> = repository.allFCourse
    val allBuildings: Flow<List<String>> = repository.allBuildings

    fun addTask(task: Task) = viewModelScope.launch {
        repository.addTask(task)
    }
    fun delTask(task: Task) = viewModelScope.launch {
        repository.delTask(task)
    }
    fun addCourse(course: Course) = viewModelScope.launch {
        repository.addCourse(course)
    }
    fun delCourse(course: Course) = viewModelScope.launch {
        repository.delCourse(course)
    }
    fun getAddress(abb: String):Flow<String> = repository.getAddress(abb)

    //suspend fun getAddress(abb: String): Building = repository.getAddress(abb)
    //fun addBuilding(building: Building) = viewModelScope.launch {
    //    repository.addBuilding(building)
    //}
}

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}