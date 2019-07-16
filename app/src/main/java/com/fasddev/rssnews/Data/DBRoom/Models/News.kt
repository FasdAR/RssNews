package com.fasddev.rssnews.Data.DBRoom.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "news", indices = arrayOf(Index(value = ["link"], unique = true)))
data class News(@PrimaryKey(autoGenerate = true) public var id: Long?, public var title: String, public var link: String)
{
}