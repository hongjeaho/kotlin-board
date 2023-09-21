package com.example.border.common.exception

import org.springframework.http.HttpStatus

class NotFoundBoardException(
  message: String,
) : BaseException(HttpStatus.NOT_FOUND, message)
