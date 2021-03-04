package io

import io.vavr.control.Try
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object WebService {
    private const val CONNECTION_TIMEOUT = 15000

    fun getDocument(strUrl: String): Try<Document> {
        return Try.of {
            Jsoup.connect(strUrl)
                .ignoreHttpErrors(true)
                .timeout(CONNECTION_TIMEOUT).get()
        }
    }

    fun getDocument(strUrl: String, cookies: String): Try<Document> {
        return if (cookies.contains("=")) {
            val cookiesValue = cookies.split("=".toRegex()).toTypedArray()
            Try.of {
                Jsoup.connect(strUrl)
                    .cookie(cookiesValue[0], cookiesValue[1])
                    .header("Cookies", cookies)
                    .ignoreHttpErrors(true)
                    .timeout(CONNECTION_TIMEOUT).get()
            }
        } else Try.failure(Exception("Invalid Cookies"))
    }
}