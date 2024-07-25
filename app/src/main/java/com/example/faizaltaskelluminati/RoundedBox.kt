package com.example.faizaltaskelluminati

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.faizaltaskelluminati.Const.AddData
import com.example.faizaltaskelluminati.Const.plushValue
import com.example.faizaltaskelluminati.Const.resetValue


@Composable
fun RoundedBox(thisData: MainActivity, color: Color, onMinus: () -> Unit, onPlush: () -> Unit) {
    var clickCount by remember { mutableStateOf(1) }


    plushValue.observe(thisData) {
        if (AddData == true) {
            clickCount += it
        }
    }

    Box(
        modifier = Modifier
            .width(80.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 0.5.dp, color = color, shape = RoundedCornerShape(10.dp))
            .background(color = Color.Transparent)
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        if (clickCount != 1) {
                            //resetValue.postValue(1)
                            onMinus.invoke()
                            clickCount--
                        }
                    },
                text = "-",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = color
            )
            VerticalDivider(modifier = Modifier)
            Text(
                modifier = Modifier
                    .weight(1f),
                text = clickCount.toString(),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = color
            )
            VerticalDivider()

            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        //resetValue.postValue(1)
                        onPlush.invoke()
                        clickCount++

                    },

                text = "+",
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                color = color
            )

        }


    }


}

@Composable
fun QTYRoundedBox(
    thisData: MainActivity,
    color: Color,
    onMinus: () -> Unit,
    onPlush: () -> Unit,
    onCount: (Int) -> Unit
) {
    var clickCount by remember { mutableStateOf(1) }
    resetValue.observe(thisData) {
        clickCount = it
    }

    Box(
        modifier = Modifier
            .width(80.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 0.5.dp, color = color, shape = RoundedCornerShape(10.dp))
            .background(color = Color.Transparent)
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        if (clickCount != 1) {
                            onMinus.invoke()
                            clickCount--
                            onCount(clickCount)
                        }
                    },
                text = "-",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = color
            )
            VerticalDivider(modifier = Modifier)
            Text(
                modifier = Modifier
                    .weight(1f),
                text = clickCount.toString(),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = color
            )
            VerticalDivider()

            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = {
                        onPlush.invoke()
                        clickCount++
                        onCount(clickCount)


                    }),

                text = "+",
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                color = color
            )


        }


    }


}

@Composable
fun subTitle() {
    Text(
        text = "Choose up to 1",
        fontSize = 11.sp,
        color = Color.Gray
    )
}

@Composable
fun RadioButtonCustome(isSelectedItem: Boolean, onClick: () -> Unit) {
    androidx.compose.material3.RadioButton(
        modifier = Modifier.size(13.dp),
        selected = isSelectedItem,
        colors = RadioButtonDefaults.colors(
            selectedColor = Color.Black,
        ),
        onClick = {
            AddData = true
            //resetValue.postValue(1)
            onClick.invoke()
        }
    )
}

@Composable
fun checkBoxButtonCustome(isSelectedItem: Boolean, onClick: () -> Unit) {
    Checkbox(
        modifier = Modifier.size(11.dp),
        checked = isSelectedItem,
        colors = CheckboxDefaults.colors(
            checkmarkColor = Color.White,
            checkedColor = Color.Black

        ),
        onCheckedChange = {
           // resetValue.postValue(1)
            onClick.invoke()
        }
    )
}