package com.example.planit

import android.annotation.SuppressLint
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
import java.text.SimpleDateFormat
import java.util.*

class ModifyActivity : AppCompatActivity() {
    lateinit var nameView: TextView
    lateinit var dateView : TextView
    lateinit var infoView : TextView
    lateinit var startView: TextView
    lateinit var endView : TextView

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitydialog)

        val deleteBtn = findViewById<Button>(R.id.deleteBtn)
        deleteBtn.isVisible = true

        nameView = findViewById<TextView>(R.id.actvNameView)
        dateView = findViewById<TextView>(R.id.actvDateView)
        infoView = findViewById<TextView>(R.id.actvInfoView)
        startView = findViewById<TextView>(R.id.actvStartView)
        endView = findViewById<TextView>(R.id.actvEndView)

        val bundle :Bundle ?=intent.extras
        val message = bundle!!.getString("id") // 1
        val selectedActivity = message?.let { ActivityRepo.getActivitybyName(it) }

        val formatter = SimpleDateFormat("dd/MM/yyyy")

        if (selectedActivity != null) {
            nameView.text = selectedActivity.name
            dateView.text = formatter.format(selectedActivity.date)
            infoView.text = selectedActivity.info
            startView.text = selectedActivity.startTime.toString()
            endView.text = selectedActivity.endTime.toString()
        }

        val confirmButton = findViewById<Button>(R.id.confirmBtn)
        confirmButton.setOnClickListener{
            val new_activityName = nameView.text.toString()
            val new_activityDate = Date(dateView.text.toString())
            val new_activityInfo = infoView.text.toString()
            val new_activityStart = startView.text.toString().toDouble()
            val new_activityEnd = endView.text.toString().toDouble()

            val new_activity = Activity(new_activityName, new_activityDate, new_activityInfo, new_activityStart,
            new_activityEnd)
            nameView.isEnabled = true
            ActivityRepo.update(selectedActivity?.name, new_activity)
            Toast.makeText(
                this,
                "Updated successfully",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this@ModifyActivity, MainActivity::class.java).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra("msg", "done")

            }
            startActivity(intent)
        }

        deleteBtn.setOnClickListener{
            if (selectedActivity != null) {
                ActivityRepo.delete(selectedActivity.name)
            }
            Toast.makeText(
                this,
                "Deleted successfully",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this@ModifyActivity, MainActivity::class.java).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra("msg", "done")

            }
            startActivity(intent)
        }

    }
}