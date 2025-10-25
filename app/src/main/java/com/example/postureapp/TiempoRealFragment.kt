package com.example.postureapp

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.postureapp.databinding.FragmentTiempoRealBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import android.graphics.Color
import kotlin.random.Random

class TiempoRealFragment : Fragment() {

    private var _binding: FragmentTiempoRealBinding? = null
    private val binding get() = _binding!!

    private var currentPostureAngle = 15f // Ángulo actual (0-180)
    private var timeElapsed = 0
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTiempoRealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPostureGauge()
        setupHistoricalChart()
        setupCheckboxes()
        startTimer()
    }

    private fun setupPostureGauge() {
        // Configurar el gauge inicial
        binding.postureGauge.setPostureAngle(currentPostureAngle)
        updatePostureWarning()
    }

    private fun updatePostureWarning() {
        when {
            currentPostureAngle < 60 -> {
                // Postura buena - Ocultar advertencia
                binding.warningCard.visibility = View.GONE
            }
            currentPostureAngle < 120 -> {
                // Postura regular - Advertencia amarilla
                binding.warningCard.visibility = View.VISIBLE
                binding.warningText.text = "¡Cuidado con tu postura!"
                binding.warningCard.setCardBackgroundColor(Color.parseColor("#FFF9C4"))
            }
            else -> {
                // Postura mala - Advertencia roja
                binding.warningCard.visibility = View.VISIBLE
                binding.warningText.text = "¡Estas muy encorvado!"
                binding.warningCard.setCardBackgroundColor(Color.parseColor("#FFEBEE"))
            }
        }
    }

    private fun setupHistoricalChart() {
        val entries = listOf(
            PieEntry(30f, "Tiempo encorvado"),
            PieEntry(25f, "Tiempo derecho"),
            PieEntry(20f, "Caminatas"),
            PieEntry(15f, "Estiramientos"),
            PieEntry(10f, "Tiempo no funcional")
        )

        val colors = listOf(
            Color.parseColor("#EF5350"), // Rojo
            Color.parseColor("#66BB6A"), // Verde
            Color.parseColor("#AED581"), // Verde claro
            Color.parseColor("#26C6DA"), // Cyan
            Color.parseColor("#000000")  // Negro
        )

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors
        dataSet.valueTextSize = 12f
        dataSet.valueTextColor = Color.WHITE
        dataSet.setDrawValues(true)

        val data = PieData(dataSet)
        binding.historicalChart.data = data
        binding.historicalChart.description.isEnabled = false
        binding.historicalChart.legend.isEnabled = true
        binding.historicalChart.setUsePercentValues(false)
        binding.historicalChart.setDrawEntryLabels(false)
        binding.historicalChart.animateY(1000)
        binding.historicalChart.invalidate()
    }

    private fun setupCheckboxes() {
        binding.checkbox30min.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Acción cuando se marca el checkbox de 30 minutos
                binding.progressBar.progress = 0
                timeElapsed = 0
            }
        }

        binding.checkboxCaminar.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Acción cuando se marca salir a caminar
                // Podrías resetear el gauge o guardar datos
            }
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeElapsed++
                updateProgressBar()
                simulatePostureChange()
            }

            override fun onFinish() {
                // No se ejecutará porque usamos Long.MAX_VALUE
            }
        }.start()
    }

    private fun simulatePostureChange() {
        // Simular cambio gradual de postura (en app real vendría de sensores)
        val change = Random.nextFloat() * 20 - 10 // Entre -10 y +10 grados
        currentPostureAngle = (currentPostureAngle + change).coerceIn(0f, 180f)

        // Actualizar el gauge
        binding.postureGauge.setPostureAngle(currentPostureAngle)

        // Actualizar advertencia
        updatePostureWarning()
    }

    private fun updateProgressBar() {
        // Progreso de 0-100 cada 30 minutos (1800 segundos)
        val progress = ((timeElapsed % 1800) * 100) / 1800
        binding.progressBar.progress = progress

        // Opcional: resetear checkbox cuando se complete
        if (progress >= 100 && binding.checkbox30min.isChecked) {
            binding.checkbox30min.isChecked = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
        _binding = null
    }
}