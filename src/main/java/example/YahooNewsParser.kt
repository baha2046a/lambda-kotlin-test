package example

import io.JSoupUtil
import io.WebService

object YahooNewsParser {
    private const val url = "https://news.yahoo.co.jp/"

    fun getNews() : List<String> {
        val htmlDoc = WebService.getDocument(url)
        return htmlDoc.map {
            JSoupUtil.elementStream(it, "p.yjnSub_list_headline")
                .map { p->p.text() }
                .distinct()
                .toJavaList()
        }.getOrElse { listOf("") }
    }
}