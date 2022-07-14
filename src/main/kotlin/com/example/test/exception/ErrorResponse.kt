/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.test.exception

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import java.time.OffsetDateTime

/**
 * @author Hyunwoong Shim
 */
data class ErrorResponse(
    @field:JsonProperty("result_code")
    val resultCode: String,

    @field:JsonProperty("http_status")
    val httpStatus: String,

    val timestamp: OffsetDateTime = OffsetDateTime.now(),
    val message: String,
    val path: String,
    val error: MutableList<Error>?= mutableListOf()
)

data class Error(
    val field:String,
    val message: String
)