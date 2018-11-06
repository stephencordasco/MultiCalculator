package com.example.steph.multicalculator

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class InchesToFeetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inches_to_feet, container, false)
    }

    companion object {
        fun newInstance():InchesToFeetFragment{
            return InchesToFeetFragment()
        }
    }
}
