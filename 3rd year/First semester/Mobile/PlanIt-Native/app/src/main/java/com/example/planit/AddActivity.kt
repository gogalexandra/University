package com.example.planit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.planit.Model.Activity
import com.example.planit.Model.Task
import com.example.planit.Repository.ActivityRepo
import com.example.planit.Repository.TaskRepo
import java.util.Date

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitydialog)

        val delete = findViewById<Button>(R.id.deleteBtn)
        delete.isVisible = false

        val confirmButton = findViewById<Button>(R.id.confirmBtn)
        confirmButton.setOnClickListener{
            val activityName = findViewById<TextView>(R.id.actvNameView)?.text.toString()
            val activityDate = findViewById<TextView>(R.id.actvDateView)?.text.toString()
            val activityInfo = findViewById<TextView>(R.id.actvInfoView)?.text.toString()
            val activityStart = findViewById<TextView>(R.id.actvStartView)?.text.toString()
            val activityEnd = findViewById<TextView>(R.id.actvEndView)?.text.toString()

            if (activityName != "" && activityInfo != ""
                && activityStart != "" && activityDate != "" && activityEnd != ""){
                val toDate = Date(activityDate)
                val activity = Activity(activityName, toDate, activityInfo, activityStart.toDouble(), activityEnd.toDouble())
                ActivityRepo.add(activity)

                val intent = Intent(this@AddActivity, MainActivity::class.java)
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