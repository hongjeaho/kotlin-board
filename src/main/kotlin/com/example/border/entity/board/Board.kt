package com.example.border.entity.board

import com.example.border.dto.board.BoardRequest
import com.example.border.entity.BaseEntity
import org.springframework.data.relational.core.mapping.Table

@Table("board")
class Board(
  title: String,
  content: String,
) : BaseEntity() {
  var title: String = title
    private set
  var content: String = content
    private set
  fun update(boardRequest: BoardRequest) {
    title = boardRequest.title
    content = boardRequest.content
  }
}
