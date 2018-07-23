package cn.berberman.conveyorbeltrecorder.algorithm

import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import cn.berberman.conveyorbeltrecorder.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton

class PathSolverUI : AnkoComponent<Fragment> {

	companion object {
		fun getBlockViewId(count: Int) = 700 + count
		fun getColorDataIndex(id: Int) = id - 701
		const val YELLOW_BUTTON_ID = 901
		const val BLUE_BUTTON_ID = 902
		const val GREEN_BUTTON_ID = 903
		const val RED_BUTTON_ID = 904
		const val DONE_BUTTON_ID = 905
		const val CLEAR_BUTTON_ID = 906
	}

	override fun createView(ui: AnkoContext<Fragment>): View = with(ui) {
		linearLayout {
			orientation = LinearLayout.VERTICAL
			gravity = Gravity.CENTER
			gridLayout {
				rowCount = 8
				columnCount = 8

				for (i in 1..64)
					imageButton {
						id = getBlockViewId(i)
					}.lparams {
						margin = dip(2)
						width = dip(20)
						height = dip(20)
						columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f)
						rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f)
					}

			}.lparams {
				gravity = Gravity.CENTER
				width = dip(350)
				height = dip(350)

			}
			linearLayout {
				orientation = LinearLayout.HORIZONTAL
				imageButton {
					id = YELLOW_BUTTON_ID
					setBackgroundColor(ctx.getColor(R.color.yellow))
					imageResource = R.drawable.ic_create_white_24dp
				}.lparams {
					width = dip(70)
					height = dip(50)
				}
				imageButton {
					id = BLUE_BUTTON_ID
					setBackgroundColor(ctx.getColor(R.color.colorPrimary))
					imageResource = R.drawable.ic_create_white_24dp
				}.lparams {
					width = dip(70)
					height = dip(50)
				}
				imageButton {
					id = RED_BUTTON_ID
					setBackgroundColor(ctx.getColor(R.color.red))
					imageResource = R.drawable.ic_create_white_24dp
				}.lparams {
					width = dip(70)
					height = dip(50)
				}
				imageButton {
					id = GREEN_BUTTON_ID
					setBackgroundColor(ctx.getColor(R.color.green))
					imageResource = R.drawable.ic_create_white_24dp
				}.lparams {
					width = dip(70)
					height = dip(50)
				}

			}.lparams {
				margin = dip(20)
			}
			linearLayout {
				gravity = Gravity.CENTER_HORIZONTAL
				floatingActionButton {
					id = DONE_BUTTON_ID
					imageResource = R.drawable.ic_done_white_24dp
				}.lparams {
					width = wrapContent
					height = wrapContent
				}
				floatingActionButton {
					id = CLEAR_BUTTON_ID
					imageResource = R.drawable.ic_delete_white_24dp
				}.lparams {
					leftMargin = dip(20)
					width = wrapContent
					height = wrapContent
				}
			}
		}
	}
}