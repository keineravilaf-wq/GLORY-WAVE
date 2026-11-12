package dev.lelonio.square.backend.youtube

/**
 * The home page's own shelves: Christian music only.
 *
 * Instead of YouTube's personalised home, the page is this list, read from
 * the top a few shelves at a time. To change what the home shows, edit the
 * list at the bottom: add, remove or reorder entries and nothing else needs
 * to move.
 *
 * Each entry is one of:
 *  - [Artists]: a row of artist portraits, each found by searching its name.
 *  - [Songs]: a row of songs for a search. [Songs.artist], when set, keeps only
 *    the results credited to that artist, so a search for one name does not
 *    fill the row with someone else's songs.
 *  - [Playlists]: a row of public playlists for a search.
 */
internal object ChristianHome {

    sealed interface Shelf {
        val title: String
    }

    data class Artists(override val title: String, val names: List<String>) : Shelf

    data class Songs(
        override val title: String,
        val query: String,
        val artist: String? = null,
        val strapline: String? = null,
    ) : Shelf

    data class Playlists(override val title: String, val query: String) : Shelf

    /** How many shelves are read per page; the list asks for the next page as it runs out. */
    const val PAGE_SIZE = 4

    private const val TAG = "Música cristiana"

    private fun artist(name: String, query: String = name) =
        Songs(title = name, query = query, artist = name, strapline = TAG)

    val shelves: List<Shelf> = listOf(
        Artists(
            title = "Artistas cristianos",
            names = listOf(
                "Daddy Yankee",
                "Alex Zurdo",
                "Israel Houghton",
                "Christine D'Clario",
                "Marcos Witt",
                "Miel San Marcos",
                "Redimi2",
                "Jesús Adrián Romero",
                "Marcos Barrientos",
                "Evan Craft",
                "Lilly Goodman",
                "Hillsong en Español",
                "Funky",
                "Barak",
                "Averly Morillo",
                "Un Corazón",
            ),
        ),
        Playlists("Música cristiana", "música cristiana"),

        // Daddy Yankee's catalogue is mostly reggaeton; the extra word steers
        // the search to his Christian songs. Adjust the query if it drifts.
        artist("Daddy Yankee", query = "Daddy Yankee cristiano"),
        artist("Alex Zurdo"),
        artist("Israel Houghton"),
        artist("Christine D'Clario"),
        artist("Marcos Witt"),

        Playlists("Adoración en español", "adoración en español"),
        artist("Miel San Marcos"),
        artist("Redimi2"),
        artist("Jesús Adrián Romero"),
        artist("Marcos Barrientos"),

        Playlists("Alabanzas", "alabanzas cristianas"),
        artist("Evan Craft"),
        artist("Lilly Goodman"),
        artist("Hillsong en Español"),
        artist("Funky"),

        Playlists("Rap y trap cristiano", "rap cristiano"),
        artist("Barak"),
        artist("Averly Morillo"),
        artist("Un Corazón"),
        artist("Kike Pavón"),

        Playlists("Para orar", "música cristiana para orar"),
        Playlists("Worship", "worship songs"),
    )
}
