package demo.lets.work.newsapplication.data.remote.dto

import com.google.gson.annotations.SerializedName
import demo.lets.work.newsapplication.data.local.entity.NewsEntity

data class ArticleDto(
    val sourceDto: SourceDto,
    val title: String,
    val description: String,
    val author: String,
    @SerializedName("url")
    val newsUrl: String,
    @SerializedName("urlToImage")
    val imageUrl: String,
    val content: String,
    val publishedAt: String
) {
    fun toNewsEntity(): NewsEntity {
        return NewsEntity(
            newsTitle = title,
            newsDescription = description,
            newsAuthor = author,
            newsUrl = newsUrl,
            newsImage = imageUrl,
            sourceName = sourceDto.name,
            newsPublishedAt = publishedAt
        )
    }
}