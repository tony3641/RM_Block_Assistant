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
import cn.berberman.conveyorbeltrecorder.algorithm.http.HttpUtil
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.toast

class PathSolverFragment : Fragment(), AnkoLogger, View.OnClickListener, View.OnLongClickListener {


	private lateinit var blocks: MutableList<ImageButton>

	private lateinit var yellowBlock: ImageButton
	private lateinit var blueBlock: ImageButton
	private lateinit var redBlock: ImageButton
	private lateinit var greenBlock: ImageButton

	private lateinit var doneButton: FloatingActionButton
	private lateinit var clearButton: FloatingActionButton

	private lateinit var colorData: Array<PathSolver.Color>

	private lateinit var activeColor: PathSolver.Color

	private lateinit var masks: Array<Boolean>

	private var isActiveCastle = true
	private var isSolved = false

	private var lastTick: Long = 0
	private var lastViewId: Int = 0

	private var isFinished = true
		set(value) {
			doneButton.isClickable = value
			field = value
		}

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val ui = UI { PathSolverUI().createView(this) }.view
		blocks = mutableListOf()
		initView(ui)
		colorData = Array(64) { Color.NULL }
		masks = Array(64) { false }
		activeColor = Color.NULL
		return ui
	}

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
		doneButton.setOnClickListener(this)

		doneButton.setOnLongClickListener(this)
		activeColor(Color.NULL)
	}

	override fun onClick(v: View) {


		when (v.id) {
			in PathSolverUI.getBlockViewId(1)
					..PathSolverUI.getBlockViewId(64) -> {
				PathSolverUI.getColorDataIndex(v.id).let {
					if (System.currentTimeMillis() - lastTick <= 500 && lastViewId == it && !isSolved) {
						colorData[it] = Color.NULL
						blocks[it].imageResource = 0
						refresh(it)
					} else {
						if (isSolved)
							masks[it] = !masks[it]
						else {
							colorData[it] = activeColor
							if (isActiveCastle && activeColor != Color.NULL)
								blocks[it].imageResource = R.drawable.ic_terrain_white_24dp
						}
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
				blocks.forEach {
					it.setBackgroundColor(context.getColor(Color.NULL.color))
					it.imageResource = 0
				}
				isActiveCastle = true
				isSolved = false
				for (i in colorData.indices) {
					colorData[i] = Color.NULL
					masks[i] = false
				}

			}

			PathSolverUI.DONE_BUTTON_ID               -> {
				val data = PathSolver.encode(colorData)
				debug("!data: " + data.joinToString())
				if (data.size != 16) {
					toast("图似乎不对~")
					return
				}
				isFinished = false

				if (PathSolver.breathlessServerHost == "") {
					toast("地址似乎不对~")
					return
				}

				val task =
						async(CommonPool) {
							HttpUtil.httpGet(PathSolver.breathlessServerHost,
									data.also { debug(it.joinToString()) })
						}
				launch(CommonPool) {
					val result: String
					try {
						result = task.await()
					} catch (e: Exception) {
						e.printStackTrace()
						onUiThread {
							toast("服务器连接错误~")
						}
						return@launch
					}
					if (result == "timeout") {
						onUiThread {
							toast("解图超时了~")
							for (i in colorData.indices) {
								colorData[i] = Color.NULL
								refresh(i)
							}
						}
						return@launch
					}

					val d = result.split("-")
							.map { it.toInt() }.toIntArray().map(Color.Companion::fromCode)

					onUiThread {
						if (d.size == 64) {
							for (i in colorData.indices) {
								colorData[i] = d[i]
								refresh(i)
								isSolved = true
							}
							isActiveCastle = false
						} else toast("图似乎无解~")

					}
				}
				isFinished = true
			}
		}
	}

	override fun onLongClick(v: View): Boolean {
		when (v.id) {
			PathSolverUI.DONE_BUTTON_ID -> {
				val template = "0-4-60-56-57-61-5-1-58-2-6-62-59-63-7-3"
						.split("-")
						.map { it.toInt() }
						.toIntArray()
						.let(PathSolver::decode)
				for (i in colorData.indices) {
					colorData[i] = template[i]
					blocks[i].imageResource = R.drawable.ic_terrain_white_24dp
					refresh(i)
				}
			}
		}
		return true
	}


	private fun refresh(index: Int) {
		colorData[index].let {
			blocks[index].setBackgroundColor(
					context.getColor(if (masks[index]) R.color.fuck else it.color))
			if (it == Color.NULL)
				blocks[index].imageResource = 0
		}
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