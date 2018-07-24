package cn.berberman.conveyorbeltrecorder.algorithm

import android.support.annotation.ColorRes
import cn.berberman.conveyorbeltrecorder.R

object PathSolver {

	var breathlessServerHost = "192.168.1.103"


	enum class Color(@ColorRes val color: Int, val code: Int) {
		RED(R.color.red, 3),
		GREEN(R.color.green, 2),
		BLUE(R.color.colorPrimary, 1),
		YELLOW(R.color.yellow, 4),
		NULL(R.color.valid, 0);

		companion object {
			fun fromCode(code: Int) = when (code) {
				0    -> NULL
				1    -> BLUE
				2    -> GREEN
				3    -> RED
				4    -> YELLOW
				else -> NULL
			}
		}
	}


	fun encode(data: Array<Color>): IntArray {

		fun Array<Color>.indexesOf(color: Color): List<Int> {
			val r = mutableListOf<Int>()
			forEachIndexed { index, c -> if (c == color) r.add(index) }
			return r
		}


		val blue = data.indexesOf(Color.BLUE)
		val green = data.indexesOf(Color.GREEN)
		val red = data.indexesOf(Color.RED)
		val yellow = data.indexesOf(Color.YELLOW)

		fun checkSize(list: List<Int>) = list.size == 4

		val c = listOf(blue, green, red, yellow)



		return if (c.map(::checkSize).all { it })
			c.flatten().toIntArray() else IntArray(0)
	}

	fun decode(data: IntArray): Array<Color> {
		val result = Array(64) { Color.NULL }
		data.forEachIndexed { index, i ->
			if (index in 0..3) result[i] = Color.BLUE
			if (index in 4..7) result[i] = Color.GREEN
			if (index in 8..11) result[i] = Color.RED
			if (index in 12..15) result[i] = Color.YELLOW
		}
		return result
	}
}

