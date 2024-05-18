package kz.mukha.fcs.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

object ResponseUtil {

    fun createResponse(
        httpStatus: HttpStatus,
        responseCode: ResponseCode,
        data: Any? = null
    ): ResponseEntity<Map<String, Any>> {
        val responseBody = mutableMapOf<String, Any>(
            "timestamp" to LocalDateTime.now(),
            "code" to responseCode.code,
            "message" to responseCode.message,
        )
        data?.let {
            responseBody["data"] = it
        }

        return ResponseEntity.status(httpStatus).body(responseBody)
    }
}