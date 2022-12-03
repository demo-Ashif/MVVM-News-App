package demo.lets.work.newsapplication.data.remote.dto

import com.google.gson.annotations.SerializedName
import demo.lets.work.newsapplication.core.utils.Helper
import demo.lets.work.newsapplication.data.local.entity.NewsEntity

data class ArticleDto(
    @SerializedName("source")
    val sourceDto: SourceDto?,
    val title: String,
    @SerializedName("description")
    val description: String?,
    val author: String?,
    @SerializedName("url")
    val newsUrl: String,
    @SerializedName("urlToImage")
    val imageUrl: String?,
    @SerializedName("content")
    val content: String?,
    val publishedAt: String
) {
    fun toNewsEntity(): NewsEntity {

        return NewsEntity(
            newsTitle = title,
            newsDescription = description?:"Read more ...",
            newsAuthor = author ?: "News Correspondent",
            newsUrl = newsUrl,
            newsImage = imageUrl?:"",
            sourceName = validateSourceName(),
            newsPublishedAt = Helper.toReadableDate(publishedAt)
        )
    }

    private fun validateSourceName(): String {
        return if (sourceDto?.name == null) {
            "N/A"
        } else {
            sourceDto.name
        }
    }
}