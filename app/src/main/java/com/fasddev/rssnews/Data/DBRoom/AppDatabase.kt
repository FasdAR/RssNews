package com.fasddev.rssnews.Data.DBRoom

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.fasddev.rssnews.Data.DBRoom.Dao.NewsDao
import com.fasddev.rssnews.Data.DBRoom.Models.News

@Database(entities = arrayOf(News::class), version = 1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun newsDao(): NewsDao
}