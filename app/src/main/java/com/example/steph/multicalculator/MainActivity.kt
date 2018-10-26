package com.example.steph.multicalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.calculators, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.tipCalculator -> {
                Toast.makeText(this, "You selected ${item.title}", Toast.LENGTH_SHORT).show()
                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}