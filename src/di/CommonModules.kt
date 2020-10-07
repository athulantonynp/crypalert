package app.wzrx.di

import app.wzrx.modules.MarketFetcher
import okhttp3.OkHttpClient
import org.koin.dsl.module

val commonModules= module {
    single {  OkHttpClient() }
    single { MarketFetcher() }
}