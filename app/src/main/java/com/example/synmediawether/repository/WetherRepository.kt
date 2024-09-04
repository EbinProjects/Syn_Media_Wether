package com.example.synmediawether.repository

import com.example.synmediawether.Utilsss.ApiResponse
import com.example.synmediawether.pojo.WetherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WetherRepository(private val apiServices: ApiInterface) {

    suspend fun getWeather(
        longitude : Double,
        latitude : Double,
        key : String
    ) : Flow<ApiResponse<WetherResponse>> = flow {
        emit(ApiResponse.Loading)
        val response = apiServices.getWeatherData(  longitude = longitude, latitude = latitude, apiKey = key )
        emit(ApiResponse.Success(response))
    }.catch { e ->
        emit(ApiResponse.Error(mapToErrorType(e), e))
    }

    private fun mapToErrorType(exception: Throwable): ApiResponse.ErrorType {
        return when (exception) {
            is IOException -> ApiResponse.ErrorType.Network
            is HttpException -> {
                when (exception.code()) {
                    401 -> ApiResponse.ErrorType.Unauthorized
                    404 -> ApiResponse.ErrorType.NotFound
                    in 500..599 -> ApiResponse.ErrorType.Server
                    else -> ApiResponse.ErrorType.Unknown
                }
            }
            else -> ApiResponse.ErrorType.Unknown
        }
    }
}