package com.example.elementsfeeddisplay.domain.repository

import android.util.Log.e
import com.example.elementsfeeddisplay.data.api.JsonParser
import com.example.elementsfeeddisplay.domain.model.FeedItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class FeedRepository : IFeedRepository {
    override suspend fun getNextProduct(skip: Int): FeedItem.ProductFeed? = withContext(Dispatchers.IO) {
        var connection: HttpURLConnection? = null
        try {
            val url = URL("https://dummyjson.com/products?limit=1&skip=$skip")
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 5000
            connection.readTimeout = 5000

            if(connection.responseCode == HttpURLConnection.HTTP_OK) {
                val responseText = connection.inputStream.bufferedReader().use { it.readText() }
                val responseDTO = JsonParser.parseProductResponse(responseText)

                val product = responseDTO.products.firstOrNull()
                if (product != null) {
                    return@withContext FeedItem.ProductFeed(
                        id = product.id,
                        text = product.description,
                        timeStamp = java.text.SimpleDateFormat(
                            "HH:mm:ss",
                            java.util.Locale.getDefault()
                        ).format(java.util.Date())
                    )
                }
            }
                null
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    finally {
        connection?.disconnect()
        }
    }
}