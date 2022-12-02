package demo.lets.work.newsapplication.data.local

import androidx.room.*
import demo.lets.work.newsapplication.data.local.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(newsEntityList: List<NewsEntity>)

    @Query("SELECT * FROM news_table")
    suspend fun getAllNewsHeadlines(): List<NewsEntity>

    @Query("DELETE FROM news_table")
    suspend fun deleteAllNews()
}