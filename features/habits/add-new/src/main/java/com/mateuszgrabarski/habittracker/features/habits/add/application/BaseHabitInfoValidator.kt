package com.mateuszgrabarski.habittracker.features.habits.add.application

import com.mateuszgrabarski.habittracker.business.data.models.habits.options.HabitType
import com.mateuszgrabarski.habittracker.features.habits.add.application.model.FilledHabitTypeInputs
import com.mateuszgrabarski.habittracker.features.habits.add.application.model.NumberInputs
import com.mateuszgrabarski.habittracker.features.habits.add.application.model.SelectedIcon
import com.mateuszgrabarski.habittracker.features.habits.add.application.model.TimeInputs
import com.mateuszgrabarski.habittracker.features.habits.add.const.MIN_NUMBER_OF_CHARS_IN_HABIT_NAME

class BaseHabitInfoValidator {

    fun isValid(
        selectedIcon: SelectedIcon?,
        habitName: String,
        habitType: HabitType,
        inputs: FilledHabitTypeInputs?
    ): Boolean =
        iconMustBeSelected(selectedIcon = selectedIcon) &&
            habitNameMustBeLongerThenMinNumberOfChars(habitName = habitName) &&
            typeCanNotBeNotSelected(habitType = habitType) &&
            inputsMustBeValidForType(inputs = inputs, habitType = habitType)

    private fun iconMustBeSelected(selectedIcon: SelectedIcon?) =
        selectedIcon != null

    private fun habitNameMustBeLongerThenMinNumberOfChars(habitName: String) =
        habitName.length > MIN_NUMBER_OF_CHARS_IN_HABIT_NAME

    private fun typeCanNotBeNotSelected(habitType: HabitType) =
        habitType != HabitType.getNotSelectableType()

    private fun inputsMustBeValidForType(
        inputs: FilledHabitTypeInputs?,
        habitType: HabitType
    ) = when (habitType) {
        HabitType.None,
        HabitType.YesOrNo -> inputs == null
        HabitType.Number -> validateNumberInputs(inputs)
        HabitType.Timer -> validateTimeInputs(inputs)
    }

    private fun validateNumberInputs(inputs: FilledHabitTypeInputs?) =
        if (inputs != null) {
            val (goal, unit) = (inputs as NumberInputs)
            goal > 0.0 && unit.isNotEmpty()
        } else {
            false
        }

    private fun validateTimeInputs(inputs: FilledHabitTypeInputs?) =
        if (inputs != null) {
            val (hours, minutes) = (inputs as TimeInputs).time
            when {
                hours > 0 && minutes > 0 -> true
                hours >= 0 && minutes > 0 -> true
                hours > 0 && minutes >= 0 -> true
                else -> false
            }
        } else {
            false
        }
}
