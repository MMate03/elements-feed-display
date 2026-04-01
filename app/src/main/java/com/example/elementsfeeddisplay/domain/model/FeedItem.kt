package com.example.elementsfeeddisplay.domain.model

sealed class FeedItem {

    data class ProductFeed(
        val id: Int,
        val text: String,
        val timeStamp: String,
    ) : FeedItem()

    data class CommandEntry(
        val command: String,
        val timeStamp: String,
    ) : FeedItem()
}