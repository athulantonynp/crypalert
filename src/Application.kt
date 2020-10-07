package app.wzrx

import app.wzrx.di.commonModules
import app.wzrx.modules.MarketFetcher
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.java.KoinJavaComponent.inject
import org.koin.ktor.ext.inject
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost()
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    install(org.koin.ktor.ext.Koin){ modules(commonModules) }

    val fetcher:MarketFetcher by inject()
    fetcher.start("ltcinr")

   /* routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/stat"){


          *//*  val request = Request.Builder()
                .url("http://www.google.com")
                .build()
            var response=okHttp.newCall(request).execute()
            var body=response.body?.string()
            if (body != null) {
                call.respondText ( body, contentType = ContentType.Text.Html)
            }else{
                call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
            }*//*
        }
    }*/
}

