/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.exception

import org.springframework.http.HttpStatus

/**
 * @author Hyunwoong Shim
 */
open class UserException(
    val code: String,
    val httpStatus: HttpStatus,
    override val message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause) {
    constructor(errorCode: ErrorCode, messageMapper: ((String) -> String)? = null, cause: Throwable? = null) : this(
        errorCode.errorCode,
        errorCode.httpStatus,
        messageMapper?.let { messageMapper(errorCode.message) } ?: errorCode.message,
        cause
    )

    constructor(errorCode: ErrorCode, additionalMessage: String, cause: Throwable? = null) : this(
        errorCode.errorCode,
        errorCode.httpStatus,
        "${errorCode.message} $additionalMessage",
        cause
    )
}