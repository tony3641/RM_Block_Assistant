package cn.berberman.conveyorbeltrecorder.setting

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import cn.berberman.conveyorbeltrecorder.algorithm.PathSolver
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.warn

class SettingFragment : Fragment(), TextWatcher, AnkoLogger {


	private lateinit var remoteHost: EditText

	private lateinit var savedRemoteHost: SharedPreferences


	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val ui = UI { SettingUI().createView(this) }.view
		initView(ui)
		savedRemoteHost = context.getSharedPreferences("savedRemoteHost", Context.MODE_PRIVATE)
		val ip = savedRemoteHost.getString("ip", "")
		remoteHost.setText(ip, TextView.BufferType.EDITABLE)
		PathSolver.breathlessServerHost = ip
		return ui
	}


	private fun initView(ui: View) {
		remoteHost = ui.findViewById(SettingUI.REMOTE_HOST_ID)
		remoteHost.addTextChangedListener(this)
	}

	override fun afterTextChanged(s: Editable) {
		s.toString().let {
			if (PathSolver.ipPattern.matcher(it).matches()) {
				PathSolver.breathlessServerHost = it
				savedRemoteHost.edit().putString("ip", it).apply()
				toast("地址改变: ${PathSolver.breathlessServerHost}")
			} else {
				toast("地址似乎不对~")
				warn("failed to match $it")
			}
		}
	}

	override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
	}

	override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

	}
}