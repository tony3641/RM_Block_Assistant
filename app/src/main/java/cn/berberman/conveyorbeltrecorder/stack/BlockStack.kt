package cn.berberman.conveyorbeltrecorder.stack

import android.graphics.Color
import android.widget.ImageView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.jetbrains.anko.verbose
import org.jetbrains.anko.warn
import java.util.*

class BlockStack(private val views: List<ImageView>) : AnkoLogger {

	private val stack = Stack<Int>()

	fun pop() {
		if (stack.size == 0) {
			warn("stack is empty!")
			views[0].context.toast("stack is empty!")
			return
		}
		verbose("stack pop:${stack.pop()}")
		refresh()
	}

	fun push(color: Int) {
		verbose("stack size: ${stack.size}")
		if (stack.size == 3) {
			warn("stack is full!")
			views[0].context.toast("stack is full!")
			return
		}
		verbose("stack push:${stack.push(color)}")
		refresh()
	}

	private fun refresh() {
		verbose("stack refresh")
		views[0].setBackgroundColor(stack.getOrNull(stack.size - 1) ?: Color.TRANSPARENT)
		views[1].setBackgroundColor(stack.getOrNull(stack.size - 2) ?: Color.TRANSPARENT)
		views[2].setBackgroundColor(stack.getOrNull(stack.size - 3) ?: Color.TRANSPARENT)
		verbose("stack content: ${stack.joinToString()}")

	}
}