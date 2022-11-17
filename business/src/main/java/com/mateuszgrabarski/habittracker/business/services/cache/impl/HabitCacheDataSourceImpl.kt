package com.mateuszgrabarski.habittracker.business.services.cache.impl

import com.mateuszgrabarski.habittracker.business.data.models.habits.NewHabit
import com.mateuszgrabarski.habittracker.business.services.cache.CacheResult
import com.mateuszgrabarski.habittracker.business.services.cache.CacheResult.GenericError
import com.mateuszgrabarski.habittracker.business.services.cache.CacheResult.Success
import com.mateuszgrabarski.habittracker.business.services.cache.HabitCacheDataSource
import com.mateuszgrabarski.habittracker.business.services.cache.abstraction.HabitDaoService

class HabitCacheDataSourceImpl(
    private val dao: HabitDaoService
) : HabitCacheDataSource {

    override suspend fun insertNewHabit(habit: NewHabit): CacheResult<Long> {
        val numberOfInserts = dao.insertNewHabit(habit = habit)
        return when {
            numberOfInserts > 0 -> Success(value = numberOfInserts)
            else -> GenericError(errorMessage = "cache error")
        }
    }
}