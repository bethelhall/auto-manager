package data.model

import androidx.room.*
import com.google.gson.annotations.*
import java.io.Serializable

@Entity(tableName = "cars")

data class Car(
    @SerializedName("id")
    @PrimaryKey val id: Long,

    @SerializedName("price")
    val price: Long,

    @SerializedName("model")
    val model: String,

    @SerializedName("yr")
    val year: String,

    @SerializedName("km")
    val km: String,

    @SerializedName("engine")
    val engine: String

) : Serializable