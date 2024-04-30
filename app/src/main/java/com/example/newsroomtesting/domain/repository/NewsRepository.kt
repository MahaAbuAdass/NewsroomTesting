package com.example.newsroomtesting.domain.repository

import com.example.newsroom.domain.model.Article
import com.example.newsroomtesting.util.Resource

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String
    ): Resource<List<Article>>


}