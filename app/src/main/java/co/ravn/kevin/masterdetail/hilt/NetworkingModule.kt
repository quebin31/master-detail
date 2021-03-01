package co.ravn.kevin.masterdetail.hilt

import co.ravn.kevin.masterdetail.networking.Api
import co.ravn.kevin.masterdetail.networking.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkingModule {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private fun buildClient(): OkHttpClient = OkHttpClient.Builder().run {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        build()
    }

    private fun buildRetrofit(): Retrofit = Retrofit.Builder().run {
        val gson = GsonBuilder().setLenient().create()

        client(buildClient())
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create(gson))
        build()
    }

    private fun buildApiService(): ApiService = buildRetrofit().create(ApiService::class.java)

    @Provides
    fun provideRetrofitApi(): Api {
        val apiService = buildApiService()
        return Api(apiService)
    }
}
