package com.ggoobuk.domain.di

import com.ggoobuk.domain.usecase.CheckBookmarkUseCase
import org.koin.dsl.module

val coreDomainModule = module {
    factory { CheckBookmarkUseCase() }
}