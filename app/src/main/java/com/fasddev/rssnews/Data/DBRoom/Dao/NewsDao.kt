package com.fasddev.rssnews.Data.DBRoom.Dao

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.fasddev.rssnews.Data.DBRoom.Models.News

@Dao
interface NewsDao
{
    @Insert
    fun insert(news: News)

    @Query("SELECT * FROM news")
    fun getAllLD(): LiveData<List<News>>

    @Query("SELECT * FROM news")
    fun getAll(): List<News>

    @Query("SELECT * FROM news")
    fun getAllDSF() : DataSource.Factory<Int, News>
}