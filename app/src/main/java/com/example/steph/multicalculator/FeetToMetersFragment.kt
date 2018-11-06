package com.example.steph.multicalculator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_feet_to_meters.*
import kotlinx.android.synthetic.main.fragment_feet_to_meters.view.*
import java.text.DecimalFormat

class FeetToMetersFragment : Fragment() {

    private val metersToFeetConversion = 3.28084
    private val feetToMetersConversion = 0.3048
    private val precision = DecimalFormat("0.00")

    // Need this to stop the eitTexts from calling onTextChanged when I change the text in code cause it will hang
    private var textChangedInCode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_feet_to_meters, container, false)

        rootView.editTextFeet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start : Int, count : Int, after : Int){}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
                if (!textChangedInCode){
                    textChangedInCode = true
                    if (editTextFeet.text.toString() != "") {
                        //rootView.editTextMeters.setText(calculateToMeters(rootView.editTextFeet.text.toString().toDouble()).toString())
                        val answer = calculateToMeters(editTextFeet.text.toString().toDouble())
                        editTextMeters.setText("${precision.format(answer)}")
                    }
                    else{
                        editTextMeters.setText("0.00")
                    }

                    textChangedInCode = false
                }
            }

            override fun afterTextChanged(s: Editable){
            }
        })
        rootView.editTextMeters.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start : Int, count : Int, after : Int){}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
                if (!textChangedInCode){
                    textChangedInCode = true
                    if (editTextMeters.text.toString() != "") {
                        //rootView.editTextFeet.setText(calculateToFeet(rootView.editTextMeters.text.toString().toDouble()).toString())
                        val answer = calculateToFeet(editTextMeters.text.toString().toDouble())
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
        fun newInstance(): FeetToMetersFragment {
            return FeetToMetersFragment()
        }
    }

    private fun calculateToFeet(meters: Double): Double{
        return meters * metersToFeetConversion
    }

    private fun calculateToMeters(feet: Double): Double{
        return feet * feetToMetersConversion
    }
}