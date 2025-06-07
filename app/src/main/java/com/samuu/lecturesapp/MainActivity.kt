package com.samuu.lecturesapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samuu.lecturesapp.ui.theme.LecturesAppTheme
import com.samuu.lecturesapp.composables.*
import com.samuu.lecturesapp.domain.User
import com.samuu.lecturesapp.repository.RecommendedBooks
import com.samuu.lecturesapp.repository.UserRepository

// Rutas de navegacion de la aplicacion.
object Routes {
    const val LOGIN = "login"
    const val HOME = "home"
    const val RECOMMENDED_READINGS = "recommendedReadings"
    const val RECOMMENDED_READING_DETAIL = "recommendedReadingDetail/{bookId}"
    const val READING_NOW = "readingNow"
    const val READ_BOOKS = "readBooks"
    const val ADD_NEW_READING = "addNewReading"
    const val SETTINGS = "settings"
    const val REGISTER = "registerNewUser"
}

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        enableEdgeToEdge()
        setContent {
            LecturesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        var user by remember {mutableStateOf(User(0,"","", mutableListOf()))}
        var users by remember { mutableStateOf(UserRepository()) }
        var books by remember { mutableStateOf(RecommendedBooks()) }
        NavHost(navController = navController, startDestination = Routes.LOGIN) {
            composable(Routes.LOGIN) {
                user = LoginScreen(navController = navController, usRepo = users)
            }
            composable(Routes.HOME) {
                if(user.id != 0){
                    HomeScreen(navController = navController)
                }
                else{
                    user = LoginScreen(navController = navController, usRepo = users)
                }
            }
            composable(Routes.REGISTER) {
                RegisterScreen(navController = navController, userRepo = users)
            }
            composable(Routes.RECOMMENDED_READINGS) {
                if(user.id != 0){
                    RecommendedReadingsScreen(navController = navController, recommendedBooks = books)
                }
                else{
                    user = LoginScreen(navController = navController, usRepo = users)
                }
            }
            composable(Routes.RECOMMENDED_READING_DETAIL) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId")
                RecommendedDetailsScreen(navController = navController, bookId = bookId,user)
            }
            composable(Routes.READING_NOW) {
                if(user.id != 0){
                    ReadingNowScreen(navController = navController, user = user)
                } else {
                    user = LoginScreen(navController = navController, usRepo = users)
                }
            }
            composable(Routes.READ_BOOKS) {
                if(user.id != 0){
                    ReadBooksScreen(navController = navController, user = user)
                } else {
                    user = LoginScreen(navController = navController, usRepo = users)
                }
            }
            composable(Routes.ADD_NEW_READING) {
                if(user.id != 0){
                    AddNewReadingScreen(navController = navController,user = user)
                } else {
                    user = LoginScreen(navController = navController, usRepo = users)
                }
            }
            composable(Routes.SETTINGS) {
                if(user.id != 0){
                    SettingsScreen(navController = navController, user = user, usRepo = users)
                } else {
                    user = LoginScreen(navController = navController, usRepo = users)
                }
            }
        }
    }
}

