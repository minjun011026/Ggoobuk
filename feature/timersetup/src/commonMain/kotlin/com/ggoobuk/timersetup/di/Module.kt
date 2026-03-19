package com.ggoobuk.timersetup.di

import com.ggoobuk.timersetup.TimeSetupViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureTimeSetUpModule = module {
    viewModelOf(::TimeSetupViewModel)
}