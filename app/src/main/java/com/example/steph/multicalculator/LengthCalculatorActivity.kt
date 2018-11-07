package com.example.steph.multicalculator

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_length_calculator.*
import kotlinx.android.synthetic.main.fragment_feet_to_meters.view.*

class LengthCalculatorActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_length_calculator)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        //fab.setOnClickListener { view ->
          //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //    .setAction("Action", null).show()
        //}

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.calculators, menu)
        val item = menu!!.findItem(R.id.lengthCalculator)
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            // on item "Tip Calculator" select, go to TipCalculatorActivity
            R.id.tipCalculator -> {
                val i = Intent(this, TipCalculatorActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(i)
                true
            }
            // on item "Tax Calculator" select, go to MainActivity (or recreate MainActivity)
            R.id.taxCalculator -> {
                val i = Intent(this, MainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(i)
                true
            }
            // on item "BMI Calculator" select, got to BmiCalculatorActivity
            R.id.bmiCalculator -> {
                val i = Intent(this, BmiCalculatorActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(i)
                true
            }
            // inform the user that they are already using this calculator
            R.id.lengthCalculator -> {
                Toast.makeText(applicationContext, "You're already using this calculator!", Toast.LENGTH_LONG).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            if (position == 0){
                return FeetToMetersFragment.newInstance()
            }
            else if (position == 1){
                return InchesToFeetFragment.newInstance()
            }
            else{
                return CentimetersToMetersFragment.newInstance()
            }

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return FeetToMetersFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }
    // member variable to count number of back presses
    private var backPress: Int = 0
    override fun onBackPressed() {
        // notify the user they can exit the application by pressing the back button again
        Toast.makeText(applicationContext, "Press back again to exit application", Toast.LENGTH_LONG).show()
        // check if the user presses back again
        backPress++
        // if two back presses, exit the application
        if (backPress > 1) {
            finishAffinity()
        }
    }
}
