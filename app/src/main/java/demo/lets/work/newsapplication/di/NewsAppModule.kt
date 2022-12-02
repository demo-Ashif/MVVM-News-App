package demo.lets.work.newsapplication.di

import demo.lets.work.newsapplication.domain.repository.NewsRepository
import demo.lets.work.newsapplication.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import demo.lets.work.newsapplication.core.common.DispatcherProvider
import demo.lets.work.newsapplication.data.local.NewsDao
import demo.lets.work.newsapplication.data.remote.api.NewsApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsAppModule {

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi, newsDao: NewsDao): NewsRepository =
        NewsRepositoryImpl(api, newsDao)

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
    }
}