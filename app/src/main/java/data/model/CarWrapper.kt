package data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CarWrapper(
    @SerializedName("_embedded")
    @Expose
    val embeddedCars: CarList
) {
    data class CarList(
        @SerializedName("cars")
        @Expose
        val allCars: List<Car>
    )

}