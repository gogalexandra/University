package com.example.planit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.planit.Model.Task
import com.example.planit.R.*
import com.example.planit.Repository.TaskRepo

class ModifyTask : AppCompatActivity() {
    lateinit var nameView: TextView
    lateinit var infoView : TextView
    lateinit var durationView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.taskdialog)
        val deleteBtn = findViewById<Button>(id.taskDeleteBtn)
        deleteBtn.isVisible = true

        nameView = findViewById(id.taskNameView)
        infoView = findViewById(id.taskInfoView)
        durationView = findViewById(id.taskDurationView)

        val bundle :Bundle ?=intent.extras
        val message = bundle!!.getString("id") // 1
        val selectedTask = message?.let { TaskRepo.getTaskbyName(it) }

        if (selectedTask != null) {
            nameView.text = selectedTask.name
            infoView.text = selectedTask.info
            durationView.text = selectedTask.duration.toString()
        }

        val confirmButton = findViewById<Button>(id.taskConfirmationButton)
        confirmButton.setOnClickListener{
            val new_taskName = nameView.text.toString()
            val new_taskInfo = infoView.text.toString()
            val new_taskDuration = durationView.text.toString()

            val new_task = selectedTask?.isDone?.let { it1 ->
                Task(new_taskName, new_taskInfo, new_taskDuration.toDouble(),
                    it1
                )
            }
            nameView.isEnabled = true
            TaskRepo.update(selectedTask?.name, new_task)
            Toast.makeText(
                this,
                "Updated successfully",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this@ModifyTask, MainActivity::class.java).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra("msg", "done")

            }
            startActivity(intent)
        }

        deleteBtn.setOnClickListener{
            if (selectedTask != null) {
                TaskRepo.delete(selectedTask.name)
            }
            Toast.makeText(
                this,
                "Deleted successfully",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this@ModifyTask, MainActivity::class.java).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra("msg", "done")

            }
            startActivity(intent)
        }



    }
}