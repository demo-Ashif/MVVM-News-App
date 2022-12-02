package demo.lets.work.newsapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("articles")
    val articleList: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)