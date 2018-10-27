package com.example.steph.multicalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_tip_calculator.*
import java.lang.StringBuilder
import java.text.DecimalFormat

class TipCalculatorActivity : AppCompatActivity() {

    var progressValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_calculator)

        priceEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val total = StringBuilder()
                total.append("$")
                total.append(priceEditText.text.toString())
                totalTextView2.text = total
            }
        })

        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                progressValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // build a string of the tip to print to the application
                val tip = StringBuilder()
                tip.append("%")
                tip.append(progressValue.toString())
                // set the text of the textview
                tipTextView2.text = tip

                // calculate after the seek bar stops tracking
                calculateTip()
            }
        })
    }

    fun calculateTip() {
        // check if the edittext field is empty
        if (priceEditText.text.toString() == "") {
            priceEditText.setText("0")
        }

        // used to format the decimal
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
}