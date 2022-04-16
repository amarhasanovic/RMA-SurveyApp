package ba.etf.rma22.projekat

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.view.FragmentAnkete
import ba.etf.rma22.projekat.view.FragmentIstrazivanje
import ba.etf.rma22.projekat.view.FragmentPoruka
import ba.etf.rma22.projekat.view.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    val fragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)

        viewPager.offscreenPageLimit = 2
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)
        viewPager.adapter = viewPagerAdapter

        viewPagerAdapter.add(0, FragmentAnkete.newInstance())
        viewPagerAdapter.add(1, FragmentIstrazivanje.newInstance(viewPagerAdapter))

        viewPager.registerOnPageChangeCallback(viewPagerChangeCallback)
    }
    private val viewPagerChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            if(fragments[1] is FragmentPoruka) {
                viewPagerAdapter.add(1, FragmentIstrazivanje.newInstance(viewPagerAdapter))
                viewPagerAdapter.remove(2)
            }
        }
    }
//    private fun refreshSecondFragmentText() {
//        Handler(Looper.getMainLooper()).postDelayed({
//            viewPagerAdapter.refreshFragment(1, FragmentIstrazivanje.newInstance())
//        }, 5000)
//    }

//    //Funkcija za izmjenu fragmenta
//    private fun openFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
}