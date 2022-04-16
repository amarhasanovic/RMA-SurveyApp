package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.ankete
import ba.etf.rma22.projekat.data.korisnik
import ba.etf.rma22.projekat.data.models.Anketa
import java.util.*

object AnketaRepository {
    fun upisiAnketu(anketa: Anketa) {
        korisnik().anketeKorisnika.add(anketa)
    }
    fun getMyAnkete() : List<Anketa> {
        return korisnik().anketeKorisnika
    }
    fun getAll() : List<Anketa> {
        return ankete().sortedBy { it.datumPocetak.time }
    }
    fun getDone() : List<Anketa> {
        return getMyAnkete().filter { (it.progres == 1f) }
    }
    fun getFuture() : List<Anketa> {
        return getMyAnkete().filter { (it.datumKraj > Date()) && (it.progres == 0f) }
    }
    fun getNotTaken() : List<Anketa> {
        return getMyAnkete().filter { (it.datumKraj < Date()) && (it.progres < 1f)}
    }
}