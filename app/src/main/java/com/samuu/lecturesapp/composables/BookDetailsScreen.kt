package com.samuu.lecturesapp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samuu.lecturesapp.R
import com.samuu.lecturesapp.Routes // Rutas que usa el NavHost
import com.samuu.lecturesapp.domain.User
import com.samuu.lecturesapp.ui.theme.SecondaryColor
import com.samuu.lecturesapp.repository.RecommendedBooks

var recommendedBooks: RecommendedBooks = RecommendedBooks()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendedDetailsScreen(navController: NavController, bookId: String?, user: User) {
    // Busca los detalles del libro usando el bookId proporcionado.
    val book = recommendedBooks.find(bookId.toString())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.book_details_title)) },
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
        // Comprueba si el libro se encontró (no es nulo).
        book?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Muestra la imagen del libro.
                Image(
                    painter = painterResource(id = it.imgId),
                    contentDescription = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Muestra el título y el autor del libro.
                Text("Título: ${it.title}", style = MaterialTheme.typography.headlineSmall)
                Text("Autor/s: ${it.author}", style = MaterialTheme.typography.bodyLarge)
                // Placeholder para mostrar el número de páginas.
                Text("${stringResource(R.string.pages_label)} ${stringResource(R.string.pages_placeholder)}", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))
                Text(stringResource(R.string.synopsis_label), style = MaterialTheme.typography.titleMedium)
                // Muestra la descripción o sinopsis del libro.
                Text(
                    book.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        // Lógica para añadir el libro a la lista de leyendo ahora del usuario.
                        user.reading.add(it)
                        // Navega a la pantalla de Leyendo Ahora.
                        navController.navigate(Routes.READING_NOW)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

                ) {
                    Text(stringResource(R.string.add_to_reading_button))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        // Lógica para marcar el libro como "completado" y añadirlo a la lista de lecturas del usuario.
                        it.status = "Completado"
                        it.readPages = it.pages
                        user.reading.add(it)
                        // Navega a la pantalla de "Libros Leídos".
                        navController.navigate(Routes.READ_BOOKS)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text(stringResource(R.string.mark_as_read_button))
                }
            }
        } ?: run {
            // Lógica para mostrar un mensaje si el libro no se encuentra.
            Text(stringResource(R.string.book_not_found), modifier = Modifier.padding(16.dp))
        }
    }
}