package com.reverb.recipechallenge.model.datamodel

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String,
    var isSelected: Boolean = false
)