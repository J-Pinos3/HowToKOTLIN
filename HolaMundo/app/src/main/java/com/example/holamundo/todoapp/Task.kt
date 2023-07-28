package com.example.holamundo.todoapp

data class Task (val name:String, val category: TasksCategory, var isSelected:Boolean = false)
