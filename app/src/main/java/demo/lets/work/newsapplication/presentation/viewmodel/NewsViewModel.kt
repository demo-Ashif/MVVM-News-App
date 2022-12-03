package demo.lets.work.newsapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.lets.work.newsapplication.core.common.DispatcherProvider
import demo.lets.work.newsapplication.core.utils.Resource
import demo.lets.work.newsapplication.domain.model.News
import demo.lets.work.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    init {
        getNewsHeadlines()
    }

    sealed class NewsUiEvent {
        object Loading : NewsUiEvent()
        class Error(val data: List<News>, val msg: String) : NewsUiEvent()
        class Success(val data: List<News>) : NewsUiEvent()
    }

    private val _newsStateFlow =
        MutableStateFlow<NewsUiEvent>(NewsUiEvent.Loading)
    val newsStateFlow: StateFlow<NewsUiEvent>
        get() = _newsStateFlow


    private fun getNewsHeadlines() {
        viewModelScope.launch(dispatcher.io) {

            repository.getAllNewsHeadlines().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        if (result.data != null) {
                            _newsStateFlow.emit(
                                NewsUiEvent.Error(
                                    data = result.data,
                                    msg = result.message!!
                                )
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _newsStateFlow.emit(NewsUiEvent.Loading)
                    }
                    is Resource.Success -> {
                        if (result.data != null) {
                            _newsStateFlow.emit(NewsUiEvent.Success(result.data))
                        }
                    }
                }
            }
        }
    }

}