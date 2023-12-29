package com.muhdila.suitmediatest.ui.screen.third

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muhdila.suitmediatest.data.remote.response.GetUsersData
import com.muhdila.suitmediatest.data.remote.retforit.ApiService
import retrofit2.HttpException
import java.io.IOException

class UsersPagingSource(private val apiService: ApiService) : PagingSource<Int, GetUsersData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetUsersData> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getAllUsers(page = nextPageNumber, perPage = PAGE_SIZE)

            if (response.isSuccessful) {
                val data = response.body()?.data ?: emptyList()
                LoadResult.Page(
                    data = data,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (data.isEmpty()) null else nextPageNumber + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to load"))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GetUsersData>): Int? {
        return state.anchorPosition
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}