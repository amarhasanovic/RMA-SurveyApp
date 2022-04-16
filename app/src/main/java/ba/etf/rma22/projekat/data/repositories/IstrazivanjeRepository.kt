package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.istrazivanja
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.myIstrazivanja

object IstrazivanjeRepository {
    fun getIstrazivanjeByGodina(godina: Int) : List<Istrazivanje> {
        return istrazivanja().filter { it.godina == godina }
    }
    fun getALl() : List<Istrazivanje> {
        return istrazivanja()
    }
    fun getUpisani() : List<Istrazivanje> {
        return myIstrazivanja()
    }
}