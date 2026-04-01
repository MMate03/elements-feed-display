package com.example.elementsfeeddisplay.data.api

import com.example.elementsfeeddisplay.data.model.ProductDTO
import com.example.elementsfeeddisplay.data.model.ProductResponseDTO
import org.json.JSONObject

object JsonParser {

    fun parseProductResponse(jsonString: String): ProductResponseDTO {
        val root = JSONObject(jsonString);
        val productArray = root.getJSONArray("products");
        val productList = mutableListOf<ProductDTO>();

        for (i in 0 until productArray.length()) {
            val productObject = productArray.getJSONObject(i);
            productList.add(
                ProductDTO(
                    id = productObject.getInt("id"),
                    description = productObject.getString("description"),
                    thumbnail = productObject.getString("thumbnail")
            )
            )
    }
        return ProductResponseDTO(
            products = productList,
            total = root.getInt("total"),
            skip = root.getInt("skip"),
            limit = root.getInt("limit")
        );
}
}