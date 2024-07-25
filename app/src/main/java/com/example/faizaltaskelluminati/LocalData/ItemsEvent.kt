package com.example.faizaltaskelluminati.LocalData

sealed interface ItemsEvent {
    object SaveItem:ItemsEvent
    data class DeleteItem(val item:Items):ItemsEvent
}