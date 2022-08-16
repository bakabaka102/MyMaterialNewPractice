package com.practice.mymaterial.spot_light

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.practice.mymaterial.R

class SpotLightImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    private var paint = Paint()
    private var shouldDrawSpotLight = false
    private var gameOver = false

    private lateinit var winnerRect: RectF
    private var androidBitmapX = 0f
    private var androidBitmapY = 0f

    private val bitmapAndroid = BitmapFactory.decodeResource(resources, R.drawable.android)
    private val spotlight = BitmapFactory.decodeResource(resources, R.drawable.mask)

    private val shaderMatrix = Matrix()

    private var shader: Shader

    init {
        val bitmap = Bitmap.createBitmap(spotlight.width, spotlight.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val shaderPaint = Paint(Paint.ANTI_ALIAS_FLAG)

        // Draw a black rectangle.
        shaderPaint.color = Color.BLACK
        canvas.drawRect(
            0.0f,
            0.0f,
            spotlight.width.toFloat(),
            spotlight.height.toFloat(),
            shaderPaint
        )



        shaderPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        //canvas.drawBitmap(spotlight, 0.0f, 0.0f, shaderPaint)
        canvas.drawBitmap(spotlight, 0f, 0.0f, shaderPaint)

        shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        //1
        //shader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP)
        paint.shader = shader

        shaderMatrix.setTranslate(100f, 550f)
        shader.setLocalMatrix(shaderMatrix)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Color the background yellow.
        canvas?.drawColor(Color.YELLOW)

        shaderMatrix.setTranslate(100f, 550f)
        shader.setLocalMatrix(shaderMatrix)

        //canvas?.drawRect(0.0f, 0.0f,spotlight.width.toFloat(), spotlight.height.toFloat(), paint)
        //1
        canvas?.drawRect(0.0f, 0.0f, width.toFloat(), height.toFloat() / 2, paint)
    }

}