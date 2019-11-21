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
import android.widget.TextView
import nstudiosappdev.android.view.easyswipe.R


class EasySwipeSettings(
    context: Context,
    attributeSet: AttributeSet,
    view: View
) {

    /**
     * Accept side colors
     */
    private var acceptStartColor: Int? = null
    private var acceptCenterColor: Int? = null
    private var acceptEndColor: Int? = null

    /**
     * Reject side colors
     */
    private var rejectStartColor: Int? = null
    private var rejectCenterColor: Int? = null
    private var rejectEndColor: Int? = null

    /**
     * Animation texts
     */
    private var acceptText: String? = null
    private var rejectText: String? = null

    /**
     * Animation text colors
     */
    private var acceptTextColor: Int? = null
    private var rejectTextColor: Int? = null

    /**
     * Animation text sizes
     */
    private var acceptTextSize: Float? = null
    private var rejectTextSize: Float? = null

    /**
     * Animation corner radius
     */
    private var cornerRadius: Float? = null

    /**
     * Animation pin drawable
     */
    private var thumb: Int? = null

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
                R.styleable.EasySwipe_acceptText
            )

        rejectText =
            typedArray.getString(
                R.styleable.EasySwipe_rejectText
            )

        acceptTextColor =
            typedArray.getColor(
                R.styleable.EasySwipe_acceptTextColor,
                ContextCompat.getColor(context, R.color.colorDefaultAcceptText)
            )

        rejectTextColor =
            typedArray.getColor(
                R.styleable.EasySwipe_rejectTextColor,
                ContextCompat.getColor(context, R.color.colorDefaultRejectText)
            )

        acceptTextSize =
            typedArray.getDimension(
                R.styleable.EasySwipe_acceptTextSize,
                DEFAULT_TEXT_SIZE
            )

        rejectTextSize =
            typedArray.getDimension(
                R.styleable.EasySwipe_rejectTextSize,
                DEFAULT_TEXT_SIZE
            )

        cornerRadius =
            typedArray.getDimension(
                R.styleable.EasySwipe_cornerRadius,
                DEFAULT_RADIUS
            )

        thumb =
            typedArray.getResourceId(
                R.styleable.EasySwipe_thumb,
                DEFAULT_THUMB
            )

        arrangeTexts(view)

        createProgressDrawable(view)

        arrangeMainProgress(view)

        createThumb(view, context)

        typedArray.recycle()

    }

    /**
     * Create accept side drawable
     */
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
        positiveDrawable.cornerRadius = cornerRadius!!

        return positiveDrawable
    }

    /**
     * Create reject side and main drawable
     */
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
        negativeDrawable.cornerRadius = this.cornerRadius!!

        return ScaleDrawable(
            negativeDrawable,
            Gravity.START,
            SCALE_MAX,
            NOT_SCALE
        )
    }

    /**
     * Put texts
     *
     * @param view
     */
    private fun arrangeTexts(view: View) {

        val textViewAccept = view.findViewById<TextView>(R.id.textViewAccept)
        val textViewReject = view.findViewById<TextView>(R.id.textViewReject)

        if (!acceptText.isNullOrEmpty()) textViewAccept.text = acceptText
        else textViewAccept.text = DEFAULT_ACCEPT_TEXT

        if (!rejectText.isNullOrEmpty()) textViewReject.text = rejectText
        else textViewReject.text = DEFAULT_REJECT_TEXT

        textViewAccept.setTextColor(acceptTextColor!!)
        textViewReject.setTextColor(rejectTextColor!!)

        textViewAccept.textSize = acceptTextSize!!
        textViewReject.textSize = rejectTextSize!!
    }

    /**
     * Merge drawables
     *
     * @param view
     */
    private fun createProgressDrawable(view: View) {

        val positiveDrawable = createPositiveDrawable()
        val negativeScaleDrawable = createNegativeShapeDrawable()

        val layers = arrayOf(positiveDrawable, negativeScaleDrawable)
        val layerDrawable = LayerDrawable(layers)

        val seekBarMain = view.findViewById<SeekBar>(R.id.seekBarMain)
        seekBarMain.progressDrawable = layerDrawable
        seekBarMain.progress = DEFAULT_PROGRESS
    }

    /**
     * Main progress bar settings
     *
     * @param view
     */
    private fun arrangeMainProgress(view: View) {
        val seekBarMain = view.findViewById<SeekBar>(R.id.seekBarMain)
        seekBarMain.isClickable = false
    }

    /**
     * Pin button settings
     *
     * @param view
     * @param context
     */
    private fun createThumb(view: View, context: Context) {
        val seekBarPin = view.findViewById<SeekBar>(R.id.seekBarPin)
        seekBarPin.thumb = context.getDrawable(thumb!!)
    }

    companion object {
        private const val DEFAULT_PROGRESS = 51
        private const val DEFAULT_RADIUS = 30f
        private const val DEFAULT_HEIGHT = 150
        private const val NOT_SCALE = -1f
        private const val SCALE_MAX = 1f
        private const val DEFAULT_ACCEPT_TEXT = "ACCEPT"
        private const val DEFAULT_REJECT_TEXT = "REJECT"
        private const val DEFAULT_TEXT_SIZE = 16f
        private val DEFAULT_THUMB = R.drawable.ic_pin_button
    }
}