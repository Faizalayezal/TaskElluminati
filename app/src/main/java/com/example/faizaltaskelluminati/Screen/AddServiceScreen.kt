package com.example.faizaltaskelluminati.Screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.faizaltaskelluminati.Const.resetValue
import com.example.faizaltaskelluminati.ItemViewModel
import com.example.faizaltaskelluminati.LocalData.Items
import com.example.faizaltaskelluminati.MainActivity
import com.example.faizaltaskelluminati.Model.DataItem
import com.example.faizaltaskelluminati.Model.ItemList
import com.example.faizaltaskelluminati.Model.Specification
import com.example.faizaltaskelluminati.Model.subItems
import com.example.faizaltaskelluminati.QTYRoundedBox
import com.example.faizaltaskelluminati.R
import com.example.faizaltaskelluminati.RadioButtonCustome
import com.example.faizaltaskelluminati.RoundedBox
import com.example.faizaltaskelluminati.subTitle
import kotlin.math.log

@SuppressLint("UnrememberedMutableState", "RestrictedApi", "MutableCollectionMutableState")
@Composable
fun AddServiceScreen(
    thisData: MainActivity,
    modifier: Modifier,
    dataModel: DataItem,
    viewModel: ItemViewModel,
    popBack: (() -> Unit)? = null,
) {


    val dataList by remember { mutableStateOf(mutableStateListOf<Specification>()) }
    val selectedValue = remember { mutableStateOf("999") }
    val mainAmount = rememberSaveable { mutableStateOf(0) }
    val sum = remember { mutableStateOf(0) }
    val count = remember { mutableStateOf(1) }
    val selectedId = remember { mutableStateOf("") }
    val items = remember { mutableStateListOf<subItems>() }


    val context = LocalContext.current

    LaunchedEffect(key1 = dataModel) {
        dataList.clear()
        dataModel.specifications?.let {
            dataList.addAll(it)

        }


    }
    DisposableEffect(thisData) {
        onDispose {
            viewModel.getId(0)
        }

    }


    dataList.map {
        it.list?.map { its ->
            if (its.price == selectedValue.value.toIntOrNull()) {
                selectedId.value = its._id ?: ""

            }

        }

    }

    sum.value = items.sumOf { it.price }

    mainAmount.value =
        ((selectedValue.value.toIntOrNull() ?: 0) * count.value).plus(sum.value * count.value)




    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 50.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            colorFilter = ColorFilter.tint(Color.Black),
            contentDescription = "", modifier = Modifier
                .size(20.dp)
                .clickable {
                    popBack?.invoke()
                }


        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                SpecificationList(
                    dataList.getOrNull(0),
                    selectedValue,
                    dataList.getOrNull(0)?.name.toString(),
                    mainAmount,
                    items,
                    count,
                    onClick = {

                    }

                )
                HorizontalDivider()
                dataList.map {
                    if (selectedId.value == it.modifierId) {

                        BedroomList(
                            thisData,
                            it.list,
                            it.name.toString(),
                            it._id,
                            items,
                            count

                        )

                    }

                }

                HorizontalDivider()

            }


        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(color = Color.Black),


            ) {

            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 50.dp)
            ) {

                QTYRoundedBox(
                    thisData = thisData,
                    color = White,
                    onMinus = {
                        mainAmount.value = (selectedValue.value.toIntOrNull()
                            ?: 0) - sum.value

                    },
                    onPlush = {

                    }, onCount = {
                        count.value = it
                    })
            }


            Button(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    if (!selectedValue.value.contains(mainAmount.value.toString())) {
                        if (viewModel.IDS.value == 0) {
                            val itemData = Items(
                                apartmentSize = "",
                                totalAmount = selectedValue.value,
                            )
                            viewModel.addItem(itemData)
                        } else {
                            val itemData = Items(
                                id = viewModel.IDS.value,
                                apartmentSize = "",
                                totalAmount = selectedValue.value,
                            )
                            viewModel.updateItem(itemData)
                        }

                        popBack?.invoke()
                    } else {
                        Toast.makeText(context, "Please select sub Category", Toast.LENGTH_LONG)
                            .show()

                    }


                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )

            ) {

                Text(
                    text = "Add To Cart- ₹${mainAmount.value}",
                    color = White,
                    fontSize = 14.sp
                )
            }
        }


        Spacer(modifier = Modifier.height(40.dp))

    }


}

