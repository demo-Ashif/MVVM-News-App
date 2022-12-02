package demo.lets.work.newsapplication

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)