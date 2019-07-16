package com.fasddev.rssnews.Adapters.Paging

import android.support.v7.util.DiffUtil
import com.fasddev.rssnews.Data.DBRoom.Models.News

class NewsDiffUtilCallback (): DiffUtil.ItemCallback<News>()
{
    override fun areItemsTheSame(oldItem: News?, newItem: News?): Boolean
    {
        return oldItem?.link == newItem?.link
    }

    override fun areContentsTheSame(oldItem: News?, newItem: News?): Boolean
    {
        return oldItem?.title == newItem?.title
    }
}