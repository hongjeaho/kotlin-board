package com.example.border.entity.board

import com.example.border.entity.BaseEntity
import org.springframework.data.relational.core.mapping.Table

@Table("board")
class Board(
  val title: String,
  val content: String,
  createdBy: String,
) : BaseEntity(
  createdBy
)
