package com.xy.inc.advice

import com.xy.inc.constant.APIConstant
import com.xy.inc.exception.PointOfInterestException
import com.xy.inc.response.ErrorResponse
import com.xy.inc.response.Response
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.lang.Exception
import java.time.Instant

@ControllerAdvice
class RestExceptionHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(value = [(PointOfInterestException::class)])
    fun handlePointOfInterestException(ex: PointOfInterestException, request: WebRequest):
            ResponseEntity<Response<ErrorResponse>> {
        log.error("Error in handlePointOfInterestException: {}", ex.message)

        val errorResponse = ErrorResponse(
                Instant.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                APIConstant.ERROR_400,
                APIConstant.DETAILS_ERROR_400
        )

        val response = Response(data = errorResponse)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(Exception::class)])
    fun handleException(ex: Exception, request: WebRequest):
            ResponseEntity<Response<ErrorResponse>> {
        log.error("Error in handleRuntimeException: {}", ex.message)

        val errorResponse = ErrorResponse(
                Instant.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                APIConstant.ERROR_500,
                APIConstant.DETAILS_ERROR_500
        )
        val response = Response(data = errorResponse)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}