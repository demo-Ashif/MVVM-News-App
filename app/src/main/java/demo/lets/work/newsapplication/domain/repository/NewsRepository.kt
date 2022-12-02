package demo.lets.work.newsapplication.domain.repository

import demo.lets.work.newsapplication.core.utils.Resource
import demo.lets.work.newsapplication.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getAllNewsHeadlines(): Flow<Resource<List<News>>>
}