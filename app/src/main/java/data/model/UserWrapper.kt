package data.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserWrapper (
    @SerializedName("_embedded")
    @Expose
    val embeddedUsers: UserList
) {

    data class UserList(
        @SerializedName("cars")
        @Expose
        val allUsers: List<User>
    )

}