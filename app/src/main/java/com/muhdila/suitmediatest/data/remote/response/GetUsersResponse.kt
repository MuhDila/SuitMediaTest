package com.muhdila.suitmediatest.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetUsersResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("data")
    val data: List<GetUsersData>,
    @SerializedName("support")
    val support: SupportData,
)

data class GetUsersData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String,
)

data class SupportData(
    @SerializedName("url")
    val url: String,
    @SerializedName("text")
    val text: String,
)

data class UserResponse(
    @SerializedName("data")
    val data: UserData,
    @SerializedName("support")
    val support: SupportDataUser
)

data class UserData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String
)

data class SupportDataUser(
    @SerializedName("url")
    val url: String,
    @SerializedName("text")
    val text: String
)
