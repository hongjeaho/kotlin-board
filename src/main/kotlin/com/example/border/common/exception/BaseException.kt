package com.example.border.common.exception

import org.springframework.http.HttpStatus

sealed class BaseException(
  httpStatus: HttpStatus,
  message: String,
) : RuntimeException(message)
