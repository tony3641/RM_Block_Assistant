package cn.berberman.conveyorbeltrecorder.algorithm

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import cn.berberman.conveyorbeltrecorder.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.UI
import org.jetbrains.anko.debug

class PathSolverFragment : Fragment(), AnkoLogger {

	private lateinit var blocks: MutableList<ImageButton>

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
	                          savedInstanceState: Bundle?): View {
		val ui = UI { PathSolverUI().createView(this) }.view
		blocks = mutableListOf()
		initView(ui)
		return ui
	}

	private fun initView(view: View) {

		for (i in 1..64)
			view.findViewById<ImageButton>(PathSolverUI.getBlockViewId(i)).let(blocks::add)

		blocks.forEach {
			it.setBackgroundResource(R.color.red)
			debug(it.id)
		}
	}
}