package demo.lets.work.newsapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.lets.work.newsapplication.core.common.DispatcherProvider
import demo.lets.work.newsapplication.core.utils.Resource
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
        object Error : NewsUiEvent()
        object Success : NewsUiEvent()
    }

    private val _newsEvent =
        MutableStateFlow<NewsUiEvent>(NewsUiEvent.Loading)
    val newsEvent: StateFlow<NewsUiEvent> = _newsEvent


    private fun getNewsHeadlines() {
        viewModelScope.launch(dispatcher.io) {

            repository.getAllNewsHeadlines().collect { result ->
                when (result) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {}
                }
            }
        }
    }

}