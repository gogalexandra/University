package com.example.planit.Model

import java.util.Date

data class Activity(
    var name: String,
    var date: Date,
    var info: String,
    var startTime: Double,
    var endTime: Double
)