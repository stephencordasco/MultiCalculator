package com.example.steph.multicalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tip_calculator.*
import java.lang.StringBuilder
import java.text.DecimalFormat

class TipCalculatorActivity : AppCompatActivity() {

    var progressValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_calculator)

        // TextChangedListener for the edittext
        priceEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                // declare StringBuilder object
                val total = StringBuilder()
                // build the string for the total
                total.append("$")
                total.append(priceEditText.text.toString())
                // set the totalTextView2 text to total
                totalTextView2.text = total
                // update the tip cost
                printTipCost()
            }
        })

        // SeekBarChangeListener for the seek bar
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // store the progress value
                progressValue = progress
                // print the tip
                printTip()
                // print the tip cost
                printTipCost()
                // calculate the tip after the seek bar stops tracking
                calculateTip()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    // helper method prints the tip as a percentage (%0)
    fun printTip() {
        // build a string of the tip to print to the application
        val tip = StringBuilder()
        tip.append(progressValue.toString())
        tip.append("%")
        // set the text of the textview
        tipTextView2.text = tip
    }

    // helper method prints the tip as a dollar amount ($0.00)
    fun printTipCost() {
        // check if the edittext field is empty
        checkEditText()

        // used to format the cost
        val precision = DecimalFormat("0.00")

        // calculate the tip cost
        val price = java.lang.Double.parseDouble(priceEditText.text.toString())
        val tipCost: Double = price * (progressValue.toDouble()/100)

        // build a string of the tip cost to print to the application
        val tip = StringBuilder()
        tip.append("$")
        tip.append(precision.format(tipCost).toString())
        // set the text of the textview
        tipCostTextView2.text = tip
    }

    // helper method calculates the tip and adds it to the current price, then displays it to the UI
    fun calculateTip() {
        // check if the edittext field is empty
        checkEditText()

        // used to format the cost
        val precision = DecimalFormat("0.00")

        // store the price
        val price = java.lang.Double.parseDouble(priceEditText.text.toString())
        // calculate and store the tip
        val tip: Double = price * (progressValue.toDouble()/100)
        // calculate and store the total
        val total: Double = price + tip

        // build a string of the total to print to the application
        val totalValue = StringBuilder()
        totalValue.append("$")
        totalValue.append(precision.format(total).toString())
        // set the text of the textview
        totalTextView2.text = totalValue
    }

    // helper method checks if the edittext field is empty; prevents null pointer exception
    private fun checkEditText() {
        // check if edittext field is null
        if (priceEditText.text.toString() == "") {
            // it is so set it to value 0
            priceEditText.setText("0")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.calculators, menu)
        val item: MenuItem = menu!!.findItem(R.id.tipCalculator)
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            // inform the user that they are already using this calculator
            R.id.tipCalculator -> {
                Toast.makeText(applicationContext, "You're already using this calculator!", Toast.LENGTH_LONG).show()
                true
            }
            // on item "Tax Calculator" select, go to MainActivity (or recreate MainActivity)
            R.id.taxCalculator -> {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                true
            }
            // on item "BMI Calculator" select, got to BmiCalculatorActivity
            R.id.bmiCalculator -> {
                val i = Intent(this, BmiCalculatorActivity::class.java)
                startActivity(i)
                true
            }
            // on item "Length Calculator" select, go to LengthCalculatorActivity
            R.id.lengthCalculator -> {
                val i = Intent(this, LengthCalculatorActivity::class.java)
                startActivity(i)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}