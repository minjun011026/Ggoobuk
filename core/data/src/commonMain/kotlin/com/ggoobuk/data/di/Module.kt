package com.ggoobuk.data.di

import com.ggoobuk.data.repository.api.BookmarkRepository
import com.ggoobuk.data.repository.impl.BookmarkRepositoryImpl
import org.koin.dsl.module

val coreDataModule = module {
    single<BookmarkRepository> {
        BookmarkRepositoryImpl(bookmarkDao = get())
    }
}