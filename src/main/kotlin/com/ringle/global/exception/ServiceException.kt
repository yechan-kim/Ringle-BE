package com.ringle.global.exception

import com.ringle.global.common.response.type.ErrorCode

class ServiceException(
    val errorCode: ErrorCode,
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message ?: errorCode.message, cause) {
}
