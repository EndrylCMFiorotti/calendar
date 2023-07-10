package com.example.calendar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.calendar.databinding.ActivityMainBinding
import com.squareup.timessquare.CalendarCellDecorator
import com.squareup.timessquare.CalendarPickerView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dates = mutableMapOf<Date, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEventOnDate("2023-07-10", R.color.red)
        setEventOnDate("2023-07-12", R.color.red)
        setEventOnDate("2023-07-23", R.color.red)
        setEventOnDate("2024-06-22", R.color.red)

        setEventOnDate("2023-07-30", R.color.dark_pink)
        setEventOnDate("2023-07-20", R.color.dark_pink)
        setEventOnDate("2023-10-20", R.color.dark_pink)
    }

    private fun setEventOnDate(date: String, color: Int) {
        dates[SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)!!] =
            ContextCompat.getColor(this, color)
        reloadDatesList()

        // RED - Cancelled day
        // DARK PINK - Scheduled day
    }

    private fun reloadDatesList() {
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)
        val decorators = mutableListOf<CalendarCellDecorator>()
        decorators.add(CustomCalendarDecorator(dates))

        with(binding) {
            cpvCalendar.decorators = decorators
            cpvCalendar.init(Date(), nextYear.time).withSelectedDate(Date())
        }

        clickToDaySetup()
    }

    private fun clickToDaySetup() {
        binding.cpvCalendar.setOnDateSelectedListener(
            object : CalendarPickerView.OnDateSelectedListener {
                override fun onDateSelected(date: Date) {
                    val selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date)
                    Toast.makeText(baseContext, selectedDate, Toast.LENGTH_SHORT).show()
                }

                override fun onDateUnselected(date: Date?) {
                    // Action to take when a date is cleared
                }
            }
        )
    }
}