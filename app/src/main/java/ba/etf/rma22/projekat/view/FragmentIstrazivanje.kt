package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import ba.etf.rma22.projekat.viewmodel.GrupaListViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeListViewModel

class FragmentIstrazivanje() : Fragment() {
    private lateinit var godinaSpinner: Spinner
    private lateinit var istrazivanjeSpinner: Spinner
    private lateinit var grupaSpinner: Spinner
    private lateinit var dodajDugme: Button

    private var anketaListViewModel = AnketaListViewModel()
    private var istrazivanjeListViewModel = IstrazivanjeListViewModel()
    private var grupaListViewModel = GrupaListViewModel()

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.upis_istrazivanja_fragment, container, false)
        //Inicijalizacija komponenti
        godinaSpinner = view.findViewById(R.id.odabirGodina)
        istrazivanjeSpinner = view.findViewById(R.id.odabirIstrazivanja)
        grupaSpinner = view.findViewById(R.id.odabirGrupa)
        dodajDugme = view.findViewById(R.id.dodajIstrazivanjeDugme)

        //Dugme
        dodajDugme.setOnClickListener {
            upisiKorisnika()
        }
        spinnerSetUp()

        return view
    }



    private fun spinnerSetUp() {
        dodajDugme.isEnabled = false

        //Spinneri
        val listaGodina: List<String> =
            listOf("Odaberite godinu") + istrazivanjeListViewModel.getAll()
                .map { it.godina.toString() }.distinct()
        val godinaSpinnerAdapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, listaGodina) }
        godinaSpinner.adapter = godinaSpinnerAdapter
        godinaSpinner.setSelection(getDefaultYearValue())

        godinaSpinnerSetUp()
    }

    private fun godinaSpinnerSetUp() {
        godinaSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (!parent.getItemAtPosition(position).equals("Odaberite godinu")) {
                    istrazivanjeSpinnerSetUp(parent, position)
                } else {
                    dodajDugme.isEnabled = false
                    val istrazivanjeSpinnerAdapter = activity?.let {
                        ArrayAdapter(
                            it,
                            android.R.layout.simple_spinner_item,
                            listOf<String>()
                        )
                    }
                    istrazivanjeSpinner.adapter = istrazivanjeSpinnerAdapter
                    val grupaSpinnerAdapter = activity?.let {
                        ArrayAdapter(
                            it,
                            android.R.layout.simple_spinner_item,
                            listOf<String>()
                        )
                    }
                    grupaSpinner.adapter = grupaSpinnerAdapter
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun istrazivanjeSpinnerSetUp(parent: AdapterView<*>, position: Int) {
        val listaIstrazivanja: List<String> =
            listOf("Odaberite istraživanje") + istrazivanjeListViewModel.getIstrazivanjeByGodina(
                Integer.parseInt(parent.getItemAtPosition(position).toString())
            ).filter { it !in istrazivanjeListViewModel.getUpisani() }
                .map { it.naziv }
        val istrazivanjeSpinnerAdapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                listaIstrazivanja
            )
        }
        istrazivanjeSpinner.adapter = istrazivanjeSpinnerAdapter
        istrazivanjeSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (!parent.getItemAtPosition(position).equals("Odaberite istraživanje")) {
                    grupaSpinnerSetUp(parent, position)
                } else {
                    dodajDugme.isEnabled = false
                    val grupaSpinnerAdapter = ArrayAdapter(
                        view.context,
                        android.R.layout.simple_spinner_item,
                        listOf<String>()
                    )
                    grupaSpinner.adapter = grupaSpinnerAdapter
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun grupaSpinnerSetUp(parent: AdapterView<*>, position: Int) {
        val listaGrupa: List<String> =
            listOf("Odaberite grupu") + grupaListViewModel.getGroupsByIstrazivanje(
                parent.getItemAtPosition(position).toString()
            ).map { it.naziv }
        val grupaSpinnerAdapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                listaGrupa
            )
        }
        grupaSpinner.adapter = grupaSpinnerAdapter
        grupaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                dodajDugme.isEnabled = !parent.getItemAtPosition(position).equals("Odaberite grupu")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    fun upisiKorisnika() {
        val istrazivanje = istrazivanjeListViewModel.getAll().find {
            it.naziv == istrazivanjeSpinner.selectedItem && it.godina == Integer.parseInt(godinaSpinner.selectedItem.toString())
        }
        val anketa = anketaListViewModel.getAll().find { it.nazivIstrazivanja == istrazivanje?.naziv ?:""  && it.nazivGrupe == grupaSpinner.selectedItem }
        var text = "Greška"
        val duration = Toast.LENGTH_SHORT
        if(istrazivanje != null && anketa != null && !korisnikVecUpisan(istrazivanje)){
            anketaListViewModel.upisiAnketu(anketa)
            text = "Uspješno upisan"
        }

        val toast = Toast.makeText(context, text, duration)
        toast.show()

        val grupa = istrazivanje?.let { grupaListViewModel.getGroupsByIstrazivanje(it.naziv).find { it.naziv == grupaSpinner.selectedItem } }
        grupa?.let { FragmentPoruka.newInstance(it) }?.let { viewPagerAdapter.add(1, it) }
        viewPagerAdapter.remove(2)
    }

    fun korisnikVecUpisan(istrazivanje: Istrazivanje) : Boolean {
        return istrazivanjeListViewModel.getUpisani().find { it.naziv == istrazivanje.naziv && it.godina == istrazivanje.godina } != null
    }

    private fun getDefaultYearValue() : Int {
        if(istrazivanjeListViewModel.getUpisani().size != 0)
            return istrazivanjeListViewModel.getUpisani().last().godina
        return 0
    }

    companion object {
        fun newInstance(viewPagerAdapter: ViewPagerAdapter): FragmentIstrazivanje = FragmentIstrazivanje().apply {
            this.viewPagerAdapter = viewPagerAdapter
        }
    }
}