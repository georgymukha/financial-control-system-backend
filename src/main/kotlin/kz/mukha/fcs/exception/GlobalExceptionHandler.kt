package kz.mukha.fcs.exception

import kz.mukha.fcs.util.LoggerUtil.getLogger
import kz.mukha.fcs.util.ResponseCode
import kz.mukha.fcs.util.ResponseUtil.createResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = getLogger(this::class.java)

    @ExceptionHandler(Exception::class)
    fun handleAllException(ex: Exception, request: WebRequest): ResponseEntity<Map<String, Any>> {
        logger.error("Unhandled exception occurred: ${ex.message}", ex)
        return createResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ResponseCode.UNHANDLED_EXCEPTION,
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException, request: WebRequest): ResponseEntity<Map<String, Any>> {
        logger.warn(ex.stackTraceToString())
        return createResponse(
            HttpStatus.BAD_REQUEST,
            ResponseCode.MISSED_PARAMETER,
        )
    }
}