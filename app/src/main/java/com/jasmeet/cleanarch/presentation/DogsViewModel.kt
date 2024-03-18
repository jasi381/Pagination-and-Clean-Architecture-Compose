package com.jasmeet.cleanarch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.jasmeet.cleanarch.data.DogsPagingSource
import com.jasmeet.cleanarch.data.DogsRepository
import com.jasmeet.cleanarch.domain.Dog
import com.jasmeet.cleanarch.domain.toDog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val repository: DogsRepository
) : ViewModel() {

    private val _dogResponse: MutableStateFlow<PagingData<Dog>> =
        MutableStateFlow(PagingData.empty())
    val dogResponse = _dogResponse.asStateFlow()

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = true
                ),
                pagingSourceFactory = { DogsPagingSource(repository) }
            ).flow.cachedIn(viewModelScope).collectLatest { dogs->
                _dogResponse.value = dogs.map { it.toDog() }
            }
        }
    }
}
