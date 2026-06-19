package com.example.movieapp.core.data.remote.response

import kotlinx.serialization.SerialName

data class SearchResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<SearchResult>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)