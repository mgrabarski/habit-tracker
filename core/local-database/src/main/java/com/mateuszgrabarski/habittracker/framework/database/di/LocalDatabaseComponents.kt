package com.mateuszgrabarski.habittracker.framework.database.di

import com.mateuszgrabarski.habittracker.business.services.cache.abstraction.UserDaoService
import com.mateuszgrabarski.habittracker.framework.database.DatabaseDiScope
import com.mateuszgrabarski.habittracker.framework.database.HabitDatabase
import com.mateuszgrabarski.habittracker.framework.database.services.LocalUserDaoService
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val localDatabaseModule = module {
    single(named(DatabaseDiScope)) { HabitDatabase.buildDatabase(androidContext()) }
    factory { (get(named(DatabaseDiScope)) as HabitDatabase).provideUserDao() }
    factory<UserDaoService> { LocalUserDaoService(get()) }
}
