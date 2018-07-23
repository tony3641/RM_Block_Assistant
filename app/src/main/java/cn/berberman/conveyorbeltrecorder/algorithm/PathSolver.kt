package cn.berberman.conveyorbeltrecorder.algorithm

import android.support.annotation.ColorRes
import cn.berberman.conveyorbeltrecorder.R

class PathSolver {
	enum class Color(@ColorRes val color: Int) {
		RED(R.color.red),
		GREEN(R.color.green),
		BLUE(R.color.colorPrimary),
		YELLOW(R.color.yellow),
		NULL(R.color.valid)
	}
}