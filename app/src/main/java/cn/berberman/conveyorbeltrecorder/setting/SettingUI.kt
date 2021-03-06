package cn.berberman.conveyorbeltrecorder.setting

import android.support.v4.app.Fragment
import android.text.InputType
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.editText
import org.jetbrains.anko.linearLayout

class SettingUI : AnkoComponent<Fragment> {

	companion object {
		const val REMOTE_HOST_ID = 1024
	}

	override fun createView(ui: AnkoContext<Fragment>): View = with(ui) {
		linearLayout {
			gravity = Gravity.CENTER

			editText {
				id = REMOTE_HOST_ID
				hint = "服务器地址~"

				inputType = InputType.TYPE_TEXT_VARIATION_URI
			}
		}
	}

}