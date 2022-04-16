package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Assert.*
import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Test
import java.util.*

class AnketaRepositoryTest {
    @Test
    fun myAnketa(){
        val mojeAnkete = AnketaRepository.getMyAnkete()
        assertEquals(mojeAnkete.size, 2)
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Istraživanje broj 1"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa 1"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Istraživanje broj 2"))))
        assertThat(mojeAnkete, hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa 1"))))
    }

    @Test
    fun getAll() {
        val ankete = AnketaRepository.getAll()
        assertEquals(ankete.size, 16)
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Istraživanje broj 1"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Istraživanje broj 2"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa 1"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa 2"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("progres", Is(1f))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("progres", Is(0f))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("datumPocetak", greaterThan(Date()))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("datumKraj", greaterThan(Date()))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("datumKraj", lessThan(Date()))))
    }

    @Test
    fun getDone() {
        val ankete = AnketaRepository.getAll()
        for(anketa in ankete){
            assertNotNull(anketa)
        }
    }

    @Test
    fun getFuture() {
        val ankete = AnketaRepository.getFuture()
        for(anketa in ankete) {
            assertThat("Failed", anketa.datumKraj, greaterThanOrEqualTo(Date()))
            assertThat("Failed", anketa.progres, lessThan(1f))
        }
    }

    @Test
    fun getNotTaken() {
        val ankete = AnketaRepository.getNotTaken()
        for(anketa in ankete) {
            assertThat("Failed: Test 1", anketa.datumKraj, lessThan(Date()))
            assertThat("Failed: Test 2", anketa.progres, lessThan(1f))
        }
    }
}