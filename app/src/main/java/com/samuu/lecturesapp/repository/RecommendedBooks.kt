package com.samuu.lecturesapp.repository

import com.samuu.lecturesapp.R
import com.samuu.lecturesapp.domain.Book

data class RecommendedBooks(
    var books : MutableList<Book> = mutableListOf(
        Book("Alicia en el pais de las maravillas",
            "Lewis Carroll",
            R.drawable.alice,
            "Una joven llamada Alicia cae por una madriguera de conejo " +
                    "hacia un mundo fantástico y subterráneo poblado por " +
                    "peculiares criaturas antropomórficas.",
            "Fantasía",
            200,
            0,
            "",
            "",
            ""
        ),
        Book("El Castillo en el Aire",
            "Diane Wynne Jones",
            R.drawable.castilloa,
            "El castillo en el aire es una novela de fantasía escrita por Diana Wynne Jones, que forma parte de la serie de Howl. La historia sigue a un joven llamado Abdullah, un " +
                    "comerciante de alfombras que sueña con aventuras y un mundo más allá de su vida cotidiana. Un día, Abdullah encuentra una alfombra mágica que lo transporta a un castillo en el aire, donde se encuentra con la hermosa princesa Flower." +
                    "A medida que Abdullah se adentra en este nuevo mundo, se enfrenta a diversos desafíos, incluyendo un poderoso mago y una serie de intrigas. La novela explora temas de amor, valentía y la búsqueda de la identidad, todo ello envuelto en un ambiente mágico y lleno de sorpresas. Con su característico humor y creatividad, Wynne Jones teje una narrativa cautivadora que atrapa a los lectores en un viaje inolvidable.",
            "Fantasía",
            520,
            0,
            "",
            "",
            ""
        ),
        Book("El Castillo Ambulante",
            "Diane Wynne Jones",
            R.drawable.castillov,
            "Un castillo que viaja alrededor del mundo, un mago vive dentro y se dice que roba el corazon de jovenes bellas",
            "Fantasía",
            570,
            0,
            "",
            "",
            ""
        ),
        Book("Cien Años de Soledad",
            "Gabriel García Marquez",
            R.drawable.cienanios,
            "",
            "Novela",
            120,
            0,
            "",
            "",
            ""
        ),
        Book("Harry Potter y la piedra filosofal",
            "J.K. Rowling",
            R.drawable.harrypotterpf,
            "Harry Potter descubre su magia y asiste a Hogwarts, enfrentando peligros y haciendo amigos.",
            "Novela",
            205,
            0,
            "",
            "",
            ""
        ),
        Book("Los cristales de la galaxia",
            "Jacobo Grinberg",
            R.drawable.cristales,
            "",
            "Psicología",
            720,
            0,
            "",
            "",
            ""
        ),
        Book("Cumbres Borrascosas",
            "Emily Brontë",
            R.drawable.cumbres,
            "Cumbres borrascosas narra la tumultuosa relación entre " +
                    "Heathcliff y Catherine, marcada por amor, venganza y tragedia en los páramos ingleses.",
            "Novela",
            820,
            0,
            "",
            "",
            ""
        ),
        Book("Querido Evan Hansen",
            "Stephenie Meyer",
            R.drawable.deh,
            "Un adolescente lucha con la ansiedad y la soledad tras un trágico malentendido.",
            "Drama",
            520,
            0,
            "",
            "",
            ""
        ),
        Book("El principíto",
            "Antoine de Saint-Exupéry",
            R.drawable.elprincipito,
            "Un joven príncipe viaja por planetas, aprendiendo sobre amor, amistad y la naturaleza humana.",
            "Fantasía",
            520,
            0,
            "",
            "",
            ""
        ),
        Book("Fundamentos de la Experiencia",
            "Jacobo Grinberg",
            R.drawable.experiencia,
            "",
            "Psicología",
            850,
            0,
            "",
            "",
            ""
        ),
        Book("Rayuela",
            "Julio Cortázar",
            R.drawable.rayuela,
            "Horacio Oliveira en su búsqueda de sentido y amor en París y Buenos Aires.",
            "Novela",
            520,
            0,
            "",
            "",
            ""
        ),
        Book("La Construcción de la realidad",
            "Jacobo Grinberg",
            R.drawable.realidad,
            "",
            "Psicología",
            720,
            0,
            "",
            "",
            ""
        )
    )
)
{
    // Lógica para encontrar un libro por su título en la lista de libros recomendados.
    fun find(title: String) : Book? {
        // Itera sobre cada libro en la lista.
        for(b in books) {
            // Si el título del libro actual coincide con el título buscado, devuelve ese libro.
            if(b.title == title)
                return b
        }
        // Si no se encuentra ningún libro con el título dado, devuelve nulo.
        return null
    }
}