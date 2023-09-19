package com.example.border.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("board")
class Board(
  val title:String,
  val content:String,
  createdBy:String
) : BaseEntity(
  createdBy
)
