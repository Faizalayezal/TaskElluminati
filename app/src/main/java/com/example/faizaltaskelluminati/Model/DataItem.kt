package com.example.faizaltaskelluminati.Model

data class DataItem(
    val _id: String?,
    val item_taxes: List<Int?>?,
    val name: List<String?>?,
    val price: Int?,
    val specifications: List<Specification>?


) : java.io.Serializable

data class Specification(
    val _id: String?,
    val name: List<String>?,
    val list: List<ItemList>?,

    val isAssociated: Boolean?,
    val isParentAssociate: Boolean?,
    val is_required: Boolean?,
    val max_range: Int?,
    val modifierGroupId: String?,
    val modifierGroupName: String?,
    val modifierId: String?,
    val modifierName: String?,
    val range: Int?,
    val sequence_number: Int?,
    val type: Int?,
    val unique_id: Int?,
    val user_can_add_specification_quantity: Boolean?
) : java.io.Serializable


data class ItemList(
    val _id: String?,
    val name: List<String>?,
    var price: Int?,
    var is_default_selected: Boolean?=false,
    val specification_group_id: String?,
    val unique_id: Int?,
    var savePrice: List<Int>?,
) : java.io.Serializable


data class subItems(var price: Int, var unique_id: Int?): java.io.Serializable
