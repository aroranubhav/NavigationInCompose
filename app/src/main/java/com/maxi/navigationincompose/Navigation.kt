package com.maxi.navigationincompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Host() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        //nav graph nodes
        composable("main") {
            MainScreen(
                noParamNav = {
                    navController.navigate("noParams")
                }, paramNav = { fName, lName ->
                    navController.navigate("params/${fName}/${lName}")
                })
        }

        composable("noParams") {
            NoParamsScreen()
        }

        composable("params/{fName}/{lName}",
            arguments = listOf(
                navArgument("fName") {
                    type = NavType.StringType
                },
                navArgument("lName") {
                    type = NavType.StringType
                }
            )
        ) {
            val firstName = it.arguments?.getString("fName") ?: ""
            val lastName = it.arguments?.getString("lName") ?: ""
            ParamsScreen(firstName, lastName)
        }
    }
}

@Composable
fun MainScreen(
    noParamNav: () -> Unit,
    paramNav: (fName: String, lName: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Navigate to no Param Screen",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.clickable {
                noParamNav()
            }
        )

        Text(
            text = "Navigate to Param Screen",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .clickable {
                    paramNav("Anubhav", "Arora")
                }
        )
    }
}

@Composable
fun NoParamsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No Params here!",
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Composable
fun ParamsScreen(firstName: String, lastName: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$firstName $lastName",
            style = MaterialTheme.typography.displayLarge
        )
    }
}