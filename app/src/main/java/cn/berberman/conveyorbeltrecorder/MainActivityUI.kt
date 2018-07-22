package cn.berberman.conveyorbeltrecorder

import android.view.View
import org.jetbrains.anko.*

class MainActivityUI : AnkoComponent<MainActivity> {

	companion object {
		val FRAME_LAYOUT_ID = 888
	}

	override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
		linearLayout {

			frameLayout {
				id = FRAME_LAYOUT_ID
			}.lparams {
				width = matchParent
				height = matchParent
			}

		}
	}
}