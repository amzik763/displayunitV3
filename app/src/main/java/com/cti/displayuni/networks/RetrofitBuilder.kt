package com.cti.displayuni.networks

import com.cti.displayuni.utility.myComponents
import com.cti.displayuni.utility.showLogs
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    // OLD BASE URL FOR PRODUCTION
    // private const val BASE_URL = "http://10.0.3.101:5000"
    private const val BASE_URL = "http://192.168.43.95:5000"
//    private const val BASE_URL = "http://10.0.2.2:5000"

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
            try {
                // Retrieve token from wherever you store it
                val token = retrieveToken()
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            } catch (e: IOException) {
                showLogs("GOT ERROR: ","error IO")

                // Handle IOException




                // For example, log the error
                e.printStackTrace()
                // Rethrow the exception to propagate it further if needed
                throw e
            }catch (e: HttpException) {
                showLogs("GOT ERROR: ","error HTTP")

                // Handle IOException
                // For example, log the error
                e.printStackTrace()
                // Rethrow the exception to propagate it further if needed
                throw e
            } catch (e: Exception) {
                showLogs("GOT ERROR: ","error")
                // Handle other types of exceptions
                // For example, log the error
                e.printStackTrace()
                // Rethrow the exception to propagate it further if needed
                throw e
            }
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


