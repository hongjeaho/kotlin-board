package com.example.border.dto.board

import org.springframework.web.bind.annotation.RequestParam

data class BoardSearchRequest(
  @RequestParam
  val title: String,
  @RequestParam
  val content: String,
  @RequestParam
  val createdBy: String,
)
