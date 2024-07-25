package com.example.faizaltaskelluminati

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.faizaltaskelluminati.Screen.AddServiceScreen
import com.example.faizaltaskelluminati.Screen.ListScreen
import com.example.faizaltaskelluminati.ui.theme.FaizalTaskElluminatiTheme
import com.example.faizaltaskelluminati.Model.DataItem
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FaizalTaskElluminatiTheme {
                App(this@MainActivity)
            }
        }
    }
}

@Composable
fun App(mainActivity: MainActivity) {
    val navController= rememberNavController()

    val context= LocalContext.current

    val rawResId = R.raw.data
    val jsonString = context.resources.openRawResource(rawResId).bufferedReader().use {
        it.readText()
    }

    val dataList = Gson().fromJson(jsonString, DataItem::class.java)

    val itemViewModel: ItemViewModel = viewModel()

    NavHost(navController =navController , startDestination ="screen1" ){
        composable(route="screen1"){
            ListScreen(mainActivity, itemViewModel){
                navController.navigate("screen2")
            }
        }
        composable(route="screen2"){
            AddServiceScreen(mainActivity,modifier = Modifier,dataList,itemViewModel, popBack = navController::goBack,)
        }

    }
}


private fun NavHostController.goBack() = popBackStack()