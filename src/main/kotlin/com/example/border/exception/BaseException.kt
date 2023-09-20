package com.example.border.exception

import org.springframework.http.HttpStatus

sealed  class BaseException(
  httpStatus: HttpStatus,
  message: String
) : RuntimeException(message)
