package cn.berberman.conveyorbeltrecorder

import android.view.View
import org.jetbrains.anko.*

class MainActivityUI : AnkoComponent<MainActivity> {

	companion object {
		const val RED_BUTTON_ID = 233
		const val GREEN_BUTTON_ID = 234
		const val YELLOW_BUTTON_ID = 235
		const val BLUE_BUTTON_ID = 236
		const val BLOCK1_ID = 237
		const val BLOCK2_ID = 238
		const val BLOCK3_ID = 239
		const val POP_BUTTON_ID = 240
	}

	override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
		relativeLayout {
			button {
				width = dip(80)
				height = dip(40)
				setBackgroundColor(context.getColor(R.color.red))
				id = RED_BUTTON_ID
			}.lparams {
				alignParentEnd()
				alignParentTop()
				marginEnd = dip(50)
				topMargin = dip(180)
			}

			button {
				width = dip(80)
				height = dip(40)
				setBackgroundColor(context.getColor(R.color.yellow))
				id = YELLOW_BUTTON_ID
			}.lparams {
				alignStart(RED_BUTTON_ID)
				below(RED_BUTTON_ID)
				topMargin = dip(15)
			}

			button {
				width = dip(80)
				height = dip(40)
				setBackgroundColor(context.getColor(R.color.green))
				id = GREEN_BUTTON_ID
			}.lparams {
				alignStart(YELLOW_BUTTON_ID)
				below(YELLOW_BUTTON_ID)
				topMargin = dip(15)
			}

			button {
				width = dip(80)
				height = dip(40)
				setBackgroundColor(context.getColor(R.color.colorPrimary))
				id = BLUE_BUTTON_ID
			}.lparams {
				alignStart(GREEN_BUTTON_ID)
				below(GREEN_BUTTON_ID)
				topMargin = dip(15)
			}

			imageView {
				id = BLOCK1_ID
			}.lparams {
				alignParentStart()
				below(RED_BUTTON_ID)
				marginStart = dip(30)
				width = dip(160)
				height = dip(80)
			}
			imageView {
				id = BLOCK2_ID
			}.lparams {
				alignStart(BLOCK1_ID)
				below(BLOCK1_ID)
				width = dip(160)
				height = dip(80)
			}

			imageView {
				id = BLOCK3_ID
			}.lparams {
				alignStart(BLOCK2_ID)
				below(BLOCK2_ID)
				width = dip(160)
				height = dip(80)
			}

			button {
				id = POP_BUTTON_ID
				text = "POP"
			}.lparams {
				below(BLOCK3_ID)
				centerHorizontally()
				topMargin = dip(100)
				width = dip(160)
				height = dip(80)
			}

		}
	}

}