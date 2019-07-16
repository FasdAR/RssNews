package com.fasddev.rssnews

import android.app.Application
import android.arch.persistence.room.Room
import com.fasddev.rssnews.Data.DBRoom.AppDatabase

class App : Application()
{
    companion object {
        lateinit var instance: App
    }

    var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        database = Room.databaseBuilder(this, AppDatabase::class.java, "nwdb").build()
    }
}