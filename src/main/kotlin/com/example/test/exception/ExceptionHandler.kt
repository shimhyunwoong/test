/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.exception

import java.time.LocalDateTime
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * @author Hyunwoong Shim
 */
@ControllerAdvice
class ExceptionHandler(
) {
    @ExceptionHandler(value = [CustomException::class])
    fun handleRequestException(e: CustomException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(e.errorCode.httpStatus)
            .body(
                ErrorResponse(
                    LocalDateTime.now(),
                    e.errorCode.httpStatus.value(),
                    e.errorCode.httpStatus.name,
                    e.errorCode.name,
                    e.errorCode.errorCode,
                    e.errorCode.message
                )
            )
    }
}