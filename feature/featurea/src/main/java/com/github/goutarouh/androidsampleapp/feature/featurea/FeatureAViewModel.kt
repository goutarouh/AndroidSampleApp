package com.github.goutarouh.androidsampleapp.feature.featurea

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.goutarouh.androidsampleapp.core.repository.Data1Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeatureAViewModel @Inject constructor(
    val data1Repository: Data1Repository
): ViewModel() {
    init {
        viewModelScope.launch {
            val rss = data1Repository.getRss()
            Log.i("hasegawa", rss.title)
        }
    }
}