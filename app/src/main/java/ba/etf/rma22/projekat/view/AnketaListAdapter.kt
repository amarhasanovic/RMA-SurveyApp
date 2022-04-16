package ba.etf.rma22.projekat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import java.lang.Math.round
import java.text.SimpleDateFormat
import java.util.*

class AnketaListAdapter (
    private var ankete: List<Anketa>
    ) : RecyclerView.Adapter<AnketaListAdapter.AnketaViewHolder> (){

    inner class AnketaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val naziv: TextView = itemView.findViewById(R.id.naslov_ankete)
        val nazivIstrazivanja: TextView = itemView.findViewById(R.id.naziv_istrazivanja)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progresZavrsetka)
        val datum: TextView = itemView.findViewById(R.id.datum)
        val statusAnketeImage: ImageView = itemView.findViewById(R.id.status_ankete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnketaViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_anketa, parent, false)
        return AnketaViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnketaViewHolder, position: Int) {
        holder.naziv.text = ankete[position].naziv
        holder.nazivIstrazivanja.text = ankete[position].nazivIstrazivanja
        holder.progressBar.progress = dajProgressAnkete(ankete[position].progres)

        val pattern = "dd.MM.yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val context: Context = holder.statusAnketeImage.context
        if (ankete[position].datumRada != null){
            val date = simpleDateFormat.format(ankete[position].datumRada!!.time)
            holder.datum.text = "Anketa urađena: " + date
            var id: Int = context.resources
                .getIdentifier("plava", "drawable", context.packageName)
            holder.statusAnketeImage.setImageResource(id)
        } else if(ankete[position].datumKraj <= Date()){
            val date = simpleDateFormat.format(ankete[position].datumKraj.time)
            holder.datum.text = "Anketa zatvorena: " + date
            var id: Int = context.resources
                .getIdentifier("crvena", "drawable", context.packageName)
            holder.statusAnketeImage.setImageResource(id)
        } else if(ankete[position].datumPocetak >= Date()){
            val date = simpleDateFormat.format(ankete[position].datumPocetak.time)
            holder.datum.text = "Vrijeme aktiviranja: " + date
            var id: Int = context.resources
                .getIdentifier("zuta", "drawable", context.packageName)
            holder.statusAnketeImage.setImageResource(id)
        } else {
            val date = simpleDateFormat.format(ankete[position].datumKraj.time)
            holder.datum.text = "Vrijeme zatvaranja: " + date
            var id: Int = context.resources
                .getIdentifier("zelena", "drawable", context.packageName)
            holder.statusAnketeImage.setImageResource(id)
        }
    }

    override fun getItemCount(): Int = ankete.size

    fun azurirajAnkete(ankete: List<Anketa>) {
        this.ankete = ankete
        notifyDataSetChanged()
    }

    fun dajProgressAnkete(progressFloat: Float) : Int {
        var progressValue = round(progressFloat*10)
        if(progressValue % 2 != 0)
            progressValue += 1
        return progressValue * 10
    }
}