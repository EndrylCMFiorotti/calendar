package com.example.calendar

import android.graphics.Color
import com.squareup.timessquare.CalendarCellDecorator
import com.squareup.timessquare.CalendarCellView
import java.util.Date

class CustomCalendarDecorator(private val dateColors: Map<Date, Int>) : CalendarCellDecorator {

    override fun decorate(cellView: CalendarCellView, date: Date) {
        if (dateColors.containsKey(date)) {
            val color = dateColors[date] ?: Color.RED
            cellView.setBackgroundColor(color)
            cellView.isClickable = false
        } else {
            cellView.setBackgroundColor(Color.TRANSPARENT)
        }
    }
}