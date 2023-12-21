package com.example.dbtest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val details: String? = null,
    @ColumnInfo val date: String,
    @ColumnInfo val due: String,
    @ColumnInfo val oneHourBefore: Boolean
)

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val type: String,
    @ColumnInfo val code: String? = null,
    @ColumnInfo val room: String? = null,
    @ColumnInfo val start: String,
    @ColumnInfo val end: String,
    @ColumnInfo val mon: Boolean,
    @ColumnInfo val tue: Boolean,
    @ColumnInfo val wed: Boolean,
    @ColumnInfo val thu: Boolean,
    @ColumnInfo val fri: Boolean,
)

@Entity
data class Building(
    @PrimaryKey(autoGenerate = false) val abb: String,
    @ColumnInfo val address: String
)

