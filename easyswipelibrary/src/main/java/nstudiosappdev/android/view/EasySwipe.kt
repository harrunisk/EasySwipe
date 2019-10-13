package nstudiosappdev.android.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import nstudiosappdev.android.view.easyswipe.R

class EasySwipe(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    init {
        val view = LayoutInflater.from(context).inflate(
            R.layout.view_easy_swipe,
            this,
            true
        )
        view.invalidate()
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