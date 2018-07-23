package cn.berberman.conveyorbeltrecorder

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import cn.berberman.conveyorbeltrecorder.algorithm.PathSolverFragment
import cn.berberman.conveyorbeltrecorder.setting.SettingFragment
import cn.berberman.conveyorbeltrecorder.stack.BlockStackFragment
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

	private lateinit var blockStaticFragment: BlockStackFragment

	private lateinit var pathSolverFragment: PathSolverFragment

	private lateinit var settingFragment: SettingFragment

	private lateinit var viewPager: ViewPager

	private lateinit var fragmentPagerAdapter: MainFragmentPagerAdapter
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		//window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
		requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
		MainActivityUI().setContentView(this)
		blockStaticFragment = BlockStackFragment()
		pathSolverFragment = PathSolverFragment()
		settingFragment = SettingFragment()

		viewPager = findViewById(MainActivityUI.VIEW_PAGER_ID)
		fragmentPagerAdapter = MainFragmentPagerAdapter(supportFragmentManager,
				listOf(pathSolverFragment, blockStaticFragment, settingFragment))

		viewPager.adapter = fragmentPagerAdapter
	}
}