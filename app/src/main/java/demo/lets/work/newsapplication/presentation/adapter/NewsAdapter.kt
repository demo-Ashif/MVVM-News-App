package demo.lets.work.newsapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import demo.lets.work.newsapplication.databinding.NewsItemBinding
import demo.lets.work.newsapplication.domain.model.News

class NewsAdapter(private val onNewsClicked: (News) -> Unit) :
    ListAdapter<News, NewsAdapter.NoteViewHolder>(ComparatorDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val news = getItem(position)
        news?.let {
            holder.bind(it)
        }
    }

    inner class NoteViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.tvTitle.text = news.newsTitle
            binding.tvDescription.text = news.newsDescription
            binding.tvSourceName.text = news.sourceName
            binding.tvPublishDate.text = news.newsPublishedAt
            binding.root.setOnClickListener {
                onNewsClicked(news)
            }
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }


}