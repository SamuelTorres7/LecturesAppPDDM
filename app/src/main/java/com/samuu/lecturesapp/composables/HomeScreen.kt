package com.samuu.lecturesapp.composables

import com.samuu.lecturesapp.R
import com.samuu.lecturesapp.Routes
import com.samuu.lecturesapp.ui.theme.SecondaryColor
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.samuu.lecturesapp.ui.theme.LecturesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.home_screen_title)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SecondaryColor, titleContentColor = MaterialTheme.colorScheme.onSecondary)
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = SecondaryColor
            ) {
                // Lógica de navegación: cuando se hace clic en un ítem de la barra inferior, se navega a la ruta correspondiente.
                // El ítem "Home" está seleccionado por defecto en esta pantalla.
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = stringResource(R.string.nav_home)) },
                    label = { Text(stringResource(R.string.nav_home)) },
                    selected = true,
                    onClick = { navController.navigate(Routes.HOME) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.List, contentDescription = stringResource(R.string.nav_reading_now)) },
                    label = { Text(stringResource(R.string.nav_reading_now)) },
                    selected = false,
                    onClick = { navController.navigate(Routes.READING_NOW) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Star, contentDescription = stringResource(R.string.nav_read_books)) },
                    label = { Text(stringResource(R.string.nav_read_books)) },
                    selected = false,
                    onClick = { navController.navigate(Routes.READ_BOOKS) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f)
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Settings, contentDescription = stringResource(R.string.nav_settings)) },
                    label = { Text(stringResource(R.string.nav_settings)) },
                    selected = false,
                    onClick = { navController.navigate(Routes.SETTINGS) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f)
                    )
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Lógica para mostrar un área con el título "LECTURAS" en el centro.
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(stringResource(R.string.lectures_title), style = MaterialTheme.typography.headlineLarge)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                // Lógica de navegación: al hacer clic en este botón, se navega a la pantalla de lecturas recomendadas.
                onClick = { navController.navigate(Routes.RECOMMENDED_READINGS) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(stringResource(R.string.recommended_button))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                // Lógica de navegación: al hacer clic en este botón, se navega a la pantalla para añadir una nueva lectura.
                onClick = { navController.navigate(Routes.ADD_NEW_READING) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(stringResource(R.string.add_new_button))
                Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_new_content_description))
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    LecturesAppTheme {
        HomeScreen(navController = rememberNavController())
    }
}