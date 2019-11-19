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

    private var cornerRadius: Float? = DEFAULT_RADIUS

    private var rejectText: String? = "REJECT"
    private var acceptText: String? = "ACCEPT"

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

        acceptText =
            typedArray.getString(
                R.styleable.EasySwipe_acceptText,
                DEFAULT_ACCEPT_TEXT
            )

        val positiveDrawable = createPositiveDrawable()
        val negativeScaleDrawable = createNegativeShapeDrawable()

        val layers = arrayOf(positiveDrawable, negativeScaleDrawable)
        val layerDrawable = LayerDrawable(layers)

        val seekBarMain = view.findViewById<SeekBar>(R.id.seekbar_main)
        seekBarMain.progressDrawable = layerDrawable
        seekBarMain.progress = DEFAULT_PROGRESS

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
        positiveDrawable.cornerRadius = DEFAULT_RADIUS

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
        negativeDrawable.setSize(0, DEFAULT_HEIGHT)
        negativeDrawable.cornerRadius = cornerRadius!!

        return ScaleDrawable(
            negativeDrawable,
            Gravity.START,
            SCALE_MAX,
            NOT_SCALE
        )
    }
    companion object {
        private const val DEFAULT_PROGRESS = 51
        private const val DEFAULT_RADIUS = 30f
        private const val DEFAULT_HEIGHT = 150
        private const val NOT_SCALE = -1f
        private const val SCALE_MAX = 1f
        private const val DEFAULT_ACCEPT_TEXT = "ACCEPT"
        private const val DEFAULT_REJECT_TEXT = "REJECT"
    }
}