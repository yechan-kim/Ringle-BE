package com.ringle.global.common.response.type

enum class ErrorCode(
    val httpStatus: Int,
    val code: String,
    val message: String,
) {
    // 400 Bad Request
    INVALID_INPUT(400, "VALIDATION_001", "유효하지 않은 입력값입니다"),
    MEMBERSHIP_INACTIVE(400, "MEMBERSHIP_002", "비활성화된 멤버십입니다"),
    NO_ACTIVE_MEMBERSHIP(400, "USER_MEMBERSHIP_002", "활성화된 멤버십이 없습니다"),
    FEATURE_NOT_AVAILABLE(400, "USER_MEMBERSHIP_006", "사용할 수 없는 기능입니다"),

    // 402 Payment Required
    PAYMENT_FAILED(402, "PAYMENT_004", "결제 처리에 실패했습니다"),

    // 404 Not Found
    USER_NOT_FOUND(404, "USER_001", "사용자를 찾을 수 없습니다"),
    MEMBERSHIP_NOT_FOUND(404, "MEMBERSHIP_001", "멤버십을 찾을 수 없습니다"),
    USER_MEMBERSHIP_NOT_FOUND(404, "USER_MEMBERSHIP_001", "사용자 멤버십을 찾을 수 없습니다"),
    PAYMENT_NOT_FOUND(404, "PAYMENT_005", "결제 정보를 찾을 수 없습니다"),

    // 409 Conflict
    USER_EMAIL_DUPLICATE(409, "USER_002", "이미 존재하는 이메일입니다"),
    MEMBERSHIP_NAME_DUPLICATE(409, "MEMBERSHIP_003", "이미 존재하는 멤버십 이름입니다"),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(500, "SYSTEM_001", "내부 서버 오류가 발생했습니다"),
} 
