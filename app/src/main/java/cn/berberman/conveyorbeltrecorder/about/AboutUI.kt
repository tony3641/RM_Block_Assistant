package cn.berberman.conveyorbeltrecorder.about

import android.graphics.BitmapFactory
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import cn.berberman.conveyorbeltrecorder.R
import cn.berberman.conveyorbeltrecorder.algorithm.PathSolver
import org.jetbrains.anko.*

class AboutUI : AnkoComponent<Fragment> {
	companion object {
		const val TEXT_VIEW_ID = 8102
		const val IMAGE_VIEW_ID = 8103
	}

	override fun createView(ui: AnkoContext<Fragment>): View = with(ui) {
		linearLayout {
			gravity = Gravity.CENTER
			orientation = LinearLayout.VERTICAL

			textView {
				id = TEXT_VIEW_ID
				textSize = 60f
				text = "Deep♂Dark"
				textColor = R.color.colorAccent
			}.lparams {
				bottomMargin = dip(120)
				gravity = Gravity.CENTER

			}

			imageView {
				id = IMAGE_VIEW_ID
				imageBitmap = BitmapFactory.decodeResource(resources, R.mipmap.billy)
				backgroundColor = PathSolver.Color.BLUE.color
			}.lparams {
				gravity = Gravity.CENTER_HORIZONTAL

				width = wrapContent
				height = wrapContent
			}


		}
	}

}