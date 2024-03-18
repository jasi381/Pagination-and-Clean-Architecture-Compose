package com.jasmeet.cleanarch.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jasmeet.cleanarch.R


@Composable
fun DogsScreen(
    modifier: Modifier = Modifier,
    viewModel: DogsViewModel = hiltViewModel()
) {
    val response = viewModel.dogResponse.collectAsLazyPagingItems()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        verticalItemSpacing = 15.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        items(response.itemSnapshotList.items) {response->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(response.url)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(100.dp)
            )
        }

        response.apply {
            when {
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                    Log.d("TAG", "DogsScreen: Loading")
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }

                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    Log.d("TAG", "DogsScreen: Error")
                    item {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                            .navigationBarsPadding()){
                            Text(text = "Error", modifier = Modifier.align(Alignment.Center) )
                        }
                    }
                }
            }
        }
    }
}