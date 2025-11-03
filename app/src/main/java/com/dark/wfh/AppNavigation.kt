package com.dark.wfh

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onProductClick = { product ->
                val encodedTitle = URLEncoder.encode(product.title, StandardCharsets.UTF_8.toString())
                val encodedDesc = URLEncoder.encode(product.description, StandardCharsets.UTF_8.toString())
                val encodedUrl = URLEncoder.encode(product.images.firstOrNull() ?: product.thumbnail, StandardCharsets.UTF_8.toString())

                navController.navigate(
                    "details/$encodedTitle/$encodedDesc/${product.price}/$encodedUrl"
                )
            })
        }
        composable(
            route = "details/{title}/{description}/{price}/{imageUrl}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("price") { type = NavType.FloatType },
                navArgument("imageUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val title = URLDecoder.decode(arguments.getString("title"), StandardCharsets.UTF_8.toString())
            val description = URLDecoder.decode(arguments.getString("description"), StandardCharsets.UTF_8.toString())
            val imageUrl = URLDecoder.decode(arguments.getString("imageUrl"), StandardCharsets.UTF_8.toString())

            DetailsScreen(
                title = title,
                description = description,
                price = arguments.getFloat("price").toDouble(),
                imageUrl = imageUrl,
                onBack = { navController.popBackStack() }
            )
        }
    }
}