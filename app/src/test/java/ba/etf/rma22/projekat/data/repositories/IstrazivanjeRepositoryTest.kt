package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Istrazivanje
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItem
import org.hamcrest.Matchers.hasProperty
import org.junit.Assert.assertEquals
import org.junit.Before
import org.hamcrest.CoreMatchers.`is` as Is

import org.junit.Test

class IstrazivanjeRepositoryTest {
    private lateinit var istrazivanjeRepository: IstrazivanjeRepository
    @Before
    fun setUp(){
        istrazivanjeRepository = IstrazivanjeRepository
    }

    @Test
    fun getIstrazivanjeByGodina() {
        val istrazivanja = istrazivanjeRepository.getIstrazivanjeByGodina(3)
        assertEquals(istrazivanja.size, 1)
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istraživanje broj 3"))))
    }

    @Test
    fun getALl() {
        val istrazivanja = istrazivanjeRepository.getALl()
        assertEquals(istrazivanja.size, 5)
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istraživanje broj 1"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istraživanje broj 2"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istraživanje broj 3"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istraživanje broj 4"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istraživanje broj 5"))))
    }

    @Test
    fun getUpisani() {
        val istrazivanja = istrazivanjeRepository.getUpisani()
        assertEquals(istrazivanja.size, 2)
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istraživanje broj 1"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istraživanje broj 2"))))
    }
}