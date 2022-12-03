package demo.lets.work.newsapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import demo.lets.work.newsapplication.R
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
            binding.news = news
            binding.root.setOnClickListener {
                onNewsClicked(news)
            }
        }

    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thumbs: ImageView, imageUrl: String) {

            Glide.with(thumbs)
                .load(imageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.news_image_placeholder)
                        .error(R.drawable.news_image_placeholder)
                )
                .into(thumbs)

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