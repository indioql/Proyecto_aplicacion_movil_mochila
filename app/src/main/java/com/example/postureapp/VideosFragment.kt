package com.example.postureapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.postureapp.databinding.FragmentVideosBinding

class VideosFragment : Fragment() {

    private var _binding: FragmentVideosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideosBinding.inflate(inflater, container, false)
        val view = binding.root

        // Botón 1: Ejercicio para mejorar la postura
        binding.btnPostura.setOnClickListener {
            openLink("https://www.youtube.com/watch?v=GIeOHUwwDYk") //
        }

        // Botón 2: Rutina rápida de estiramiento
        binding.btnEstiramiento.setOnClickListener {
            openLink("https://www.youtube.com/results?search_query=Rutina+r%C3%A1pida+de+estiramiento")
        }

        // Botón 3: Consejos ergonómicos
        binding.btnErgonomia.setOnClickListener {
            openLink("https://www.youtube.com/watch?v=Lx4YFmORsWc")
        }

        return view
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
