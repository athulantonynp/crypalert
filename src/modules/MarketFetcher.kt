package app.wzrx.modules

import app.wzrx.models.MarketData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dorkbox.notify.Notify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.KoinComponent
import org.koin.core.inject
import utils.Log

class MarketFetcher:KoinComponent{

    val okHttp: OkHttpClient by inject()
    var marketSlug=""

    val responseType = object : TypeToken<ArrayList<MarketData>>() {}.type


    fun start(marketSlug:String){
        try {
            attemptConnectionToWazirX(marketSlug)
        }catch (e:Exception){

        }
    }

    private fun attemptConnectionToWazirX(marketSlug: String) {
        GlobalScope.async {
            while (true){
                val response=getMarketFromWazirX(marketSlug)
                response?.price?.let { Log.e(it) }
                Notify.create()
                    .title("Title Text")
                    .text(response?.price)
                    .showWarning()
                delay(1000L)
            }
        }
    }

    private suspend fun getMarketFromWazirX(marketSlug: String): MarketData? {
        try {
            val request = Request.Builder()
                .url("https://api.wazirx.com/api/v2/trades?market=${marketSlug}")
                .build()
            var response=okHttp.newCall(request).execute()
            var string=response.body?.string()
            var responseObject=Gson().fromJson<ArrayList<MarketData>>(string,responseType)
            /*responseObject.map {
                it.dateCreated= SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH).parse(it.createdAt)
            }*/

            return responseObject.sortedByDescending { it.id }.first()

        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }
        return null
    }
}