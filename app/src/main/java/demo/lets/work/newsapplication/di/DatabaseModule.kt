package demo.lets.work.newsapplication.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import demo.lets.work.newsapplication.data.local.NewsDao
import demo.lets.work.newsapplication.data.local.NewsDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCurrencyDatabase(@ApplicationContext appContext: Context): NewsDatabase =
        NewsDatabase.getInstance(appContext)

    @Provides
    @Singleton
    fun provideCurrencyDAO(newsDb: NewsDatabase): NewsDao =
        newsDb.newsDao()



}