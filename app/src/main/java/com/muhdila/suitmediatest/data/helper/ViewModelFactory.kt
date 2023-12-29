package com.muhdila.suitmediatest.data.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muhdila.suitmediatest.data.remote.retforit.ApiConfig
import com.muhdila.suitmediatest.data.remote.retforit.ApiService
import com.muhdila.suitmediatest.ui.screen.second.DetailUserViewModel

@Suppress("UNCHECKED_CAST")
class LocalViewModelFactory private constructor(
    private val apiService: ApiService,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailUserViewModel::class.java) -> {
                DetailUserViewModel(apiService) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Composable
        fun provide(): LocalViewModelFactory {
            val context = LocalContext.current
            val apiService = ApiConfig.getApiService()

            return remember(context, apiService) {
                LocalViewModelFactory(apiService)
            }
        }
    }
}