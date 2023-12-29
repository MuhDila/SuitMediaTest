package com.muhdila.suitmediatest.ui.screen.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.muhdila.suitmediatest.data.remote.retforit.ApiConfig

class UsersViewModel : ViewModel() {

    private val pager = Pager(
        PagingConfig(pageSize = UsersPagingSource.PAGE_SIZE),
        pagingSourceFactory = { UsersPagingSource(ApiConfig.getApiService()) }
    )

    val usersData = pager.flow.cachedIn(viewModelScope)
}
