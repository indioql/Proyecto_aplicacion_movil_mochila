package com.example.postureapp.vista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.postureapp.R

class EstiramientoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el layout del fragment
        return inflater.inflate(R.layout.fragment_estiramiento, container, false)
    }
}