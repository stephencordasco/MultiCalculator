package com.example.steph.multicalculator

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmi_calculator.*
import java.text.DecimalFormat

class BmiCalculatorActivity : AppCompatActivity() {

    private var feet = 0
    private var inches = 0
    private var weight = 0.0
    private var bmi = 0.0
    private val precision = DecimalFormat("0.0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)

        buttonCalculate.setOnClickListener(){
            if (checkEditTexts()){
                feet = editTextFeet.text.toString().toInt()
                inches = editTextInches.text.toString().toInt()
                weight = editTextWeight.text.toString().toDouble()
                bmi = calculateBMI(feet, inches, weight)
                textViewBMI.text = "Your BMI is: ${precision.format(bmi)}"
                if (bmi < 18.5){
                    textViewBMICatagories.text = "You are Underweight"
                    textViewBMICatagories.setTextColor(Color.BLUE)
                }
                else if (bmi >= 18.5 && bmi <25){
                    textViewBMICatagories.text = "You are Normal Weight"
                    textViewBMICatagories.setTextColor(Color.GREEN)
                }
                else if (bmi >= 25 && bmi < 30){
                    textViewBMICatagories.text = "You are Overweight"
                    textViewBMICatagories.setTextColor(Color.MAGENTA)
                }
                else{
                    textViewBMICatagories.text = "You are Obese"
                    textViewBMICatagories.setTextColor(Color.RED)
                }
            }
            else{
                Toast.makeText(this, "You have to enter something", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.calculators, menu)
        val item = menu!!.findItem(R.id.bmiCalculator)
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
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
            // inform the user that they are already using this calculator
            R.id.bmiCalculator -> {
                Toast.makeText(applicationContext, "You're already using this calculator!", Toast.LENGTH_LONG).show()
                true
            }
            // on item "Length Calculator" select, go to LengthCalculatorActivity
            R.id.lengthCalculator -> {
                val i = Intent(this, LengthCalculatorActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(i)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun calculateBMI(feet: Int, inches: Int, weight: Double): Double{
        val totalInches : Double = (inches + (feet * 12)).toDouble()
        // Conversion factor for inches and height in BMI is 703
        return (weight/(totalInches*totalInches)) * 703
    }

    private fun checkEditTexts() : Boolean{
        if (editTextFeet.text.toString() == ""){
            return false
        }

        if (editTextInches.text.toString() == ""){
            return false
        }

        if (editTextWeight.text.toString() == ""){
            return false
        }

        return true
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