@Composable
fun SpecificationList(
    specifications: Specification?,
    selectedValue: MutableState<String>,
    name: String,
    mainAmount: MutableState<Int>,
    items: SnapshotStateList<subItems>,
    count: MutableState<Int>,
    onClick: (Int) -> Unit,
) {
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }


    Text(
        text = name.replace("[", "").replace("]", ""),
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Text(
        text = "Choose 1",
        fontSize = 11.sp,
        color = Color.Gray
    )
    specifications?.list?.forEachIndexed { index, itemList ->

        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            RadioButtonCustome(isSelectedItem(itemList.price.toString())) {
                onClick.invoke(1)
                mainAmount.value = 0
                count.value = 1
                items.clear()
                resetValue.postValue(1)
                selectedValue.value = itemList.price.toString()


            }

            Spacer(modifier = Modifier.width(15.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = specifications.list[index].name.toString().replace("[", "").replace("]", ""),
                fontSize = 14.sp,
                color = Color.Black
            )

            Text(
                text = specifications.list[index].price.toString(),
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }


}


@SuppressLint("UnrememberedMutableState")
@Composable
fun BedroomList(
    thisData: MainActivity,
    specifications: List<ItemList>?,
    name: String,
    _id: String?,
    items: MutableList<subItems>,
    count: MutableState<Int>,
) {

    val selectedId = remember { mutableStateOf(0) }

    val isSelectedItem: (Int) -> Boolean = { it ->
        selectedId.value == it
    }



    Text(
        text = name.replace("[", "").replace("]", ""),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    subTitle()
    specifications?.forEachIndexed { index, itemList ->

        if (!isSelectedItem(itemList.unique_id ?: 0)) {
            items.removeAll {
                it.unique_id == itemList.unique_id
            }
        }

        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(
                modifier = Modifier.size(11.dp),
                checked = isSelectedItem(itemList.unique_id ?: 0),
                colors = CheckboxDefaults.colors(
                    checkmarkColor = White,
                    checkedColor = Color.Black

                ),
                onCheckedChange = { isChecked ->
                    if (_id == itemList.specification_group_id) {

                        if (isSelectedItem(itemList.unique_id ?: 0)) {
                            selectedId.value = 0

                            items.removeAll {
                                it.unique_id == itemList.unique_id
                            }



                        } else {
                            selectedId.value = itemList.unique_id ?: 0

                                items.add(
                                    subItems(
                                        price = (itemList.price ?: 0),
                                        unique_id = itemList.unique_id
                                    )
                                )


                        }


                    }


                }
            )

            Spacer(modifier = Modifier.width(15.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = itemList.name.toString().replace("[", "").replace("]", ""),
                fontSize = 13.sp,
                color = Color.Black
            )
            if (isSelectedItem(itemList.unique_id ?: 0) && itemList.price.toString() != "0") {
                RoundedBox(thisData = thisData, color = (Color.Black),
                    onMinus = {
                        if (isSelectedItem(itemList.unique_id ?: 0)) {
                            items.remove(
                                    subItems(
                                        price = (itemList.price ?: 0),
                                        unique_id = itemList.unique_id
                                    )
                                )


                        }
                    }, onPlush = {
                        if (isSelectedItem(itemList.unique_id ?: 0)) {
                            items.add(
                                    subItems(
                                        price =itemList.price?:0,
                                        unique_id = itemList.unique_id
                                    )
                                )

                        }


                    })
                Spacer(modifier = Modifier.width(5.dp))
            }
            Text(
                text = itemList.price.toString(),
                fontSize = 13.sp,
                color = Color.Black
            )


        }
    }
    Spacer(modifier = Modifier.height(5.dp))
    HorizontalDivider()


}




