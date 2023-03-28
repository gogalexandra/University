package com.example.planit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.planit.Model.Task
import com.example.planit.Repository.TaskRepo


class AddTask : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taskdialog)

        val delete = findViewById<Button>(R.id.taskDeleteBtn)
        delete.isVisible = false

        val confirmButton = findViewById<Button>(R.id.taskConfirmationButton)
        confirmButton.setOnClickListener{
            val taskName = findViewById<TextView>(R.id.taskNameView)?.text.toString()
            val taskInfo = findViewById<TextView>(R.id.taskInfoView)?.text.toString()
            val taskDuration = findViewById<TextView>(R.id.taskDurationView)?.text.toString()
            if (taskName != "" && taskInfo != ""
                && taskDuration != ""){
                val task = Task(taskName, taskInfo, taskDuration.toDouble(), false)
                TaskRepo.add(task)

                val intent = Intent(this@AddTask, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                    this,
                    "Added successfully ",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{ Toast.makeText(
                this,
                "Make sure every field is completed",
                Toast.LENGTH_SHORT
            ).show()}
        }
    }
}