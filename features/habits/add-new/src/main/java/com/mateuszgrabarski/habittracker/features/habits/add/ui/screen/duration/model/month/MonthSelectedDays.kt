package com.mateuszgrabarski.habittracker.features.habits.add.ui.screen.duration.model.month

import java.time.DayOfWeek

data class MonthSelectedDays(
    private val days: MutableList<MonthDay> = MonthDay.generateNotSelectedDays().toMutableList()
) {

    val selectedDays: List<MonthDay>
        get() = days

    fun updateSelectedState(day: MonthDay) {
        days.set(
            index = day.dayNumber - 1,
            element = day.reverse()
        )
    }

    fun toDataDays(): List<DayOfWeek> =
        days.filter { it.selected }.map { DayOfWeek.of(it.dayNumber) }
}
