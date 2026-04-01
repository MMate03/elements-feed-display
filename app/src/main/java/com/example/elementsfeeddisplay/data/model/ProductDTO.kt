package com.example.elementsfeeddisplay.data.model

data class ProductDTO (
    val id: Int,
    val description: String,
    val thumbnail: String
)

data class ProductResponseDTO (
    val products: List<ProductDTO>,
    val total: Int,
    val skip: Int,
    val limit: Int
)





