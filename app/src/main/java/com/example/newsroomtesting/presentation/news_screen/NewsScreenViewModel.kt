package com.example.newsroomtesting.presentation.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsroom.domain.model.Article
import com.example.newsroomtesting.domain.repository.NewsRepository
import com.example.newsroomtesting.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsScreenViewModel @Inject constructor ( private val newsRepository: NewsRepository ) : ViewModel() {

    var articles by mutableStateOf<List<Article>>(emptyList())

    var state by mutableStateOf(NewsScreenState())

    fun onEvent(event : NewsScreenEvent){
        when (event) {
            is NewsScreenEvent.OnCategoryChanged -> {
                state = state.copy(category = event.category)
                getNewsArticle((state.category))
            }
            is NewsScreenEvent.OnCloseIconClicked-> {}
            is NewsScreenEvent.OnNewsCardClicked -> {

                state = state.copy(selectedArticle = event.article)
            }
            is NewsScreenEvent.OnSearchIconClicked -> {}
            is NewsScreenEvent.OnSearchQueryChanged -> {}


            else -> {}
        }
    }


    init {
        getNewsArticle(category = "general")
    }

    private fun getNewsArticle(category: String){
        viewModelScope.launch {

            state = state.copy(isLoading = true)
            var result = newsRepository.getTopHeadlines(category = category)

            when (result) {
                is Resource.Success -> {
                   state = state.copy(articles = result.data ?: emptyList() ,
                       isLoading = false ,
                       error = null
                       )
                }
                is Resource.Error -> {
                   state= state.copy(
                        error = result.message ,
                        isLoading = false ,
                        articles = emptyList()
                    )
                }

            }            }
    }

}