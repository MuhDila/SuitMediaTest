package com.muhdila.suitmediatest.data.remote.retforit

import com.muhdila.suitmediatest.data.remote.response.GetUsersResponse
import com.muhdila.suitmediatest.data.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getAllUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<GetUsersResponse>

    @GET("users/{idUser}")
    suspend fun getDetailUser(
        @Path("idUser") idUser: Int,
    ): Response<UserResponse>
}