package com.muhdila.suitmediatest.ui.screen.second

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhdila.suitmediatest.data.remote.response.UserData
import com.muhdila.suitmediatest.data.remote.response.UserResponse
import com.muhdila.suitmediatest.data.remote.retforit.ApiService

class DetailUserViewModel(val apiService: ApiService) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _myUserDetails = MutableLiveData<UserResponse>()
    val myUserDetails: LiveData<UserResponse> = _myUserDetails

    suspend fun loadMyUserDetails(id: Int) {
        try {
            _isLoading.value = true

            val response = apiService.getDetailUser(id)
            if (response.isSuccessful) {
                _myUserDetails.value = response.body()
            } else {
                Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Exception: ${e.message.toString()}")
        } finally {
            _isLoading.value = false
        }
    }
}