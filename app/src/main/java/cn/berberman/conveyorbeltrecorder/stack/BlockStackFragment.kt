package cn.berberman.conveyorbeltrecorder.stack

import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import cn.berberman.conveyorbeltrecorder.R
import org.jetbrains.anko.support.v4.UI

class BlockStackFragment : Fragment(), View.OnClickListener {


	private lateinit var red: Button

	private lateinit var yellow: Button

	private lateinit var green: Button

	private lateinit var blue: Button

	private lateinit var block1: ImageView

	private lateinit var block2: ImageView

	private lateinit var block3: ImageView

	private lateinit var popButton: FloatingActionButton

	private lateinit var blockStack: BlockStack

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val ui = UI { BlockStackUI().createView(this) }.view
		initView(ui)
		blockStack = BlockStack(listOf(block1, block2, block3))
		red.setOnClickListener(this)
		green.setOnClickListener(this)
		yellow.setOnClickListener(this)
		blue.setOnClickListener(this)
		popButton.setOnClickListener(this)
		return ui
	}

//	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View = run {
//		val ui = UI { BlockStackUI().createView(this) }.view
//		initView(ui)
//		blockStack = BlockStack(listOf(block1, block2, block3))
//		red.setOnClickListener(this)
//		green.setOnClickListener(this)
//		yellow.setOnClickListener(this)
//		blue.setOnClickListener(this)
//		popButton.setOnClickListener(this)
//		ui
//	}

	private fun initView(view: View) {
		with(view) {
			red = findViewById(BlockStackUI.RED_BUTTON_ID)
			yellow = findViewById(BlockStackUI.YELLOW_BUTTON_ID)
			green = findViewById(BlockStackUI.GREEN_BUTTON_ID)
			blue = findViewById(BlockStackUI.BLUE_BUTTON_ID)
			block1 = findViewById(BlockStackUI.BLOCK1_ID)
			block2 = findViewById(BlockStackUI.BLOCK2_ID)
			block3 = findViewById(BlockStackUI.BLOCK3_ID)
			popButton = findViewById(BlockStackUI.POP_BUTTON_ID)
		}
	}

	private fun getColor(@ColorRes id: Int): Int =
			context.getColor(id)


	override fun onClick(v: View) {
		when (v.id) {
			BlockStackUI.RED_BUTTON_ID    ->
				blockStack.push(getColor(R.color.red))

			BlockStackUI.GREEN_BUTTON_ID  ->
				blockStack.push(getColor(R.color.green))

			BlockStackUI.YELLOW_BUTTON_ID ->
				blockStack.push(getColor(R.color.yellow))

			BlockStackUI.BLUE_BUTTON_ID   ->
				blockStack.push(getColor(R.color.colorPrimary))

			BlockStackUI.POP_BUTTON_ID    ->
				blockStack.pop()

		}
	}


}
