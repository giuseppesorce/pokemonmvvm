package com.giuseppesorce.data.extension

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Giuseppe Sorce
 */


//import com.giuseppesorce.data.extension.ServerResponse
//import com.google.gson.Gson
//
//import kotlinx.coroutines.CancellableContinuation
//import kotlinx.coroutines.suspendCancellableCoroutine
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import kotlin.coroutines.resume


suspend fun <T : Any> Call<T>.resultAwait(): Result<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                val result: Result<T> = if (response.isSuccessful) {
                    val body = response.body()
                    if (body == null) {
                        Result.Error(
                            NetworkError.decodeError(null, "Response with null body"),
                            response.raw()
                        )
                    } else {
                        Result.Ok(body, response.raw())
                    }

                } else {
                    Result.Error(
                        NetworkError.decodeError(
                            null,
                            "Error, server not available"
                        ), response.raw()
                    )
                }
                continuation.resume(result) {

                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (continuation.isCancelled) return
            }
        })
        registerOnCompletion(continuation)
    }


}






private fun Call<*>.registerOnCompletion(continuation: CancellableContinuation<*>) {
    continuation.invokeOnCancellation {
        if (continuation.isCancelled)
            try {
                cancel()
            } catch (ex: Throwable) {
                //Ignore cancel exception
            }
    }
}

//@Suppress("unused")
sealed class Result<out T : Any> {
    /**
     * Successful result of request without errors
     */
    class Ok<out T : Any>(
        val value: T?,
        override val response: okhttp3.Response
    ) : Result<T>(), ResponseResult {
        override fun toString() = "Result.Ok{value=$value, response=$response}"
    }

    /**
     * Error result
     */
    class Error(
        override val error: NetworkError,
        override val response: okhttp3.Response?,
        val exception: Throwable? = null
    ) : Result<Nothing>(), ErrorResult, ResponseResult {
        override fun toString() = "Result.Error{name=${error.description}}"
    }


}


// Interface for [Result] classes with [okhttp3.Response]: [Result.Ok] and [Result.Error]

interface ResponseResult {
    val response: okhttp3.Response?
}

//
///**
// * Interface for [Result] classes that contains [NetworkError]: [Result.Error]
// */
interface ErrorResult {
    val error: NetworkError
}

//
sealed class NetworkError(val name: String, val description: String?, status: Int = 0) {
    class UnknownHost(name: String, desc: String?) : NetworkError(name, desc)
    class Timeout(name: String, desc: String?) : NetworkError(name, desc)
    class JsonSyntax(name: String, desc: String?) : NetworkError(name, desc)

    class InvalidCredentials(name: String, desc: String?) : NetworkError(name, desc)
    class UserExists(name: String, desc: String?) : NetworkError(name, desc)
    class Unauthorized(name: String, desc: String?) : NetworkError(name, desc)
    class EndpointNotFound(name: String, desc: String?) : NetworkError(name, desc)
    class ResourceNotFound(name: String, desc: String?) : NetworkError(name, desc)
    class ValidationError(name: String, desc: String?) : NetworkError(name, desc)
    class DatabaseError(name: String, desc: String?) : NetworkError(name, desc)
    class SignupFailed(name: String, desc: String?) : NetworkError(name, desc)
    class AuthMissingInfo(name: String, desc: String?) : NetworkError(name, desc)
    class AuthFailed(name: String, desc: String?) : NetworkError(name, desc)
    class AuthRequestError(name: String, desc: String?) : NetworkError(name, desc)
    class UserNotConfirmed(name: String, desc: String?) : NetworkError(name, desc)
    class ClientGUIDMissing(name: String, desc: String?) : NetworkError(name, desc)
    class BannedUser(name: String, desc: String?) : NetworkError(name, desc)
    class InvalidAPIKey(name: String, desc: String?) : NetworkError(name, desc)
    class InvalidOldPassword(name: String, desc: String?) : NetworkError(name, desc)
    class FailedToRestorePwd(name: String, desc: String?) : NetworkError(name, desc)
    class WeakPassword(name: String, desc: String?) : NetworkError(name, desc)
    class MaintenanceMode(name: String, desc: String?) : NetworkError(name, desc)
    class ObsoleteVersion(name: String, desc: String?) : NetworkError(name, desc)
    class Custom(name: String, desc: String?) : NetworkError(name, desc)

    companion object {
        fun decodeError(name: String?, desc: String?): NetworkError =
            when (name) {
                //TODO fazine: inserire le stringhe e cancellare quelle cablate"
                // IO
                "UnknownHostException" -> UnknownHost(
                    name,
                    "La connessione a internet sembra essere disattivata"
                )
                "SocketTimeoutException" -> Timeout(name, "Il server non ha risposto correttamente")
                // json
                "JsonSyntaxException" -> JsonSyntax(name, "JSON MARFOMERD")
                // atlas
                "AuthInvalidCredentials" -> InvalidCredentials(name, desc)
                "AuthUserAlreadyExists" -> UserExists(name, desc)
                "AuthUnauthorized" -> Unauthorized(name, desc)
                "EndpointNotFound" -> EndpointNotFound(name, desc)
                "ResourceNotFound" -> ResourceNotFound(name, desc)
                "ValidationError" -> ValidationError(name, desc)
                "DatabaseError" -> DatabaseError(name, desc)
                "AuthSignupError" -> SignupFailed(name, desc)
                "AuthMissingInformation" -> AuthMissingInfo(name, desc)
                "AuthFailed" -> AuthFailed(name, desc)
                "AuthRequestError" -> AuthRequestError(name, desc)
                "AuthUserNotConfirmed" -> UserNotConfirmed(name, desc)
                "AuthClientGUIDMissing" -> ClientGUIDMissing(name, desc)
                "AuthBannedUser" -> BannedUser(name, desc)
                "AuthInvalidApiKey" -> InvalidAPIKey(name, desc)
                "AuthInvalidOldPassword" -> InvalidOldPassword(name, desc)
                "AuthRestorePasswordError" -> FailedToRestorePwd(name, desc)
                "AuthWeakPassword" -> WeakPassword(name, desc)
                "MaintenanceMode" -> MaintenanceMode(name, desc)
                "ObsoleteVersion" -> ObsoleteVersion(name, desc)
                else -> Custom(name ?: "Unknown", desc)
            }

    }
}
