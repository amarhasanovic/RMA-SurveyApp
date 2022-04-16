package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel

class FragmentAnkete : Fragment() {
    private lateinit var ankete: RecyclerView
    private lateinit var anketaAdapter: AnketaListAdapter

    private lateinit var filterSpinner: Spinner

    private var anketaListViewModel = AnketaListViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.ankete_fragment, container, false)
        //Inicijalizacija kontrola
        ankete = view.findViewById(R.id.listaAnketa)
        filterSpinner = view.findViewById(R.id.filterAnketa)

        //RecyclerView
        ankete.layoutManager = GridLayoutManager(activity, 2)
        anketaAdapter = AnketaListAdapter(listOf())
        ankete.adapter = anketaAdapter
        anketaAdapter.azurirajAnkete(anketaListViewModel.getAll())

        //Spinner za filtriranje
        val spinnerAdapter: ArrayAdapter<CharSequence> =
            ArrayAdapter.createFromResource(
                view.context,
                R.array.filters,
                android.R.layout.simple_list_item_1
            )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filterSpinner.adapter = spinnerAdapter
        setUpSpinnerListener()

        return view
    }
    private fun setUpSpinnerListener() {
        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                spinnerSelectedItem(parent.getItemAtPosition(position) as String)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun spinnerSelectedItem(selectedItem: String) {
        when (selectedItem) {
            "Sve moje ankete" -> anketaAdapter.azurirajAnkete(anketaListViewModel.getMyAnkete())
            "Sve ankete" -> anketaAdapter.azurirajAnkete(anketaListViewModel.getAll())
            "Urađene ankete" -> anketaAdapter.azurirajAnkete(anketaListViewModel.getDone())
            "Buduće ankete" -> anketaAdapter.azurirajAnkete(anketaListViewModel.getFuture())
            "Prošle ankete" -> anketaAdapter.azurirajAnkete(anketaListViewModel.getNotTaken())
            else -> anketaAdapter.azurirajAnkete(listOf())
        }
    }

    override fun onResume() {
        super.onResume()
        spinnerSelectedItem(filterSpinner.selectedItem as String)
    }

    companion object {
        fun newInstance(): FragmentAnkete = FragmentAnkete()
    }
}