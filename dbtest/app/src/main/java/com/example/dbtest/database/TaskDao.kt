package com.example.dbtest.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY date,due DESC")
    fun getTasks(): Flow<List<Task>>
    @Insert
    suspend fun addTask(task: Task)

    @Delete
    suspend fun delTask(task: Task)

}

@Dao
interface CourseDao{
    @Query("SELECT * FROM course WHERE mon = true ORDER BY start ASC")
    fun getCourseM(): Flow<List<Course>>
    @Query("SELECT * FROM course WHERE tue = true ORDER BY start ASC")
    fun getCourseTu(): Flow<List<Course>>
    @Query("SELECT * FROM course WHERE wed = true ORDER BY start ASC")
    fun getCourseW(): Flow<List<Course>>
    @Query("SELECT * FROM course WHERE thu = true ORDER BY start ASC")
    fun getCourseTh(): Flow<List<Course>>
    @Query("SELECT * FROM course WHERE fri = true ORDER BY start ASC")
    fun getCourseF(): Flow<List<Course>>
    @Insert
    suspend fun addCourse(course: Course)
    @Delete
    suspend fun delCourse(course: Course)
}

@Dao
interface BuildingDao{
    @Query("SELECT abb FROM building ORDER BY abb ASC")
    fun getBuildings(): Flow<List<String>>

    @Query("SELECT address FROM building WHERE abb=(:abb)")
    fun getAddress(abb: String): Flow<String>

    @Insert
    suspend fun addBuilding(building: Building)
}