package app.wzrx.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class MarketData(val id:Int,
                      @SerializedName("created_at")
                      val createdAt:String,
                    @SerializedName("price")
                    val price:String,
@SerializedName("created_at_object")
var dateCreated:Date?=null)