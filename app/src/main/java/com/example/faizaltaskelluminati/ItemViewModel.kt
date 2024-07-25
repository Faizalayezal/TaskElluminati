package com.example.faizaltaskelluminati

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faizaltaskelluminati.LocalData.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val itemRepository: ItemRepository) : ViewModel() {


    private val _items = MutableStateFlow<List<Items>>(emptyList())
    val items: StateFlow<List<Items>> = _items.asStateFlow()


    init {
        viewModelScope.launch {
            itemRepository.getAllItems().collect { listOfItems ->
                _items.value = listOfItems
            }
        }
    }

    fun addItem(item: Items) = viewModelScope.launch { itemRepository.insertItem(item) }

    fun updateItem(item: Items) = viewModelScope.launch { itemRepository.updateItem(item) }

    fun deleteItem(item: Items) = viewModelScope.launch { itemRepository.deleteItem(item) }

    private val _IDS:MutableState<Int> = mutableStateOf(0)
    val IDS: State<Int> = _IDS

    fun getId(id:Int){
        _IDS.value=id
    }




}
