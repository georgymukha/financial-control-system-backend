package kz.mukha.fcs.util

enum class ResponseCode(val code: Int, val message: String, val data: Any? = null) {
    OK(0, "Everything is fine"),
    USER_CREATED(1, "User created successfully"),
    USER_DELETED(2, "User deleted successfully"),
    USER_EXISTS(-101, "User with entered username or email already exists"),
    USER_NOT_FOUND(-102, "User not found"),
    MISSED_PARAMETER(-103, "Missed parameter"),

    UNHANDLED_EXCEPTION(-666, "Unhandled exception was thrown. Please, report to https://t.me/themukha/");

    override fun toString(): String {
        return "$code: $message"
    }
}