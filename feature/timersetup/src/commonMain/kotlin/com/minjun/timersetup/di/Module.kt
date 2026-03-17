package com.minjun.timersetup.di

import com.minjun.timersetup.TimeSetupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureTimeSetUpModule = module {
    viewModelOf(::TimeSetupViewModel)
}