package demo.lets.work.newsapplication.data.repository

import demo.lets.work.newsapplication.core.utils.Resource
import demo.lets.work.newsapplication.data.local.NewsDao
import demo.lets.work.newsapplication.data.remote.api.NewsApi
import demo.lets.work.newsapplication.domain.model.News
import demo.lets.work.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val dao: NewsDao
) : NewsRepository {


    override fun getAllNewsHeadlines(): Flow<Resource<List<News>>> = flow {

        emit(Resource.Loading())

        val staleNewsHeadlines = dao.getAllNewsHeadlines().map { it.toNews() }
        emit(Resource.Loading(data = staleNewsHeadlines))

        try {
            val allNewsHeadlines = api.getNewsHeadlines("us",50)
            dao.deleteAllNews()
            dao.insert(allNewsHeadlines.articleList.map { it.toNewsEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = staleNewsHeadlines
                )
            )
        } catch (e: IOException) {

            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = staleNewsHeadlines
                )
            )
        }

        val freshNewsHeadLines = dao.getAllNewsHeadlines().map { it.toNews() }
        emit(Resource.Success(freshNewsHeadLines))

    }

}