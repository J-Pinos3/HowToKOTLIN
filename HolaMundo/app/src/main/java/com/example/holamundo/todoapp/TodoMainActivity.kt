package com.example.holamundo.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.holamundo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoMainActivity : AppCompatActivity() {

    private var categories = listOf(
        TasksCategory.Business,
        TasksCategory.Personal,
        TasksCategory.Other
    //para no tener que repetir TasksCategory., debo importar esa clase
    )

    private var tasks = mutableListOf(

        Task("Pruebas Business", TasksCategory.Business),
        Task("Pruebas Personal", TasksCategory.Personal),
        Task("Pruebas Ohter", TasksCategory.Other)

    )

    private lateinit var rec_viewCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rec_viewTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_main)

        initComponents()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog()  }

    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {

            val currentTask = etTask.text.toString()
            if(currentTask.isNotEmpty()){
                //obtengo el id el botón seleccionado
                val selectedId = rgCategories.checkedRadioButtonId

                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)

                val currentCategory: TasksCategory = when( selectedRadioButton.text ){
                    getString(R.string.dialog_category_business) -> TasksCategory.Business
                    getString(R.string.dialog_category_personal) -> TasksCategory.Personal
                    else -> TasksCategory.Other
                }

                tasks.add(Task(currentTask, currentCategory) )
                updateTasks()
                dialog.hide()
            }

        }



        dialog.show()
    }


    private fun initComponents() {
        rec_viewCategories = findViewById(R.id.rec_viewCategories)
        rec_viewTasks = findViewById(R.id.rec_viewTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }


    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories) { updateCategories(it) }
        rec_viewCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rec_viewCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks) { position -> onItemSelecter(position) }
        rec_viewTasks.layoutManager = LinearLayoutManager(this) //solo this xq es vertical por defecto
        rec_viewTasks.adapter = tasksAdapter
    }



    private fun onItemSelecter(position: Int){
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

    private fun updateCategories(position:Int){
        categories[position].isSeleted = !categories[position].isSeleted
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    private fun updateTasks(){

        val selectedCategories: List<TasksCategory> = categories.filter { it.isSeleted }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTasks
        tasksAdapter.notifyDataSetChanged()
    }



}