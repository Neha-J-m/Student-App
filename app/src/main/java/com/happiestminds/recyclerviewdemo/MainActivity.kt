package com.happiestminds.recyclerviewdemo

import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rView: RecyclerView
    val listOfStudents = mutableListOf<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupData()

        rView = findViewById(R.id.RView)

        //layout Manager - positioning of views
        rView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        rView.layoutManager = GridLayoutManager(this,2)

        //adapter - data binding
        rView.adapter = StudentAdapter(listOfStudents) { std, pos ->
            //show popup
            val vHolder = rView.findViewHolderForAdapterPosition(pos)
            val view = rView.findContainingItemView(vHolder?.itemView!!)
            val pMenu = PopupMenu(this, view)
            pMenu.menu.add("Edit")
            pMenu.menu.add("Delete")
            pMenu.show()

            pMenu.setOnMenuItemClickListener {
                when (it.title) {
                    "Delete" -> {
                        deleteStudent(std, pos)
                        true
                    }
                    "Edit" -> {
                        editStudent(std, pos)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun editStudent(std: Student, pos: Int) {
        val upperName = std.name.uppercase()
        listOfStudents[pos].name = upperName
        rView.adapter?.notifyItemChanged(pos)
    }


    private fun setupData() {
        listOfStudents.add(Student(1, "Ana", 95))
        listOfStudents.add(Student(2, "John", 45))
        listOfStudents.add(Student(3, "Smith", 98))
        listOfStudents.add(Student(4, "Jake", 67))
        listOfStudents.add(Student(5, "Shawn", 67))
        listOfStudents.add(Student(6, "Ali", 82))
        listOfStudents.add(Student(7, "Belle", 60))
        listOfStudents.add(Student(8, "Liam", 70))
        listOfStudents.add(Student(9, "Analise", 55))
        listOfStudents.add(Student(10, "Alice", 9))
        listOfStudents.add(Student(11, "Bruno", 30))
    }

    private fun deleteStudent(std: Student, position: Int) {
        listOfStudents.remove(std)
        Log.d("MainActivity", "Student ${std.name} removed at $position")
        rView.adapter?.notifyItemRemoved(position)
    }
}