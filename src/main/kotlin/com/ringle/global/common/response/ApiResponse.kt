package com.ringle.global.common.response

import com.ringle.global.common.response.type.ErrorCode
import com.ringle.global.common.response.type.SuccessCode

data class ApiResponse<T>(
    val status: Int,
    val code: String,
    val message: String,
    val result: T? = null,
) {
    companion object {
        fun success(success: SuccessCode): ApiResponse<Unit> =
            ApiResponse(
                status = success.httpStatus,
                code = success.code,
                message = success.message,
            )

        fun <T> success(success: SuccessCode, result: T): ApiResponse<T> =
            ApiResponse(
                status = success.httpStatus,
                code = success.code,
                message = success.message,
                result = result
            )

        fun error(e: ErrorCode): ApiResponse<Unit> =
            ApiResponse(
                status = e.httpStatus,
                code = e.code,
                message = e.message,
            )

        fun <T> error(e: ErrorCode, result: T): ApiResponse<T> =
            ApiResponse(
                status = e.httpStatus,
                code = e.code,
                message = e.message,
                result = result
            )
    }
}
