package cn.berberman.conveyorbeltrecorder.setting

import android.content.Context
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T : Serializable>(context: Context, val string: String, private val default: T)
	: ReadWriteProperty<Any?, T> {

	override fun getValue(thisRef: Any?, property: KProperty<*>): T {
		return findPreference(string, default)
	}

	private val prefs = context.getSharedPreferences("deep♂dark", Context.MODE_PRIVATE)

	override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
		putPreference(string, value)
	}

	private fun <A : Serializable> putPreference(name: String, value: A) = with(prefs.edit()) {
		when (value) {
			is Long    -> putLong(name, value)
			is String  -> putString(name, value)
			is Int     -> putInt(name, value)
			is Boolean -> putBoolean(name, value)
			is Float   -> putFloat(name, value)
			else       -> putString(name, ByteArrayOutputStream().let {
				ObjectOutputStream(it).writeObject(value)
				it.toString()
			})
		}.apply()
	}

	private fun <U : Serializable> findPreference(name: String, default: U): U = with(prefs) {
		val res: Any = when (default) {
			is Long    -> getLong(name, default)
			is String  -> getString(name, default)
			is Int     -> getInt(name, default)
			is Boolean -> getBoolean(name, default)
			is Float   -> getFloat(name, default)
			else       -> throw IllegalArgumentException("未找到")
		}
		@Suppress("UNCHECKED_CAST")
		res as U
	}
}