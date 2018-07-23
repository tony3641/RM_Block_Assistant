package cn.berberman.conveyorbeltrecorder.algorithm

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import cn.berberman.conveyorbeltrecorder.R
import cn.berberman.conveyorbeltrecorder.algorithm.PathSolver.Color
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.UI

class PathSolverFragment : Fragment(), AnkoLogger, View.OnClickListener {


	private lateinit var blocks: MutableList<ImageButton>

	private lateinit var yellowBlock: ImageButton
	private lateinit var blueBlock: ImageButton
	private lateinit var redBlock: ImageButton
	private lateinit var greenBlock: ImageButton

	private lateinit var doneButton: FloatingActionButton
	private lateinit var clearButton: FloatingActionButton

	private lateinit var colorData: Array<PathSolver.Color>

	private lateinit var activeColor: PathSolver.Color

	private var lastTick: Long = 0
	private var lastViewId: Int = 0

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val ui = UI { PathSolverUI().createView(this) }.view
		blocks = mutableListOf()
		initView(ui)
		colorData = Array(64) { Color.NULL }
		activeColor = Color.NULL
		return ui
	}

//	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
//	                          savedInstanceState: Bundle?): View {
//		val ui = UI { PathSolverUI().createView(this) }.view
//		blocks = mutableListOf()
//		initView(ui)
//		colorData = Array(64) { Color.NULL }
//		activeColor = Color.NULL
//		return ui
//	}

	private fun initView(view: View) {

		for (i in 1..64)
			view.findViewById<ImageButton>(PathSolverUI.getBlockViewId(i)).let(blocks::add)

		blocks.forEach { it.setBackgroundResource(R.color.valid) }

		yellowBlock = view.findViewById(PathSolverUI.YELLOW_BUTTON_ID)
		redBlock = view.findViewById(PathSolverUI.RED_BUTTON_ID)
		blueBlock = view.findViewById(PathSolverUI.BLUE_BUTTON_ID)
		greenBlock = view.findViewById(PathSolverUI.GREEN_BUTTON_ID)

		clearButton = view.findViewById(PathSolverUI.CLEAR_BUTTON_ID)
		doneButton = view.findViewById(PathSolverUI.DONE_BUTTON_ID)


		yellowBlock.setOnClickListener(this)
		blueBlock.setOnClickListener(this)
		greenBlock.setOnClickListener(this)
		redBlock.setOnClickListener(this)
		blocks.forEach {
			it.setOnClickListener(this)
		}
		clearButton.setOnClickListener(this)

		activeColor(Color.NULL)
	}

	override fun onClick(v: View) {


		when (v.id) {

			in PathSolverUI.getBlockViewId(1)
					..PathSolverUI.getBlockViewId(64) -> {
				PathSolverUI.getColorDataIndex(v.id).let {

					if (System.currentTimeMillis() - lastTick <= 500 && lastViewId == it) {
						colorData[it] = Color.NULL
						refresh(it)
					} else {
						colorData[it] = activeColor
						refresh(it)
						lastTick = System.currentTimeMillis()
						lastViewId = it
					}
				}
			}

			PathSolverUI.YELLOW_BUTTON_ID             -> activeColor(Color.YELLOW)
			PathSolverUI.RED_BUTTON_ID                -> activeColor(Color.RED)
			PathSolverUI.GREEN_BUTTON_ID              -> activeColor(Color.GREEN)
			PathSolverUI.BLUE_BUTTON_ID               -> activeColor(Color.BLUE)

			PathSolverUI.CLEAR_BUTTON_ID              -> {
				blocks.forEach { it.setBackgroundColor(context.getColor(Color.NULL.color)) }
				for (i in colorData.indices)
					colorData[i] = Color.NULL
			}
		}
	}


	private fun refresh(index: Int) {
		blocks[index].setBackgroundColor(context.getColor(colorData[index].color))
	}

	private fun activeColor(color: Color) {
		yellowBlock.imageAlpha = 0
		blueBlock.imageAlpha = 0
		greenBlock.imageAlpha = 0
		redBlock.imageAlpha = 0

		when (color) {
			Color.YELLOW -> yellowBlock.imageAlpha = 255
			Color.RED    -> redBlock.imageAlpha = 255
			Color.GREEN  -> greenBlock.imageAlpha = 255
			Color.BLUE   -> blueBlock.imageAlpha = 255
			Color.NULL   -> {
			}
		}
		activeColor = color
	}


}