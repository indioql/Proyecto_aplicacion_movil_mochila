package com.example.postureapp.vista

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.postureapp.R
import com.example.postureapp.vista.TiempoRealFragment
import com.example.postureapp.vista.VideosFragment
import com.example.postureapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    // ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Agregar las pestañas
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Estiramiento"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tiempo Real"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Videos"))

        // Mostrar fragment inicial (por defecto "Tiempo Real")
        replaceFragment(TiempoRealFragment())
        binding.tabLayout.getTabAt(1)?.select() // Marca la pestaña de Tiempo Real

        // Listener de pestañas
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> replaceFragment(EstiramientoFragment())
                    1 -> replaceFragment(TiempoRealFragment())
                    2 -> replaceFragment(VideosFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}