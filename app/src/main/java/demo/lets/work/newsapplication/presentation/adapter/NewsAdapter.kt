package demo.lets.work.newsapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import demo.lets.work.newsapplication.R
import demo.lets.work.newsapplication.domain.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val allNews: MutableList<News> = mutableListOf()

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(item: News) {

        }
    }

    fun setAllNews(newsList: List<News>) {
        allNews.clear()
        allNews.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.bind(allNews[position])
    }

    override fun getItemCount(): Int {
        return allNews.size
    }

}