package nstudiosappdev.android.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.view_easy_swipe.view.*
import nstudiosappdev.android.view.easyswipe.R
import kotlin.math.absoluteValue

class EasySwipe(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    init {
        val view = LayoutInflater.from(context).inflate(
            R.layout.view_easy_swipe,
            this,
            true
        )
        view.invalidate()

        seekbar_main.isClickable = false
        seekbar_pin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                seekbar_main.progress = 100 - progress

                val alpha = (((progress - 50).absoluteValue * 2).toFloat()) / 100
                tv_reject.alpha = 1f - alpha
                iv_tick_reject.alpha = 1f - alpha
                tv_accept.alpha = 1f - alpha
                iv_tick_accept.alpha = 1f - alpha
                when {
                    progress >= 94 -> {
                        seekbar_pin.progress = 94
                        seekbar_main.progress = 0
                    }
                    progress <= 6 -> {
                        seekbar_pin.progress = 6
                        seekbar_main.progress = 100
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //no op
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val progress = seekBar.progress

                when {
                    progress > 80 -> {
                        seekbar_pin.progress = 94
                        seekbar_main.progress = 0

                    }
                    progress < 20 -> {
                        seekbar_pin.progress = 6
                        seekbar_main.progress = 100
                    }
                    else -> {
                        seekbar_main.progress = 50
                        seekbar_pin.progress = 50
                    }
                }
            }
        })
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun setupAttributes(attrs: AttributeSet?) {

    }

    companion object {
        private const val MAX_PROGRESS = 100
    }
}