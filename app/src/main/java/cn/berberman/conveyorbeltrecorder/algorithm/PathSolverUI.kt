package cn.berberman.conveyorbeltrecorder.algorithm

import android.app.Fragment
import android.view.View
import android.widget.GridLayout
import org.jetbrains.anko.*

class PathSolverUI : AnkoComponent<Fragment> {

	companion object {
		fun getBlockViewId(count: Int) = 700 + count
	}

	override fun createView(ui: AnkoContext<Fragment>): View = with(ui) {
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

		}
	}
}