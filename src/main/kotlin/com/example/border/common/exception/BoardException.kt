package com.example.border.common.exception

import org.springframework.http.HttpStatus

class NotFoundBoardException(
  message: String,
) : BaseException(HttpStatus.NOT_FOUND, message)

class DeletedBoardException(
  message: String,
) : BaseException(HttpStatus.NOT_FOUND, message)

class UpdatedBoardException(
  message: String,
) : BaseException(HttpStatus.NOT_FOUND, message)
