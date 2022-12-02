package demo.lets.work.newsapplication.domain.model

import demo.lets.work.newsapplication.data.local.entity.NewsEntity

data class News(
    val newsTitle: String,
    val newsDescription: String,
    val newsAuthor: String,
    val newsUrl: String,
    val newsImage: String,
    val sourceName: String,
    val newsPublishedAt: String,
)