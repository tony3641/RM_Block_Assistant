package cn.berberman.conveyorbeltrecorder.startup

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import org.jetbrains.anko.*

class StartupActivityUI : AnkoComponent<StartupActivity> {
	override fun createView(ui: AnkoContext<StartupActivity>): View = with(ui) {
		linearLayout {
			gravity = Gravity.CENTER
			orientation = LinearLayout.VERTICAL

			imageView {

			}.lparams {
				bottomMargin = 100
				gravity = Gravity.CENTER
			}

			textView {
				text = "Deep♂Dark"
			}.lparams {
				gravity = Gravity.CENTER_HORIZONTAL
			}
		}
	}


}