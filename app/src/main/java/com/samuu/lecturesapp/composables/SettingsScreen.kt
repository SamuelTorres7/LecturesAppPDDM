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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samuu.lecturesapp.R
import com.samuu.lecturesapp.Routes
import com.samuu.lecturesapp.domain.User
import com.samuu.lecturesapp.repository.UserRepository
import com.samuu.lecturesapp.ui.theme.SecondaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController, user : User, usRepo: UserRepository) {
    // Estas variables almacenan el estado de los campos de texto, inicializándolos con los datos actuales del usuario.
    var username by remember { mutableStateOf(user.name) }
    var password by remember { mutableStateOf(user.password) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings_screen_title)) },
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
                    selected = true,
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
            // Lógica de actualización del campo de texto de nombre de usuario.
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(stringResource(R.string.username_settings_label)) },
                modifier = Modifier.fillMaxWidth()
            )
            // Lógica de actualización del campo de texto de contraseña.
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.password_settings_label)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Lógica para actualizar los datos del usuario:
                    // 1. Verifica que los campos no estén vacíos.
                    if(username.isNotEmpty() && password.isNotEmpty()) {
                        // 2. Actualiza el nombre y la contraseña del objeto 'user'.
                        user.name = username
                        user.password = password
                        // 3. Llama al repositorio para guardar los cambios del usuario.
                        usRepo.updateUser(user)
                        // 4. Navega de vuelta a la pantalla de inicio de sesión.
                        navController.navigate(Routes.LOGIN)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(stringResource(R.string.update_button))
            }
        }
    }
}