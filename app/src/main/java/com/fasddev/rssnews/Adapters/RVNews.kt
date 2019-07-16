package com.fasddev.rssnews.Adapters

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fasddev.rssnews.Data.DBRoom.Models.News
import com.fasddev.rssnews.R
import kotlinx.android.synthetic.main.item_news.view.*

class RVNews (val context: Context, val diffUtilCallBack: DiffUtil.ItemCallback<News>) : PagedListAdapter<News, RecyclerView.ViewHolder>(diffUtilCallBack)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        return ViewHolderItem(
            LayoutInflater.from(context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        val holder = (holder as ViewHolderItem)
        val item = (getItem(position) as News)

        holder.name.setText(item.title)
    }

    class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view)
    {
        val name: TextView = view.findViewById(R.id.name)
    }
}