package com.samuu.lecturesapp.repository

import com.samuu.lecturesapp.domain.User

data class UserRepository(
    var users: MutableList<User> = mutableListOf(
        User(1, "admin", "admin", mutableListOf()),
        User(2, "user", "user", mutableListOf())
    )
)
{
    // Lógica para añadir un nuevo usuario a la lista de usuarios.
    fun addUser(user: User) {
        user.id = getLastUser()?.id?.plus(1) ?: 1
        users.add(user)
    }

    // Lógica para buscar un usuario por su nombre.
    fun getUserByName(name: String): User? {
        // Utiliza la función 'find' de Kotlin para buscar el primer usuario cuyo nombre coincide.
        return users.find { it.name == name }
    }

    // Lógica para buscar un usuario por su ID.
    fun getUserById(id: Int): User? {
        // Utiliza la función 'find' de Kotlin para buscar el primer usuario cuyo ID coincide.
        return users.find { it.id == id }
    }

    // Lógica para eliminar un usuario de la lista.
    fun removeUser(user: User) {
        users.remove(user)
    }

    // Lógica para actualizar un usuario existente en la lista.
    fun updateUser(user: User) {
        // Encuentra el índice del usuario en la lista basándose en su ID.
        val index = users.indexOfFirst { it.id == user.id }
        // Si el usuario se encuentra (el índice no es -1), lo actualiza en esa posición.
        if (index != -1) {
            users[index] = user
        }
    }

    // Lógica para obtener el último usuario de la lista.
    fun getLastUser(): User? {
        // Utiliza 'lastOrNull' para obtener el último elemento de la lista, o nulo si la lista está vacía.
        return users.lastOrNull()
    }
}