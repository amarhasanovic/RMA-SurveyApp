package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Assert
import org.junit.Test

class GrupaRepositoryTest {
    @Test
    fun testGetGroupsByIstrazivanjeTest() {
        val grupe = GrupaRepository.getGroupsByIstrazivanje("Istraživanje broj 3")
        Assert.assertEquals(grupe.size, 2)
        assertThat(
            grupe,
            hasItem<Grupa>(hasProperty("naziv", Is("Grupa 1")))
        )
        assertThat(
            grupe,
            hasItem<Grupa>(hasProperty("naziv", Is("Grupa 2")))
        )
        assertThat(
            grupe,
            not(
                hasItem<Grupa>(
                    hasProperty(
                        "naziv",
                        Is("Grupa 3")
                    )
                )
            )
        )
    }
}