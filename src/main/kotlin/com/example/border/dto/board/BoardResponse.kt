package com.example.border.dto.board

import java.time.LocalDateTime

data class BoardResponse(
  val uuid: String,
  val title: String,
  val content: String,
  val createdTime: LocalDateTime?,
  val createdBy: String?,
  val updatedTime: LocalDateTime?,
  val updatedBy: String?,
)
