package com.example.takenoteapp_cleanarchitecture.feature_take_note.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}