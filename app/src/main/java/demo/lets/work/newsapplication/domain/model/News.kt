package demo.lets.work.newsapplication.domain.model

data class News(
    val id: Int,
    val newsTitle: String,
    val newsDescription: String,
    val newsAuthor: String,
    val newsUrl: String,
    val newsImage: String,
    val sourceName: String,
    val newsPublishedAt: String,
)