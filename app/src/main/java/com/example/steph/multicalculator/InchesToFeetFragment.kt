package com.example.steph.multicalculator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_inches_to_feet.*
import kotlinx.android.synthetic.main.fragment_inches_to_feet.view.*
import java.text.DecimalFormat

class InchesToFeetFragment : Fragment() {

    private val inchesToFeetConversion = 0.083333333
    private val feetToInchesConversion = 12
    private val precision = DecimalFormat("0.0")

    // Need this to stop the eitTexts from calling onTextChanged when I change the text in code cause it will hang
    private var textChangedInCode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_inches_to_feet, container, false)

        rootView.editTextFeet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start : Int, count : Int, after : Int){}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
                if (!textChangedInCode){
                    textChangedInCode = true
                    if (editTextFeet.text.toString() != "") {
                        val answer = calculateToInches(editTextFeet.text.toString().toDouble())
                        editTextInches.setText("${precision.format(answer)}")
                    }
                    else{
                        editTextInches.setText("0.00")
                    }

                    textChangedInCode = false
                }
            }

            override fun afterTextChanged(s: Editable){
            }
        })
        rootView.editTextInches.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start : Int, count : Int, after : Int){}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
                if (!textChangedInCode){
                    textChangedInCode = true
                    if (editTextInches.text.toString() != "") {
                        val answer = calculateToFeet(editTextInches.text.toString().toDouble())
                        editTextFeet.setText("${precision.format(answer)}")
                    }
                    else{
                        editTextFeet.setText("0.00")
                    }

                    textChangedInCode = false
                }
            }

            override fun afterTextChanged(s: Editable){}
        })
        return rootView
    }

    companion object {
        fun newInstance():InchesToFeetFragment{
            return InchesToFeetFragment()
        }
    }

    private fun calculateToFeet(inches: Double): Double{
        return inches * inchesToFeetConversion
    }

    private fun calculateToInches(feet: Double): Double{
        return feet * feetToInchesConversion
    }
}
