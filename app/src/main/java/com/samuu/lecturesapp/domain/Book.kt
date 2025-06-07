package com.samuu.lecturesapp.domain

data class Book(
    var title: String,
    var author: String,
    var imgId: Int,
    var description: String,
    var category: String,
    var pages: Int,
    var readPages: Int,
    var status : String,
    var dateStart: String,
    var dateEnd: String
)
{
    // Constructor secundario sin argumentos.
    constructor() : this("","",0,"","",0,0,"","","")

    // Lógica para verificar si el libro ha sido marcado como "Completado".
    fun isAlreadyFinished() : Boolean {
        return status == "Completado"
    }
}