package com.example.steph.multicalculator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.text.DecimalFormat

import kotlinx.android.synthetic.main.fragment_centimeters_to_meters.*
import kotlinx.android.synthetic.main.fragment_centimeters_to_meters.view.*

class CentimetersToMetersFragment : Fragment() {

    private val metersToCentimetersConversion = 1000
    private val centimetersToMetersConversion = .001
    private val precision = DecimalFormat("0.0")

    // Need this to stop the eitTexts from calling onTextChanged when I change the text in code cause it will hang
    private var textChangedInCode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_centimeters_to_meters, container, false)

        rootView.editTextInches.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start : Int, count : Int, after : Int){}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
                if (!textChangedInCode){
                    textChangedInCode = true
                    if (editTextInches.text.toString() != "") {
                        val answer = calculateToMeters(editTextInches.text.toString().toDouble())
                        editTextFeet.setText("${precision.format(answer)}")
                    }
                    else{
                        editTextFeet.setText("0.00")
                    }

                    textChangedInCode = false
                }
            }

            override fun afterTextChanged(s: Editable){
            }
        })
        rootView.editTextFeet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!textChangedInCode) {
                    textChangedInCode = true
                    if (editTextFeet.text.toString() != "") {
                        val answer = calculateToCentimeters(editTextFeet.text.toString().toDouble())
                        editTextInches.setText("${precision.format(answer)}")
                    } else {
                        editTextInches.setText("0.00")
                    }

                    textChangedInCode = false
                }
            }

            override fun afterTextChanged(s: Editable){}
        })

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_centimeters_to_meters, container, false)
        return rootView
    }


    companion object {
        fun newInstance():CentimetersToMetersFragment{
            return CentimetersToMetersFragment()
        }
    }

    private fun calculateToMeters(centimeters: Double): Double{
        return centimeters * centimetersToMetersConversion
    }

    private fun calculateToCentimeters(meters: Double): Double {
        return meters * metersToCentimetersConversion
    }
}
