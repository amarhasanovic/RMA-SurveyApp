package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Grupa

class FragmentPoruka : Fragment() {
    private lateinit var tvPoruka: TextView
    private lateinit var grupa: Grupa

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.poruka_fragment, container, false)
        tvPoruka = view.findViewById(R.id.tvPoruka)
        tvPoruka.text = "Uspješno ste upisani u grupu ${grupa.naziv} istraživanja ${grupa.nazivIstrazivanja}!"
        return view
    }

    companion object {
        fun newInstance(grupa: Grupa): FragmentPoruka = FragmentPoruka().apply {
            this.grupa = grupa
        }
    }
}