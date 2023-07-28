package com.example.holamundo.todoapp

sealed class TasksCategory {

    object Personal: TasksCategory()
    object Business: TasksCategory()
    object Other: TasksCategory()

}