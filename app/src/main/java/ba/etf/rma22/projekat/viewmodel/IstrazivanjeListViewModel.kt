package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository

class IstrazivanjeListViewModel {
    fun getIstrazivanjeByGodina(godina: Int) : List<Istrazivanje> {
        return IstrazivanjeRepository.getIstrazivanjeByGodina(godina)
    }
    fun getAll() : List<Istrazivanje> {
        return IstrazivanjeRepository.getALl()
    }
    fun getUpisani() : List<Istrazivanje> {
        return IstrazivanjeRepository.getUpisani()
    }
}