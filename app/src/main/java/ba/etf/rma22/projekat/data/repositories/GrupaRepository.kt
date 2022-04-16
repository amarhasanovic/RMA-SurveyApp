package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.grupe
import ba.etf.rma22.projekat.data.models.Grupa

object GrupaRepository {
    fun getGroupsByIstrazivanje (nazivIstrazivanja: String) : List<Grupa> {
        return grupe().filter { it.nazivIstrazivanja == nazivIstrazivanja }
    }
}