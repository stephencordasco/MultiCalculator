package com.example.steph.multicalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils.isEmpty
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.example.steph.multicalculator.R.id.beforeTaxText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tip_calculator.*
import java.lang.StringBuilder
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var beforeTax = 0.0
    var tax = 0.0089
    var taxCalculation = 0.0
    var costWithTax = 0.0
    var beforeTextChanged = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // button is the id of the button
        val calculate: Button = calculateButton
        calculate.setOnClickListener(this)

        beforeTaxText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i("MyActivity", "inside beforeText")
                //beforeTextChanged = beforeTaxText.getText().toString()
                //calculateButton.isClickable=false
                if(count== 0){
                    calculateButton.isClickable=false
                } else {
                    calculateButton.isClickable=true
                }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.calculators, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onClick(v: View) {
        // used to format the decimal

        val precision = DecimalFormat("0.00")
        beforeTax = java.lang.Double.parseDouble(beforeTaxText.text.toString())
        print("beforeafdsf"+beforeTax)
        if(beforeTaxText.text.toString() == "NULL"){
            Log.i("MyActivity", "BeforeText less than 0")
            calculateButton.isClickable=false
        }
        taxCalculation = beforeTax * tax
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.tipCalculator -> {
                val i = Intent(this, TipCalculatorActivity::class.java)
                startActivity(i)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}