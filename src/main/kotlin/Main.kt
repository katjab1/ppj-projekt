/*import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}*/

package scraping

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.eachText
import it.skrape.selects.html5.*

fun main() {
    println("========== scraping - printing example ==========")
    // docs: https://docs.skrape.it/docs/
    skrape(HttpFetcher) {   // <-- pass any Fetcher, e.g. HttpFetcher, BrowserFetcher, ...
        request {
            // request options goes here, e.g the most basic would be url
            // https://docs.skrape.it/docs/http-client/request-options
            url = "https://www.clever-fit.com/sl/studios/"
        }

        response {
            // do stuff with the response like parsing the response body
            // https://docs.skrape.it/docs/http-client/response
            println("http status code: ${status { code }}")
            println("http status message: ${status { message }}")

            htmlDocument {
                header {
                    withClass = "eael-entry-header"

                    h2 {
                        withClass = "eael-entry-title"
                        a {
                            findAll { eachText.forEach { println(it) } }
                        }

                    }
                }
            }
        }
    }
}
