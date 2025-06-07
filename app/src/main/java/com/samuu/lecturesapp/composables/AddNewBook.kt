package com.samuu.lecturesapp.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samuu.lecturesapp.R
import com.samuu.lecturesapp.Routes
import com.samuu.lecturesapp.domain.Book
import com.samuu.lecturesapp.domain.User
import com.samuu.lecturesapp.ui.theme.SecondaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewReadingScreen(navController: NavController, user: User) {
    // Estas variables almacenan el estado de los campos de texto, lo que el usuario está escribiendo.
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var totalPages by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.add_new_reading_title)) },
                navigationIcon = {
                    // Lógica para volver a la pantalla anterior cuando se presiona el botón de retroceso.
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SecondaryColor, titleContentColor = MaterialTheme.colorScheme.onSecondary)
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = SecondaryColor
            ) {
                // Lógica de navegación: cuando se hace clic en un ítem de la barra inferior, se navega a la ruta correspondiente.
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = stringResource(R.string.nav_home)) },
                    label = { Text(stringResource(R.string.nav_home)) },
                    selected = false,
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Lógica de actualización de los campos de texto, onValueChange actualiza la variable de estado
            // cada vez que el usuario escribe, reflejando el texto en la interfaz.
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(R.string.title_hint)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text(stringResource(R.string.author_hint)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = type,
                onValueChange = { type = it },
                label = { Text(stringResource(R.string.type_label)) },
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = totalPages,
                onValueChange = { totalPages = it },
                label = { Text(stringResource(R.string.pages_label)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Lógica para guardar un nuevo libro:
                    // 1. Recopila los datos ingresados por el usuario.
                    var book = Book()
                    book.title = title
                    book.author = author
                    book.category = type
                    // 2. Convierte el número de páginas a un entero.
                    book.pages = totalPages.toInt()
                    // 3. Añade el libro a la lista de lecturas del usuario.
                    user.reading.add(book)
                    // 4. Navega de regreso a la pantalla anterior.
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(stringResource(R.string.save_button))
            }
        }
    }
}