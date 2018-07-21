package cn.berberman.conveyorbeltrecorder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity(), View.OnClickListener {


	private lateinit var red: Button

	private lateinit var yellow: Button

	private lateinit var green: Button

	private lateinit var blue: Button

	private lateinit var block1: ImageView

	private lateinit var block2: ImageView

	private lateinit var block3: ImageView

	private lateinit var popButton: Button

	private lateinit var blockStack: BlockStack

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		MainActivityUI().setContentView(this)
		initView()

		blockStack = BlockStack(listOf(block1, block2, block3))

		red.setOnClickListener(this)
		green.setOnClickListener(this)
		yellow.setOnClickListener(this)
		blue.setOnClickListener(this)
		popButton.setOnClickListener(this)

	}


	private fun initView() {
		red = findViewById(MainActivityUI.RED_BUTTON_ID)
		yellow = findViewById(MainActivityUI.YELLOW_BUTTON_ID)
		green = findViewById(MainActivityUI.GREEN_BUTTON_ID)
		blue = findViewById(MainActivityUI.BLUE_BUTTON_ID)
		block1 = findViewById(MainActivityUI.BLOCK1_ID)
		block2 = findViewById(MainActivityUI.BLOCK2_ID)
		block3 = findViewById(MainActivityUI.BLOCK3_ID)
		popButton = findViewById(MainActivityUI.POP_BUTTON_ID)
	}

	override fun onClick(v: View) {
		when (v.id) {
			MainActivityUI.RED_BUTTON_ID    -> {
				blockStack.push(getColor(R.color.red))
			}
			MainActivityUI.GREEN_BUTTON_ID  -> {
				blockStack.push(getColor(R.color.green))
			}
			MainActivityUI.YELLOW_BUTTON_ID -> {
				blockStack.push(getColor(R.color.yellow))
			}
			MainActivityUI.BLUE_BUTTON_ID   -> {
				blockStack.push(getColor(R.color.colorPrimary))
			}
			MainActivityUI.POP_BUTTON_ID    -> {
				blockStack.pop()
			}
		}
	}


}
