package ba.etf.rma22.projekat.data.models

data class Korisnik (
    val nazivKorisnika: String,
    val anketeKorisnika: MutableList<Anketa>
)