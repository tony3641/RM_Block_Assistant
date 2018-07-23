package cn.berberman.conveyorbeltrecorder.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import org.jetbrains.anko.support.v4.UI

class SettingFragment : Fragment() {

	private lateinit var remoteHost: EditText

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val ui = UI { SettingUI().createView(this) }.view
		initView(ui)


		return ui
	}


	private fun initView(ui: View) {
		remoteHost = ui.findViewById(SettingUI.REMOTE_HOST_ID)
	}
}