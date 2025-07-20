package com.ringle.global.common.response.type

enum class SuccessCode(
    val httpStatus: Int,
    val code: String,
    val message: String,
) {
    // 200 OK - 성공
    REQUEST_OK(200, "OK", "요청이 성공적으로 처리되었습니다."),

    // 사용자 관련 성공 코드
    USER_DELETED(200, "USER_DELETED", "사용자가 성공적으로 삭제되었습니다"),

    // 멤버십 관련 성공 코드
    MEMBERSHIP_UPDATED(200, "MEMBERSHIP_UPDATED", "멤버십이 성공적으로 수정되었습니다."),
    MEMBERSHIP_DELETED(200, "MEMBERSHIP_DELETED", "멤버십이 성공적으로 삭제되었습니다."),
    MEMBERSHIP_DEACTIVATED(200, "MEMBERSHIP_DEACTIVATED", "멤버십이 성공적으로 비활성화되었습니다."),
    FEATURE_USED(200, "FEATURE_USED", "기능이 성공적으로 사용되었습니다."),

    // 201 Created - 생성 성공

    // 사용자 생성 관련 성공 코드
    USER_CREATED(201, "USER_CREATED", "사용자가 성공적으로 생성되었습니다."),

    // 멤버십 관련 성공 코드
    MEMBERSHIP_CREATED(201, "MEMBERSHIP_CREATED", "멤버십이 성공적으로 생성되었습니다."),
    MEMBERSHIP_ASSIGNED(201, "MEMBERSHIP_ASSIGNED", "멤버십이 성공적으로 할당되었습니다."),
    PAYMENT_COMPLETED(201, "PAYMENT_COMPLETED", "결제가 성공적으로 완료되었습니다."),
}
