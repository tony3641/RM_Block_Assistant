package cn.berberman.conveyorbeltrecorder.http

import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.experimental.suspendCoroutine

object HttpUtil {
	suspend fun httpGet(host: String, data: IntArray): String =
			suspendCoroutine {
				val url = URL("$host/breathless/${data.joinToString(separator = "-")}")
				val connection = url.openConnection() as HttpURLConnection
				with(connection) {
					requestMethod = "GET"
					connectTimeout = 5000
					readTimeout = 5000
				}

				try {
					it.resume(connection.inputStream.bufferedReader()
							.readLine())
				} catch (e: Exception) {
					e.printStackTrace()
					it.resumeWithException(e)
				} finally {
					connection.disconnect()
				}
			}


}