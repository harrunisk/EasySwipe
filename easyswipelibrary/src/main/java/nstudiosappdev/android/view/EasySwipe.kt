package nstudiosappdev.android.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.view_easy_swipe.view.*
import kotlin.math.absoluteValue
import nstudiosappdev.android.view.easyswipe.R

class EasySwipe : ConstraintLayout {

    /**
     * Listener to notify observer about
     * accept and reject cases
     */
    private lateinit var easySwipeListener: EasySwipeListener

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    /**
     * Initialize layout
     *
     * @param context
     * @param attributeSet
     */
    private fun init(context: Context, attributeSet: AttributeSet?) {

        val view = LayoutInflater.from(context).inflate(
            R.layout.view_easy_swipe,
            this,
            true
        )

        EasySwipeSettings(
            context,
            attributeSet!!,
            view
        )

        initAnimation()

    }

    private fun initAnimation () {

        seekBarPin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {

                seekBarMain.progress = MAX_PROGRESS - progress

                val alpha = (((progress - CENTER_PROGRESS).absoluteValue * 2).toFloat()) / MAX_PROGRESS
                textViewReject.alpha = MAX_ALPHA - alpha
                imageViewTickReject.alpha = MAX_ALPHA - alpha
                textViewAccept.alpha = MAX_ALPHA - alpha
                imageViewTickAccept.alpha = MAX_ALPHA - alpha

                when {
                    progress >= ANIMATION_ACCEPT_PLACE -> {
                        seekBarPin.progress = ANIMATION_ACCEPT_PLACE
                        seekBarMain.progress = MIN_PROGRESS
                    }
                    progress <= ANIMATION_REJECT_PLACE -> {
                        seekBarPin.progress = ANIMATION_REJECT_PLACE
                        seekBarMain.progress = MAX_PROGRESS
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //no op
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                val progress = seekBar.progress

                when {
                    progress > ACCEPT_THRESHOLD -> {
                        seekBarPin.progress = ANIMATION_ACCEPT_PLACE
                        seekBarMain.progress = MIN_PROGRESS
                        easySwipeListener.onAccepted()

                    }
                    progress < REJECT_THRESHOLD -> {
                        seekBarPin.progress = ANIMATION_REJECT_PLACE
                        seekBarMain.progress = MAX_PROGRESS
                        easySwipeListener.onRejected()
                    }
                    else -> {
                        seekBarMain.progress = CENTER_PROGRESS
                        seekBarPin.progress = CENTER_PROGRESS
                    }
                }
            }
        })

    }

    fun setListener(easySwipeListener: EasySwipeListener) {
        this.easySwipeListener = easySwipeListener
    }

    companion object {
        private const val ACCEPT_THRESHOLD = 80
        private const val REJECT_THRESHOLD = 20
        private const val MAX_PROGRESS = 100
        private const val CENTER_PROGRESS = 50
        private const val MIN_PROGRESS = 0
        private const val ANIMATION_ACCEPT_PLACE = 94
        private const val ANIMATION_REJECT_PLACE = 6

        private const val MAX_ALPHA = 1F
    }
}