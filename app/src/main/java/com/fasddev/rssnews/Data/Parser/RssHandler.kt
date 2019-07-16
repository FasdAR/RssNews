package com.fasddev.rssnews.Data.Parser

import android.util.Log
import com.fasddev.rssnews.App
import com.fasddev.rssnews.Data.DBRoom.Models.News
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.XMLReader
import org.xml.sax.helpers.DefaultHandler
import java.lang.Exception
import java.net.URL
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class RssHandler (val url: URL) : DefaultHandler()
{
    val saxParserFactory: SAXParserFactory = SAXParserFactory.newInstance()
    val saxParser: SAXParser = saxParserFactory.newSAXParser()
    val xmlReader: XMLReader = saxParser.xmlReader

    var isItem: Boolean = false
    var isTitle: Boolean = false
    var isLink: Boolean = false

    var news: News? = null

    public fun startSync()
    {
        Thread({
            try {
                xmlReader.contentHandler = this
                xmlReader.parse(InputSource(url.openStream()))
            }
            catch (ex: Exception)
            {
                Log.e("Ex", ex.message)
            }
        }).start()
    }

    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?)
    {
        if (qName == "item")
        {
            isItem = true

            news = News(null, "", "")
        }

        if (isItem)
        {
            if (qName == "title")
            {
                isTitle = true
            }
            else if (qName == "link")
            {
                isLink = true
            }
        }
    }

    override fun characters(ch: CharArray?, start: Int, length: Int)
    {
        val cdata = String(ch!!, start, length)
        val str: String = cdata.trim { it <= ' ' }.replace("\\s+".toRegex(), " ") + "\t"

        if (isTitle)
        {
            news?.title = str
        }
        else if (isLink)
        {
            news?.link = str
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?)
    {
        if (qName == "item")
        {
            isItem = false

            try
            {
                App.instance.database?.newsDao()?.insert(news!!)
            }
            catch (ex: Exception)
            {
                Log.d("ex", ex.message)
            }

            news = null
        }

        if (isItem)
        {
            if (qName == "title")
            {
                isTitle = false
            }
            else if (qName == "link")
            {
                isLink = false
            }
        }
    }
}