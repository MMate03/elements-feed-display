package com.example.elementsfeeddisplay.domain.repository
import com.example.elementsfeeddisplay.domain.model.FeedItem
interface IFeedRepository {
    suspend fun getNextProduct(skip: Int): FeedItem.ProductFeed?
}