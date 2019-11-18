package nstudiosappdev.android.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.SeekBar
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ScaleDrawable
import android.view.Gravity
import nstudiosappdev.android.view.easyswipe.R


class EasySwipeSettings(
    context: Context,
    attributeSet: AttributeSet,
    view: View
) {

    private var acceptStartColor: Int? = null
    private var acceptCenterColor: Int? = null
    private var acceptEndColor: Int? = null

    private var rejectStartColor: Int? = null
    private var rejectCenterColor: Int? = null
    private var rejectEndColor: Int? = null

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.EasySwipe)

        acceptStartColor =
            typedArray.getColor(
                R.styleable.EasySwipe_acceptStartColor,
                ContextCompat.getColor(context, R.color.colorDefaultAcceptStart)
            )
        acceptCenterColor =
            typedArray.getColor(
                R.styleable.EasySwipe_acceptCenterColor,
                ContextCompat.getColor(context, R.color.colorDefaultAcceptCenter)
            )
        acceptEndColor =
            typedArray.getColor(
                R.styleable.EasySwipe_acceptEndColor,
                ContextCompat.getColor(context, R.color.colorDefaultAcceptEnd)
            )
        rejectStartColor =
            typedArray.getColor(
                R.styleable.EasySwipe_rejectStartColor,
                ContextCompat.getColor(context, R.color.colorDefaultRejectStart)
            )
        rejectCenterColor =
            typedArray.getColor(
                R.styleable.EasySwipe_rejectCenterColor,
                ContextCompat.getColor(context, R.color.colorDefaultRejectCenter)
            )
        rejectEndColor =
            typedArray.getColor(
                R.styleable.EasySwipe_rejectEndColor,
                ContextCompat.getColor(context, R.color.colorDefaultRejectEnd)
            )

        val positiveDrawable = createPositiveDrawable()
        val negativeScaleDrawable = createNegativeShapeDrawable()

        val layers = arrayOf(positiveDrawable, negativeScaleDrawable)
        val layerDrawable = LayerDrawable(layers)

        val seekBarMain = view.findViewById<SeekBar>(R.id.seekbar_main)
        seekBarMain.progressDrawable = layerDrawable
        seekBarMain.progress = 51

        typedArray.recycle()
    }

    private fun createPositiveDrawable(): GradientDrawable {
        val positiveDrawable = GradientDrawable(
            GradientDrawable.Orientation.RIGHT_LEFT,
            intArrayOf(
                acceptStartColor!!,
                acceptCenterColor!!,
                acceptEndColor!!
            )
        )
        positiveDrawable.shape = GradientDrawable.RECTANGLE
        positiveDrawable.setStroke(0,0)
        positiveDrawable.setSize(1, -1)
        positiveDrawable.cornerRadius = 30f

        return positiveDrawable
    }

    private fun createNegativeShapeDrawable(): ScaleDrawable {
        val negativeDrawable = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                rejectStartColor!!,
                rejectCenterColor!!,
                rejectEndColor!!
            )
        )
        negativeDrawable.shape = GradientDrawable.RECTANGLE
        negativeDrawable.setStroke(0, 0)
        negativeDrawable.setSize(0, 150)
        negativeDrawable.cornerRadius = 30f

        return ScaleDrawable(
            negativeDrawable,
            Gravity.START,
            1f,
            -1f
        )
    }
}