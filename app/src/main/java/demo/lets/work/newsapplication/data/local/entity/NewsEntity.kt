package demo.lets.work.newsapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import demo.lets.work.newsapplication.core.common.Constants
import demo.lets.work.newsapplication.domain.model.News

@Entity(tableName = Constants.DB_TABLE_NEWS)
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val newsTitle: String,
    val newsDescription: String,
    val newsAuthor: String,
    val newsUrl: String,
    val newsImage: String,
    val sourceName: String,
    val newsPublishedAt: String,
) {
    fun toNews(): News {
        return News(
            newsTitle = newsTitle,
            newsDescription = newsDescription,
            newsAuthor = newsAuthor,
            newsUrl = newsUrl,
            newsImage = newsImage,
            sourceName = sourceName,
            newsPublishedAt = newsPublishedAt
        )
    }
}

