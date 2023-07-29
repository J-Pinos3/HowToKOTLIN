package com.example.holamundo.todoapp

sealed class TasksCategory(var isSeleted: Boolean = true) {

    object Personal: TasksCategory()
    object Business: TasksCategory()
    object Other: TasksCategory()

}