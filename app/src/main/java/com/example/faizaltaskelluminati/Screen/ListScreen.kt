package com.example.faizaltaskelluminati.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.faizaltaskelluminati.Const.AddData
import com.example.faizaltaskelluminati.Const.plushValue
import com.example.faizaltaskelluminati.ItemViewModel
import com.example.faizaltaskelluminati.MainActivity
import com.example.faizaltaskelluminati.RoundedBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(thisData: MainActivity, viewModel: ItemViewModel, onClick: () -> Unit) {
    val items = viewModel.items.collectAsState().value
    val sheetState = rememberModalBottomSheetState()
    val mainAmount = remember { mutableStateOf(0) }
    var isSheetOpen by rememberSaveable {

        mutableStateOf(false)
    }
    var getId by rememberSaveable {

        mutableStateOf(0)
    }
    val selectedIndex = remember { mutableStateOf(-1) }
    val selectedSubIndex = remember { mutableStateOf(-1) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 30.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(items) { it ->
                    val isSelected  = selectedIndex.value == items.indexOf(it)
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        onClick = {
                            getId=it.id
                            selectedSubIndex.value=items.indexOf(it)
                            isSheetOpen=true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(10.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Make your own Package",
                                modifier = Modifier
                                    .padding(14.dp),
                                textAlign = TextAlign.Center,
                            )
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(40.dp)
                                    .padding(5.dp)
                                    .clip(RoundedCornerShape(50.dp))
                                    .border(
                                        width = 0.5.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(50.dp)
                                    )
                                    .background(color = Color.Transparent)
                                    .clickable {
                                        selectedIndex.value = items.indexOf(it)
                                    }
                            ) {
                                    if (isSelected){
                                        RoundedBox(thisData,(Color.Black),onMinus = {
                                            if(!mainAmount.toString().contains("2")){
                                                if (mainAmount != null) {
                                                    mainAmount.value-=mainAmount.value
                                                }
                                            }

                                        }, onPlush = {
                                            mainAmount.value+=mainAmount.value

                                        })
                                    }else{
                                        Text(
                                            text = "Customize",
                                            fontSize = 10.sp,
                                            modifier = Modifier.align(Alignment.Center),
                                            textAlign = TextAlign.Center,
                                        )
                                    }



                            }


                        }

                        Row {
                            Text(
                                text = "₹${it.totalAmount}",
                                modifier = Modifier
                                    .padding(start = 14.dp),
                                textAlign = TextAlign.Center,
                            )


                        }

                    }


                }


            }
            Spacer(modifier = Modifier.height(5.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 90.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = Color.Black),

                ) {


                Button(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        onClick.invoke()
                    },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )

                ) {
                    Text(
                        text = "Add To Service",
                        color = Color.White,
                        fontSize = 13.sp
                    )
                }
            }


            Spacer(modifier = Modifier.height(40.dp))

        }


    }
    if(isSheetOpen){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                AddData=false
                isSheetOpen=false
            },
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(start = 10.dp)){
                    Text(
                        text = "Would You Like To Repeat Last Customisation",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = "Make your own Package",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                    )
                }

                Row(horizontalArrangement = Arrangement.SpaceEvenly,modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .border(
                                width = 0.5.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .clickable {
                                viewModel.getId(getId)
                                onClick.invoke()
                                isSheetOpen = false
                            }
                            .background(color = Color.Transparent)
                    ) {

                        Text(
                            text = "Customize",
                            fontSize = 11.sp,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(5.dp),
                            textAlign = TextAlign.Center,
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .border(
                                width = 0.5.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .clickable {
                                selectedIndex.value = selectedSubIndex.value
                                if(selectedIndex.value == selectedSubIndex.value){
                                    AddData=true
                                    plushValue.value = 1
                                }
                            }
                            .background(color = Color.Transparent)
                    ) {

                        Text(
                            text = "Repeat",
                            fontSize = 11.sp,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(5.dp),
                            textAlign = TextAlign.Center,
                        )
                    }

                }



            }
        }
    }



}
