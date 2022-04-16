package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.Korisnik
import java.util.*

private val korisnik = Korisnik("Amar Hasanović", mutableListOf(
    ankete()[1], ankete()[6]
)
)

fun ankete() : List<Anketa> {
    return listOf(
        Anketa("Anketa 1","Istraživanje broj 1", getDate(4, 3, 2022), getDate(12, 4, 2022), getDate(10, 4, 2022), 1, "Grupa 1", 1f),
        Anketa("Anketa 1","Istraživanje broj 1", getDate(10, 6, 2022), getDate(30, 4, 2022), null, 1, "Grupa 1", 0f),
        Anketa("Anketa 2","Istraživanje broj 1", getDate(10, 6, 2022), getDate(30, 4, 2022), null, 1, "Grupa 2", 0f),
        Anketa("Anketa 2","Istraživanje broj 1", getDate(10, 6, 2022), getDate(30, 4, 2022), null, 1, "Grupa 2", 0f),

        Anketa("Anketa 1","Istraživanje broj 2", getDate(25, 1, 2022), getDate(20, 2, 2022), null, 2, "Grupa 1", 0.23f),
        Anketa("Anketa 1","Istraživanje broj 2", getDate(10, 6, 2022), getDate(15, 5, 2022), null, 2, "Grupa 1", 0f),
        Anketa("Anketa 2","Istraživanje broj 2", getDate(31, 3, 2022), getDate(10, 6, 2022), null, 2, "Grupa 2", 0.4f),
        Anketa("Anketa 2","Istraživanje broj 2", getDate(31, 3, 2022), getDate(10, 6, 2022), null, 2, "Grupa 2", 0f),

        Anketa("Anketa 1","Istraživanje broj 3", getDate(11, 3, 2022), getDate(20, 4, 2022), getDate(14, 4, 2022), 3, "Grupa 1", 1f),
        Anketa("Anketa 1","Istraživanje broj 3", getDate(11, 3, 2022), getDate(20, 4, 2022), getDate(14, 4, 2022), 3, "Grupa 1", 1f),
        Anketa("Anketa 2","Istraživanje broj 3", getDate(11, 3, 2022), getDate(20, 4, 2022), getDate(14, 4, 2022), 3, "Grupa 2", 1f),
        Anketa("Anketa 2","Istraživanje broj 3", getDate(4, 3, 2022), getDate(30, 3, 2022), null, 3, "Grupa 2", 0.5f),

        Anketa("Anketa 1","Istraživanje broj 4", getDate(11, 3, 2022), getDate(20, 4, 2022), getDate(14, 2, 2022), 4, "Grupa 1", 1f),
        Anketa("Anketa 1","Istraživanje broj 4", getDate(11, 3, 2022), getDate(20, 4, 2022), getDate(14, 3, 2022), 4, "Grupa 1", 1f),
        Anketa("Anketa 2","Istraživanje broj 4", getDate(11, 3, 2022), getDate(20, 4, 2022), getDate(14, 4, 2022), 4, "Grupa 2", 1f),
        Anketa("Anketa 2","Istraživanje broj 4", getDate(4, 3, 2022), getDate(30, 3, 2022), null, 4, "Grupa 2", 0.5f)
    )
}

fun korisnik() : Korisnik {
    return korisnik
}

fun getDate(day: Int, month: Int, year: Int) : Date {
    val cal: Calendar = Calendar.getInstance()
    cal.set(year, month-1, day)
    return cal.time
}