package com.happiestminds.recyclerviewdemo

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(val dataSet: MutableList<Student>, val action: (Student, Int) -> Unit) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.nameT)
        val rollTextView = itemView.findViewById<TextView>(R.id.rollT)
        val marksTextView = itemView.findViewById<TextView>(R.id.marksT)
    }

    //called by recyclerView when new views are to be created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        //Inflate the layout, Create view holder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        Log.d("StudentAdapter", "onCreateViewHolder")
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        Log.d("StudentAdapter", "onBindViewHolder called $position")
        val std = dataSet[position]
        holder.nameTextView.text = std.name
        holder.rollTextView.text = "Roll No: ${std.rollNo}"
        holder.marksTextView.text = "Marks: ${std.marks}"

        holder.itemView.setOnClickListener {
            action(std, holder.adapterPosition)

        }
        val cardView = holder.itemView as CardView
        when (std.marks) {
            in 75..100 -> {

                cardView.setCardBackgroundColor(Color.GREEN)
            }
            in 65..75 -> {
                cardView.setCardBackgroundColor(Color.BLUE)
            }
            in 55..65 -> cardView.setCardBackgroundColor(Color.YELLOW)
            in 35..55 -> cardView.setCardBackgroundColor(Color.DKGRAY)
            else -> cardView.setCardBackgroundColor(Color.RED)
        }
    }

    override fun getItemCount() = dataSet.size //single expression functions

}