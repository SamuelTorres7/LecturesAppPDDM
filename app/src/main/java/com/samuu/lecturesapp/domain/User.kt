package com.samuu.lecturesapp.domain

data class User(
    var id : Int,
    var name : String,
    var password : String,
    var reading : MutableList<Book>
)
{
    // Constructor secundario sin argumentos.
    constructor() : this(0,"","",mutableListOf())

    // Lógica para obtener una lista de libros que el usuario ha marcado como "Completado".
    fun getReadBooks() : MutableList<Book> {
        var read = mutableListOf<Book>()
        // Itera sobre la lista de libros en "reading".
        for(b in reading){
            // Si el estado del libro es "Completado", lo añade a la nueva lista 'read'.
            if(b.status == "Completado")
                read.add(b)
        }
        return read
    }
}