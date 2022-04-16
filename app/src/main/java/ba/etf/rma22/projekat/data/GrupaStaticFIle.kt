package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.Grupa

fun grupe() : List<Grupa> {
    return listOf(
        Grupa("Grupa 1", "Istraživanje broj 1"),
        Grupa("Grupa 2", "Istraživanje broj 1"),
        Grupa("Grupa 1", "Istraživanje broj 2"),
        Grupa("Grupa 2", "Istraživanje broj 2"),
        Grupa("Grupa 1", "Istraživanje broj 3"),
        Grupa("Grupa 2", "Istraživanje broj 3"),
        Grupa("Grupa 1", "Istraživanje broj 4"),
        Grupa("Grupa 2", "Istraživanje broj 4"),
        Grupa("Grupa 1", "Istraživanje broj 5"),
        Grupa("Grupa 2", "Istraživanje broj 5")
    )
}