package com.example.juegopreguntas.bbdd

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.juegopreguntas.Aplicacion

class BBDDPreguntas():SQLiteOpenHelper(Aplicacion.contexto, Aplicacion.DB,null,Aplicacion.VERSION) {
    private val crearBBDD = arrayOf(
        // Crear tablas
        "CREATE TABLE pregunta (" +
                "id INTEGER PRIMARY KEY," +
                "texto TEXT NOT NULL" +
                ");",
        "CREATE TABLE respuesta_verdadera (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "texto TEXT NOT NULL," +
                "pregunta_id INTEGER NOT NULL," +
                "FOREIGN KEY (pregunta_id) REFERENCES pregunta (id) ON DELETE CASCADE" +
                ");",
        "CREATE TABLE respuesta_falsa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "texto TEXT NOT NULL," +
                "pregunta_id INTEGER NOT NULL," +
                "FOREIGN KEY (pregunta_id) REFERENCES pregunta (id) ON DELETE CASCADE" +
                ");",
        "CREATE TABLE argumentario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "texto TEXT NOT NULL," +
                "pregunta_id INTEGER NOT NULL," +
                "FOREIGN KEY (pregunta_id) REFERENCES pregunta (id) ON DELETE CASCADE" +
                ");",
        "CREATE TABLE partidas("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL,"+
                "fecha TEXT NOT NULL,"+
                "puntuacion INTEGER NOT NULL"+
                ");",

        // Índices
        "CREATE INDEX idx_respuesta_verdadera_pregunta_id ON respuesta_verdadera(pregunta_id);",
        "CREATE INDEX idx_respuesta_falsa_pregunta_id ON respuesta_falsa(pregunta_id);",
        "CREATE INDEX idx_argumentario_pregunta_id ON argumentario(pregunta_id);",

        // Insertar preguntas, respuestas correctas e incorrectas, e historias
        // Preguntas de fútbol
        "INSERT INTO pregunta (id, texto) VALUES (0, '¿Quién ganó el primer Mundial de Fútbol en 1930?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Uruguay', 0);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Brasil', 0);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('En el primer Mundial, organizado en Uruguay, el país anfitrión venció a Argentina 4-2 en la final, marcando un inicio glorioso para los campeonatos mundiales.', 0);",

        "INSERT INTO pregunta (id, texto) VALUES (1, '¿Qué jugador es conocido como el \"Rey del Fútbol\"?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Pelé', 1);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Diego Forlán', 1);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Pelé, con sus tres Mundiales ganados (1958, 1962, 1970), se convirtió en un ícono global, redefiniendo lo que significa ser una estrella en el fútbol.', 1);",

        "INSERT INTO pregunta (id, texto) VALUES (2, '¿Quién anotó el famoso \"Gol del Siglo\" en 1986?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Diego Maradona', 2);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Michel Platini', 2);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('En los cuartos de final del Mundial de 1986 contra Inglaterra, Maradona dribló a medio equipo inglés antes de marcar uno de los goles más recordados de la historia.', 2);",

        "INSERT INTO pregunta (id, texto) VALUES (3, '¿En qué año se fundó la FIFA?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('1904', 3);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('1924', 3);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('La FIFA fue creada en París para organizar y regular las competiciones internacionales, sentando las bases del fútbol global moderno.', 3);",

        "INSERT INTO pregunta (id, texto) VALUES (4, '¿Qué selección tiene más títulos mundiales?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Brasil', 4);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Alemania', 4);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Con cinco Mundiales en su palmarés (1958, 1962, 1970, 1994, 2002), Brasil lidera la lista gracias a generaciones doradas de jugadores legendarios.', 4);",

        "INSERT INTO pregunta (id, texto) VALUES (5, '¿Cuál fue el primer equipo en ganar la Champions League?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Real Madrid', 5);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('AC Milan', 5);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('En 1956, el Real Madrid venció al Stade de Reims en la primera edición del torneo, iniciando su histórica relación con la competición.', 5);",

        "INSERT INTO pregunta (id, texto) VALUES (6, '¿Qué equipo logró el \"Maracanazo\" en 1950?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Uruguay', 6);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Italia', 6);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('En un histórico desenlace, Uruguay venció a Brasil 2-1 en el Estadio Maracaná, un evento que dejó a miles de brasileños en lágrimas.', 6);",

        "INSERT INTO pregunta (id, texto) VALUES (7, '¿Qué país ganó el Mundial en 1998?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Francia', 7);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Alemania', 7);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Francia ganó su primer Mundial como país anfitrión en 1998, liderados por Zinédine Zidane, quien marcó dos goles en la final.', 7);",

        "INSERT INTO pregunta (id, texto) VALUES (8, '¿Qué club tiene más títulos de Premier League?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Manchester United', 8);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Liverpool', 8);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Con más de 20 títulos, el Manchester United domina la Premier League, especialmente durante la era de Alex Ferguson.', 8);",

        "INSERT INTO pregunta (id, texto) VALUES (9, '¿Quién es el máximo goleador en la historia de los mundiales?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Miroslav Klose', 9);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Ronaldo', 9);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Klose, jugador alemán, marcó 16 goles en cuatro Copas del Mundo, superando el récord de Ronaldo en 2014.', 9);",

        "INSERT INTO pregunta (id, texto) VALUES (10, '¿Qué selección europea ganó por primera vez un Mundial?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Italia', 10);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Alemania', 10);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Italia se coronó campeona del mundo en 1934, siendo la primera selección europea en lograrlo.', 10);",

        "INSERT INTO pregunta (id, texto) VALUES (11, '¿Qué jugador ganó 7 Balones de Oro?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Lionel Messi', 11);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Cristiano Ronaldo', 11);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Lionel Messi se convirtió en el jugador con más Balones de Oro, ganando su séptimo en 2021.', 11);",

        "INSERT INTO pregunta (id, texto) VALUES (12, '¿Qué equipo tiene más títulos de la Copa Libertadores?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Independiente', 12);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('River Plate', 12);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('El club argentino Independiente ha ganado la Copa Libertadores en 7 ocasiones, siendo el máximo ganador.', 12);",

        "INSERT INTO pregunta (id, texto) VALUES (13, '¿En qué año se jugó el primer Mundial de Fútbol Femenino?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('1991', 13);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('1987', 13);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('El primer Mundial femenino tuvo lugar en China en 1991, con Estados Unidos ganando el título inaugural.', 13);",

        "INSERT INTO pregunta (id, texto) VALUES (14, '¿Qué selección ganó el Mundial de 2010?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('España', 14);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Países Bajos', 14);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('España ganó su primer Mundial en 2010, derrotando a los Países Bajos 1-0 con un gol de Andrés Iniesta en la prórroga.', 14);",

        "INSERT INTO pregunta (id, texto) VALUES (15, '¿Cuál es el estadio más grande del mundo?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Rungrado 1 de Mayo', 15);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Maracaná', 15);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('El Estadio Rungrado 1 de Mayo, en Corea del Norte, tiene una capacidad de más de 114,000 espectadores.', 15);",

        "INSERT INTO pregunta (id, texto) VALUES (16, '¿Qué jugador marcó más goles en una temporada de la Premier League?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Erling Haaland', 16);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Alan Shearer', 16);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Haaland rompió récords al marcar 36 goles en una sola temporada de la Premier League en 2023.', 16);",

        "INSERT INTO pregunta (id, texto) VALUES (17, '¿Qué selección ganó la Eurocopa de 2004?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Grecia', 17);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Portugal', 17);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('En una de las mayores sorpresas en la historia del fútbol, Grecia ganó la Eurocopa 2004, derrotando a Portugal en la final.', 17);",

        "INSERT INTO pregunta (id, texto) VALUES (18, '¿Qué país fue anfitrión de la Copa América Centenario en 2016?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Estados Unidos', 18);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Argentina', 18);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('La Copa América Centenario se celebró en Estados Unidos, con Chile llevándose el título al vencer a Argentina en los penales.', 18);",

        "INSERT INTO pregunta (id, texto) VALUES (19, '¿Qué portero tiene el récord de porterías a cero en la Premier League?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Petr Čech', 19);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('David De Gea', 19);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Petr Čech tiene el récord de 202 porterías a cero en la Premier League, logrado durante su tiempo en el Chelsea y el Arsenal.', 19);",

        "INSERT INTO pregunta (id, texto) VALUES (20, '¿Quién es el máximo goleador de la historia del FC Barcelona?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Lionel Messi', 20);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Luis Suárez', 20);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Lionel Messi anotó más de 670 goles para el FC Barcelona, convirtiéndose en su máximo goleador histórico.', 20);",

        "INSERT INTO pregunta (id, texto) VALUES (21, '¿Qué equipo ganó la Champions League 2020?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Bayern Múnich', 21);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('PSG', 21);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('El Bayern Múnich ganó la Champions League 2020 al derrotar 1-0 al PSG en la final.', 21);",

        "INSERT INTO pregunta (id, texto) VALUES (22, '¿Qué selección tiene más títulos de la Copa América?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Uruguay', 22);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Brasil', 22);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Uruguay ha ganado la Copa América en 15 ocasiones, superando a Argentina y Brasil.', 22);",

        "INSERT INTO pregunta (id, texto) VALUES (23, '¿Qué equipo tiene más títulos de la Premier League?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Manchester United', 23);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Liverpool', 23);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('El Manchester United ha ganado 20 títulos de liga, incluyendo 13 desde el inicio de la Premier League en 1992.', 23);",

        "INSERT INTO pregunta (id, texto) VALUES (24, '¿Quién ganó la Bota de Oro del Mundial 2014?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('James Rodríguez', 24);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Lionel Messi', 24);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('James Rodríguez, de Colombia, fue el máximo goleador del Mundial 2014 con 6 goles.', 24);",

        "INSERT INTO pregunta (id, texto) VALUES (25, '¿Qué país ha sido anfitrión de más Mundiales masculinos?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Italia', 25);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Brasil', 25);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Italia ha sido anfitrión del Mundial masculino en dos ocasiones: 1934 y 1990.', 25);",

        "INSERT INTO pregunta (id, texto) VALUES (26, '¿Quién es el máximo goleador de la historia de la Serie A italiana?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Silvio Piola', 26);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Francesco Totti', 26);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Silvio Piola anotó 274 goles en la Serie A, un récord que se mantiene desde la década de 1950.', 26);",

        "INSERT INTO pregunta (id, texto) VALUES (27, '¿Qué equipo ganó la Copa del Mundo 1998?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Francia', 27);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Brasil', 27);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Francia ganó el Mundial de 1998 en casa, derrotando a Brasil 3-0 en la final.', 27);",

        "INSERT INTO pregunta (id, texto) VALUES (28, '¿En qué país se jugó el Mundial 2002?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Corea del Sur y Japón', 28);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Alemania', 28);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('El Mundial de 2002 fue el primero organizado por dos países: Corea del Sur y Japón.', 28);",

        "INSERT INTO pregunta (id, texto) VALUES (29, '¿Qué selección ganó el Mundial Sub-20 en 2023?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Uruguay', 29);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Italia', 29);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Uruguay ganó el Mundial Sub-20 en 2023, derrotando a Italia 1-0 en la final.', 29);",

        "INSERT INTO pregunta (id, texto) VALUES (30, '¿Quién fue nombrado mejor jugador del Mundial 2010?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Diego Forlán', 30);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Andrés Iniesta', 30);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Diego Forlán recibió el Balón de Oro como mejor jugador del Mundial 2010 por su destacada actuación.', 30);",

        "INSERT INTO pregunta (id, texto) VALUES (31, '¿Cuál es el único equipo invicto en ganar la Premier League?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Arsenal', 31);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Manchester City', 31);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('El Arsenal ganó la Premier League 2003-04 sin perder un solo partido, ganándose el apodo de ''Los Invencibles''.', 31);",

        "INSERT INTO pregunta (id, texto) VALUES (32, '¿Qué jugador ganó el Mundial de Clubes con más equipos?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Cristiano Ronaldo', 32);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Toni Kroos', 32);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Cristiano Ronaldo ha ganado el Mundial de Clubes con dos equipos diferentes: Manchester United y Real Madrid.', 32);",

        "INSERT INTO pregunta (id, texto) VALUES (33, '¿Qué selección ganó el Mundial 2006?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Italia', 33);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Francia', 33);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Italia ganó el Mundial 2006 al derrotar a Francia en una tanda de penales tras empatar 1-1.', 33);",

        "INSERT INTO pregunta (id, texto) VALUES (34, '¿Qué país ganó el Mundial de Fútbol 2018?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Francia', 34);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Croacia', 34);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Francia ganó el Mundial 2018 en Rusia, venciendo a Croacia 4-2 en la final.', 34);",

        "INSERT INTO pregunta (id, texto) VALUES (35, '¿Quién es el máximo goleador de la historia de la Copa del Mundo?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Marta', 35);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Miroslav Klose', 35);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Miroslav Klose es el máximo goleador histórico de la Copa del Mundo con 16 goles, superando a Ronaldo Nazário.', 35);",

        "INSERT INTO pregunta (id, texto) VALUES (36, '¿Qué equipo ganó la última Copa América masculina en 2021?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Argentina', 36);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Brasil', 36);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Argentina ganó la Copa América 2021 tras vencer a Brasil 1-0 en la final en el Maracanã.', 36);",

        "INSERT INTO pregunta (id, texto) VALUES (37, '¿Quién es el máximo goleador histórico de la selección argentina?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Lionel Messi', 37);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Gabriel Batistuta', 37);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Lionel Messi es el máximo goleador histórico de la selección argentina con más de 100 goles en su carrera internacional.', 37);",

        "INSERT INTO pregunta (id, texto) VALUES (38, '¿Qué país es el único que ha ganado tres Copas del Mundo consecutivas?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Brasil', 38);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Italia', 38);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Brasil es el único país que ha ganado tres Copas del Mundo consecutivas, en 1958, 1962 y 1970.', 38);",

        "INSERT INTO pregunta (id, texto) VALUES (39, '¿En qué año se fundó la FIFA?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('1904', 39);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('1920', 39);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('La FIFA fue fundada en 1904 en París, Francia, como la organización encargada de supervisar el fútbol mundial.', 39);",

        "INSERT INTO pregunta (id, texto) VALUES (40, '¿Qué selección nacional ganó la Eurocopa 2020?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Italia', 40);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Inglaterra', 40);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Italia ganó la Eurocopa 2020 al vencer a Inglaterra en la final en Wembley, Londres, por penales.', 40);",

        "INSERT INTO pregunta (id, texto) VALUES (41, '¿Quién es el jugador con más Balones de Oro?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Lionel Messi', 41);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Cristiano Ronaldo', 41);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Lionel Messi ha ganado 7 Balones de Oro, más que cualquier otro jugador en la historia.', 41);",

        "INSERT INTO pregunta (id, texto) VALUES (42, '¿Cuál es el estadio con mayor capacidad del mundo?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Rungrado 1 de Mayo', 42);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Melbourne Cricket Ground', 42);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('El estadio Rungrado 1 de Mayo, en Pyongyang, Corea del Norte, tiene una capacidad de 114,000 espectadores, siendo el mayor del mundo.', 42);",

        "INSERT INTO pregunta (id, texto) VALUES (43, '¿Quién fue el máximo goleador de la Copa Mundial 2014?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('James Rodríguez', 43);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Lionel Messi', 43);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('James Rodríguez, de Colombia, fue el máximo goleador del Mundial 2014 con 6 goles.', 43);",

        "INSERT INTO pregunta (id, texto) VALUES (44, '¿Qué selección ganó la Copa del Mundo 1966?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Inglaterra', 44);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Brasil', 44);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Inglaterra ganó la Copa del Mundo 1966, celebrada en casa, al derrotar a Alemania Federal 4-2 en la final tras tiempo extra.', 44);",

        "INSERT INTO pregunta (id, texto) VALUES (45, '¿Quién es el máximo goleador de la historia del Barcelona?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Lionel Messi', 45);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('César Rodríguez', 45);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Lionel Messi es el máximo goleador del Barcelona, con 672 goles en competiciones oficiales.', 45);",

        "INSERT INTO pregunta (id, texto) VALUES (46, '¿Qué equipo tiene más títulos de la Copa del Rey?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Barcelona', 46);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Real Madrid', 46);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('El Barcelona ha ganado la Copa del Rey en 31 ocasiones, más que cualquier otro equipo.', 46);",

        "INSERT INTO pregunta (id, texto) VALUES (47, '¿Quién fue el máximo goleador de la Copa América 2019?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Eduardo Vargas', 47);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Lionel Messi', 47);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Eduardo Vargas, de Chile, fue el máximo goleador de la Copa América 2019 con 6 goles.', 47);",

        "INSERT INTO pregunta (id, texto) VALUES (48, '¿Qué selección ganó el Mundial Femenino en 2023?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('España', 48);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('Inglaterra', 48);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('España logró su primer Mundial Femenino en 2023, con una victoria 1-0 sobre Inglaterra en la final.', 48);",

        "INSERT INTO pregunta (id, texto) VALUES (49, '¿Qué club ha ganado más títulos de la Serie A en Italia?');",
        "INSERT INTO respuesta_verdadera (texto, pregunta_id) VALUES ('Juventus', 49);",
        "INSERT INTO respuesta_falsa (texto, pregunta_id) VALUES ('AC Milan', 49);",
        "INSERT INTO argumentario (texto, pregunta_id) VALUES ('Con más de 35 títulos, la Juventus lidera el palmarés de la liga italiana.', 49);"
    )

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        db?.execSQL("PRAGMA foreign_keys = ON;")
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        crearBBDD.forEach { sql ->
            p0?.execSQL(sql)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?,  oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) {
            val dropTables = arrayOf(
                "DROP TABLE IF EXISTS respuesta_verdadera;",
                "DROP TABLE IF EXISTS respuesta_falsa;",
                "DROP TABLE IF EXISTS argumentario;",
                "DROP TABLE IF EXISTS pregunta;",
                "DROP TABLE IF EXISTS partida;"
            )
            dropTables.forEach { sql ->
                p0?.execSQL(sql)
            }
            onCreate(p0)
        }

    }


}