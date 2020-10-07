package app.wzrx.di

import okhttp3.OkHttpClient
import org.koin.dsl.module

val commonModules= module {
    single {  OkHttpClient() }
}