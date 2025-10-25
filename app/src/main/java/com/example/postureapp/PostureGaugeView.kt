package com.example.postureapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class PostureGaugeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var postureAngle = 0f // 0-180 grados

    // Paint para los arcos de color
    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arcRect = RectF()

    // Paint para la aguja (línea)
    private val needlePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    // Paint para el círculo gris central
    private val centerCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    // Paint para el punto blanco central
    private val centerDotPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        // Configuración del arco
        arcPaint.style = Paint.Style.STROKE
        arcPaint.strokeWidth = 40f
        arcPaint.strokeCap = Paint.Cap.ROUND

        // Configuración de la aguja
        needlePaint.style = Paint.Style.STROKE
        needlePaint.color = Color.parseColor("#424242")
        needlePaint.strokeWidth = 8f

        // Configuración del círculo central
        centerCirclePaint.style = Paint.Style.FILL
        centerCirclePaint.color = Color.parseColor("#616161")

        // Configuración del punto central
        centerDotPaint.style = Paint.Style.FILL
        centerDotPaint.color = Color.WHITE
    }

    fun setPostureAngle(angle: Float) {
        postureAngle = angle.coerceIn(0f, 180f)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height * 0.75f
        val radius = width * 0.35f

        arcRect.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        // Dibujar arco verde
        arcPaint.color = Color.parseColor("#66BB6A")
        canvas.drawArc(arcRect, 180f, 60f, false, arcPaint)

        // Amarillo
        arcPaint.color = Color.parseColor("#FFEB3B")
        canvas.drawArc(arcRect, 240f, 60f, false, arcPaint)

        // Rojo
        arcPaint.color = Color.parseColor("#EF5350")
        canvas.drawArc(arcRect, 300f, 60f, false, arcPaint)

        // Aguja y círculos
        drawNeedle(canvas, centerX, centerY, radius * 0.9f)
    }

    private fun drawNeedle(canvas: Canvas, cx: Float, cy: Float, length: Float) {
        val angleRad = Math.toRadians((180 + postureAngle).toDouble())
        val endX = cx + length * cos(angleRad).toFloat()
        val endY = cy + length * sin(angleRad).toFloat()

        canvas.drawLine(cx, cy, endX, endY, needlePaint)
        canvas.drawCircle(cx, cy, 20f, centerCirclePaint)
        canvas.drawCircle(cx, cy, 8f, centerDotPaint)
    }
}
