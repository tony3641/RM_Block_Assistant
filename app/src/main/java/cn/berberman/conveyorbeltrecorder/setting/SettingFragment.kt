package cn.berberman.conveyorbeltrecorder.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import cn.berberman.conveyorbeltrecorder.algorithm.PathSolver
import org.intellij.lang.annotations.Language
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.warn

class SettingFragment : Fragment(), TextWatcher, AnkoLogger {


	private lateinit var remoteHost: EditText

	@Language("RegExp")
	private val ipPattern =
			"^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))"
					.toPattern()

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val ui = UI { SettingUI().createView(this) }.view
		initView(ui)

		return ui
	}


	private fun initView(ui: View) {
		remoteHost = ui.findViewById(SettingUI.REMOTE_HOST_ID)
		remoteHost.addTextChangedListener(this)
	}

	override fun afterTextChanged(s: Editable) {

	}

	override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
	}

	override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
		s.toString().let {
			if (ipPattern.matcher(it).matches())
				PathSolver.breathlessServerHost = it
			else {
				toast("地址似乎不对~")
				warn("failed to match $it")
			}
		}
	}
}