package com.ggoobuk.domain.usecase

import com.ggoobuk.model.Time

class CheckBookmarkUseCase {

    operator fun invoke(hour: Int, minute: Int, second: Int, bookmarks: List<Time>) =
        bookmarks.any { it.hour == hour && it.minute == minute && it.second == second }

}