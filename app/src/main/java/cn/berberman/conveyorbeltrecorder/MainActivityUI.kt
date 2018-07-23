package cn.berberman.conveyorbeltrecorder

import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.viewPager

class MainActivityUI : AnkoComponent<MainActivity> {

	companion object {
		val VIEW_PAGER_ID = 888
	}

	override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
		viewPager {
			id = VIEW_PAGER_ID
		}
	}
}