package com.example.newsroomtesting.data.remote.repository

import com.example.newsroom.domain.model.Article
import com.example.newsroomtesting.data.remote.NewsApi
import com.example.newsroomtesting.domain.repository.NewsRepository
import com.example.newsroomtesting.util.Resource
import java.lang.Exception

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {

    override suspend fun getTopHeadlines(category: String): Resource<List<Article>> {

        return try {
            val response = newsApi.getBreakingNews(category = category)
            Resource.Success(response.articles)

        } catch (e: Exception) {
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}