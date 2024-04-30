package com.cti.displayuni.networks

import com.cti.displayuni.utility.myComponents
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*interface TokenProvider {
    fun getToken(): String?
    fun setToken(token: String?)
}

class SharedPreferencesTokenProvider(context: Context) : TokenProvider {

    private val sharedPreferences = context.getSharedPreferences("token_pref", Context.MODE_PRIVATE)

    override fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    override fun setToken(token: String?) {
        sharedPreferences.edit().putString("token", token).apply()
    }
}

class AuthInterceptor(private val tokenProvider: TokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = tokenProvider.getToken()

        if (token == null) {
            // If token is not available, throw an exception indicating unauthorized access
            throw IOException("Unauthorized access. Token is not available.")
        }

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}

 */

object RetrofitBuilder {

    // OLD BASE URL FOR PRODUCTION
    // private const val BASE_URL = "http://10.0.3.101:5000"
    private const val BASE_URL = "http://192.168.1.9:5000"

    // Define your logging interceptor
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    // Function to create OkHttpClient with token interceptor
    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
            .addInterceptor(loggingInterceptor)
            .readTimeout(10, TimeUnit.SECONDS)

        // Add interceptor to include token
        builder.addInterceptor { chain ->
            // Retrieve token from wherever you store it
            val token = retrieveToken()
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }

        return builder.build()
    }

    // Function to retrieve token (This is a placeholder, replace it with your actual token retrieval mechanism)
    private fun retrieveToken(): String {
        // This is just a placeholder, replace it with your actual token retrieval mechanism
        return myComponents.mainViewModel.getToken()
    }

    // Function to create ApiService instance without bearer token interceptor
    fun createApiServiceWithoutToken(): AuthAPIs {
        val okHttpClient = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
            .addInterceptor(loggingInterceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        val retrofitWithoutToken = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitWithoutToken.create(AuthAPIs::class.java)
    }

    fun createApiServiceWithToken(): OtherAPIs {

        val retrofitWithToken = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitWithToken.create(OtherAPIs::class.java)

    }
}
