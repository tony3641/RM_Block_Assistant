package cn.berberman.conveyorbeltrecorder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.berberman.conveyorbeltrecorder.algorithm.PathSolverFragment
import cn.berberman.conveyorbeltrecorder.stack.BlockStackFragment
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

	private lateinit var blockStaticFragment: BlockStackFragment

	private lateinit var pathSolverFragment: PathSolverFragment

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		MainActivityUI().setContentView(this)
		blockStaticFragment = BlockStackFragment()
		pathSolverFragment = PathSolverFragment()
//		findViewById<FrameLayout>(MainActivityUI.FRAME_LAYOUT_ID).apply { setBackgroundResource(R.color.red) }
		fragmentManager.beginTransaction()
				.replace(MainActivityUI.FRAME_LAYOUT_ID, pathSolverFragment).commit()

	}
}