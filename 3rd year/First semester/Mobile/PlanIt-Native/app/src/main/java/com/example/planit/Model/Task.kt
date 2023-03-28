package com.example.planit.Model

import android.widget.CheckBox
import java.util.Date

data class Task(
    var name: String,
    var info: String,
    var duration: Double,
    var isDone: Boolean

)