package data.remote


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {

    companion object {
        private val retrofit = Retrofit.Builder()

            .baseUrl("http://10.5.231.11:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        fun <S> buildService(serviceType: Class<S>): S {
            return retrofit.create(serviceType)
        }
    }
}