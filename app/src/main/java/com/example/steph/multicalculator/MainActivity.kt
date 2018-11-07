package com.example.steph.multicalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // member variables
    private var beforeTax = 0.0
    private var tax = 0.0089
    private var taxCalculation = 0.0
    private var costWithTax = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // button is the id of the button
        val calculate: Button = calculateButton
        calculate.setOnClickListener(this)

        beforeTaxText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateButton.isClickable=true
            }

            override fun afterTextChanged(s: Editable?) {
                // build a string of the total to print to the application
                val totalValue = StringBuilder()
                totalValue.append("$")
                totalValue.append(beforeTaxText.text.toString())
                // set the text on the textView
                result.text = totalValue
            }
        })
    }

    override fun onClick(v: View) {
        // check if the edittext field is empty
        checkEditText()

        // used to format the decimal
        val precision = DecimalFormat("0.00")
        beforeTax = java.lang.Double.parseDouble(beforeTaxText.text.toString())

        // calculate the tax
        taxCalculation = beforeTax * tax
        // calculate the cost with tax
        costWithTax = beforeTax + taxCalculation

        // build a string of the taxTotal to print to the application
        val taxTotal = StringBuilder()
        taxTotal.append("$")
        taxTotal.append(precision.format(taxCalculation).toString())
        // set the taxTotal text on the textView
        resultTax.text = taxTotal

        // build a string of the total to print to the application
        val totalValue = StringBuilder()
        totalValue.append("$")
        totalValue.append(precision.format(costWithTax).toString())
        // set the text on the textView
        result.text = totalValue
    }

    // helper method checks if the edittext field is empty; prevents null pointer exception
    private fun checkEditText() {
        // check if edittext field is null
        if (beforeTaxText.text.toString() == "") {
            // it is so set it to value 0
            beforeTaxText.setText("0")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.calculators, menu)
        val item: MenuItem = menu!!.findItem(R.id.taxCalculator)
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            // on item "Tip Calculator" select, go to TipCalculatorActivity
            R.id.tipCalculator -> {
                val i = Intent(this, TipCalculatorActivity::class.java)
                startActivity(i)
                true
            }
            // inform the user that they are already using this calculator
            R.id.taxCalculator -> {
                Toast.makeText(applicationContext, "You're already using this calculator!", Toast.LENGTH_LONG).show()
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

    // member variable to count number of back presses
    private var backPress: Int = 0
    override fun onBackPressed() {
        // notify the user they can exit the application by pressing the back button again
        Toast.makeText(applicationContext, "Press back again to exit application", Toast.LENGTH_LONG).show()
        // check if the user presses back again
        backPress++
        // if two back presses, exit the application
        if (backPress > 1) {
            super.onBackPressed()
        }
    }
}