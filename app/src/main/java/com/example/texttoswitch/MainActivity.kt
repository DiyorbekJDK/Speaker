package com.example.texttoswitch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.TimePicker
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Calendar()
        toNextActivity()
        actionBar?.hide()

    }

    private fun toNextActivity() {
        val btnNextActivity: Button = findViewById(R.id.btnNextActivity)
        btnNextActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }


    private fun Calendar() {
        val calendarView: CalendarView = findViewById(R.id.calendraView)
        val timePickerView: TimePicker = findViewById(R.id.timePicker)
        val calendarViewTextView: TextView = findViewById(R.id.textCalendar)
        val timePickerTextView: TextView = findViewById(R.id.textTimePicker)

        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            calendarViewTextView.text = "$day/$month/$year"
        }
        timePickerView.setOnTimeChangedListener { timePicker, minute, hour ->
            timePickerTextView.text = "$minute:$hour"
        }
    }
}