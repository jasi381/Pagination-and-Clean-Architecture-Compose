package com.jasmeet.cleanarch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jasmeet.cleanarch.data.Dogs
import com.jasmeet.cleanarch.data.DogsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val dogsPagingSource: DogsPagingSource
) : ViewModel() {

    private val _dogResponse: MutableStateFlow<PagingData<Dogs>> =
        MutableStateFlow(PagingData.empty())
    var dogResponse = _dogResponse.asStateFlow()
        private set

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                dogsPagingSource
            }.flow.cachedIn(viewModelScope).collect {
                _dogResponse.value = it
            }
        }
    }

}