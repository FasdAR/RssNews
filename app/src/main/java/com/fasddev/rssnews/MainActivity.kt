package com.fasddev.rssnews

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.fasddev.rssnews.Adapters.Paging.NewsDiffUtilCallback
import com.fasddev.rssnews.Adapters.RVNews
import com.fasddev.rssnews.Data.DBRoom.Models.News
import com.fasddev.rssnews.Data.Parser.RssHandler
import java.net.URL

class MainActivity : AppCompatActivity()
{
    lateinit var recyclerView: RecyclerView
    lateinit var rvNews: RVNews

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        val rssHandler: RssHandler = RssHandler(URL("https://lenta.ru/rss"))
        rssHandler.startSync()
    }

    override fun onResume()
    {
        super.onResume()

        val sourceFactory:DataSource.Factory<Int, News>? = App.instance.database?.newsDao()?.getAllDSF()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(30)
            .setPrefetchDistance(10)
            .build()

        val pagedList: LiveData<PagedList<News>> = LivePagedListBuilder(sourceFactory!!, config).build()

        rvNews = RVNews(applicationContext, NewsDiffUtilCallback())

        pagedList.observe(this, Observer {
            if (!it.isNullOrEmpty())
            {
                rvNews.submitList(it)
            }
        })

        recyclerView.adapter = rvNews
    }
}
