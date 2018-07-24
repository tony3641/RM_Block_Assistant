package cn.berberman.conveyorbeltrecorder.algorithm.http

import cn.berberman.conveyorbeltrecorder.algorithm.PathSolver
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.experimental.suspendCoroutine

object HttpUtil {
	suspend fun httpGet(data: IntArray): String =
			suspendCoroutine {
				val url = URL("http://${PathSolver.breathlessServerHost}:2333/breathless/" +
						data.joinToString(separator = "-"))
				val connection = url.openConnection() as HttpURLConnection
				with(connection) {
					requestMethod = "GET"
					connectTimeout = 3000
					readTimeout = 9000
				}
				try {
					it.resume(connection.inputStream.bufferedReader()
							.readLine())
				} catch (e: Exception) {
					it.resumeWithException(e)
				} finally {
					connection.disconnect()
				}
			}


}