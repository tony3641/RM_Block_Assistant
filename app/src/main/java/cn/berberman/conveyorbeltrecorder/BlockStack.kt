package cn.berberman.conveyorbeltrecorder

import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import java.util.*

class BlockStack(private val views: List<ImageView>) {

	companion object {
		private val TAG: String = BlockStack::class.java.simpleName
	}

	private val stack = Stack<Int>()

	init {
		Log.d(TAG, "init")
		/*views.forEach {
			it.setBackgroundColor(Color.TRANSPARENT)
		}
		for (i in 0..2)
			stack.push(Color.TRANSPARENT)*/
	}

	fun pop() {
		if (stack.size == 0) return
		Log.d(TAG, "pop")
		stack.pop()
		refresh()
	}

	fun push(color: Int) {
		Log.d(TAG, "stack size: ${stack.size}")
		if (stack.size == 3) return
		stack.push(color)
		refresh()
	}

	private fun refresh() {
		Log.d(TAG, "refresh")
		views[0].setBackgroundColor(stack.getOrNull(stack.size - 1) ?: Color.TRANSPARENT)
		views[1].setBackgroundColor(stack.getOrNull(stack.size - 2) ?: Color.TRANSPARENT)
		views[2].setBackgroundColor(stack.getOrNull(stack.size - 3) ?: Color.TRANSPARENT)
		Log.d(TAG, "stack: ${stack.joinToString()}")

	}
}