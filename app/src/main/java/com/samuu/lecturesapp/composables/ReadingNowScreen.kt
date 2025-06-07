package com.samuu.lecturesapp.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
fun ReadingNowScreen(navController: NavController, user: User) {
    // Manejo del estado del usuario para observar los cambios en la lista de lectura.
    val userState = remember { mutableStateOf(user) }
    val currentUser by userState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.reading_now_title)) },
                navigationIcon = {
                    // Lógica para volver a la pantalla anterior al presionar el botón de retroceso.
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
                // Lógica de navegación: al hacer clic en un ítem de la barra inferior, se navega a la ruta correspondiente.
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
                    selected = true,
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Lógica para mostrar cada libro que el usuario está leyendo en una tarjeta.
            items(currentUser.reading) { book ->
                ReadingBookCard(book = book, navController = navController, user = currentUser) { updatedPages ->
                    // Lógica para actualizar las páginas leídas de un libro específico:
                    // 1. Crea una copia mutable de la lista de lectura del usuario.
                    val updatedReadingList = currentUser.reading.toMutableList().apply {
                        // 2. Encuentra el índice del libro que se va a actualizar.
                        val index = indexOf(book)
                        // 3. Si el libro se encuentra, actualiza sus páginas leídas.
                        if (index != -1) {
                            this[index] = this[index].copy(readPages = updatedPages)
                        }
                    }
                    // 4. Actualiza el estado del usuario con la nueva lista de lectura.
                    userState.value = currentUser.copy(reading = updatedReadingList)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingBookCard(book: Book, user: User, navController: NavController, onPagesReadChange: (Int) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Muestra el título del libro.
                Text(stringResource(R.string.title_label) + " ${book.title}", style = MaterialTheme.typography.titleLarge)

            }
            // Muestra el autor del libro.
            Text(stringResource(R.string.author_label) + " ${book.author}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Calcula el progreso de lectura del libro (páginas leídas / total de páginas).
            val progress = if (book.pages > 0) (book.readPages.toFloat() / book.pages) else 0f
            Text(stringResource(R.string.progress_label, book.pages), style = MaterialTheme.typography.bodySmall)
            // Muestra una barra de progreso visual.
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondary
            )
            // Muestra el porcentaje de progreso.
            Text(stringResource(R.string.percentage_progress, (progress * 100).toInt()), style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.pages_read_label), style = MaterialTheme.typography.bodyMedium)
                // Campo de entrada para que el usuario actualice las páginas leídas.
                var pagesReadInput by remember { mutableStateOf(book.readPages.toString()) }
                OutlinedTextField(
                    value = pagesReadInput,
                    onValueChange = {
                        pagesReadInput = it
                        // Convierte el texto de entrada a un número entero; si no es válido, usa 0.
                        val newPages = it.toIntOrNull() ?: 0
                        // Llama a la función onPagesReadChang' para actualizar el estado del libro.
                        onPagesReadChange(newPages)
                    },
                    modifier = Modifier.width(100.dp),
                    singleLine = true,
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            // Muestra las páginas restantes por leer.
            Text(stringResource(R.string.pages_remaining_label, book.pages - book.readPages), style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = { user.reading.remove(book)
                    navController.navigate(Routes.READING_NOW)},
                modifier = Modifier.fillMaxWidth()
            ){
                Text(stringResource(R.string.delete_button))
            }
        }
    }
}