package data.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.*
import java.io.Serializable

@Dao
@Entity(tableName="users")
class User(
    @SerializedName("id")
    @PrimaryKey val id: Long,

    @SerializedName("first_name")
    val first_name: String,


    @SerializedName("last_name")
    val last_name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("phone")
    val phone: String,
    @SerializedName("password")
    val password: String,

    @SerializedName("conf_password")
    val conf_password: String
    ):Serializable