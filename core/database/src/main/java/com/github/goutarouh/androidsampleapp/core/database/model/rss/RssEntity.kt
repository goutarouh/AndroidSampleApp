package com.github.goutarouh.androidsampleapp.core.database.model.rss

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RssEntity(
    // TODO linkなどをPrimaryKeyに修正する
    @PrimaryKey
    val title: String
)
