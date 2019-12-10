package data.model

import androidx.room.*
import com.google.gson.annotations.*
import java.io.Serializable

@Entity(tableName = "cars")

data class Car(
    @SerializedName("id")
    @PrimaryKey val id: Long,

    @SerializedName("price")
    var price: Long,

    @SerializedName("model")
    var model: String,

    @SerializedName("yr")
    var year: String,

    @SerializedName("km")
    var km: String,

    @SerializedName("engine")
    var engine: String

) : Serializable