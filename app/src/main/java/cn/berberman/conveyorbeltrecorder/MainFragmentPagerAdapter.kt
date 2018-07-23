package cn.berberman.conveyorbeltrecorder

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainFragmentPagerAdapter(manager: FragmentManager, private val fragments: List<Fragment>)
	: FragmentPagerAdapter(manager) {
	override fun getItem(position: Int): Fragment = fragments[position]

	override fun getCount(): Int = fragments.size
}