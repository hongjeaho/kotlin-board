package com.example.border.service

import com.example.border.dto.board.BoardRequest
import com.example.border.dto.board.BoardResponse
import com.example.border.entity.Board
import com.example.border.repository.BoardRepository
import org.springframework.stereotype.Service

@Service
class BoardService(
  private val boardRepository: BoardRepository
) {
  fun save(boardRequest: BoardRequest) : BoardResponse {
    boardRepository.save(
      Board(
        title = boardRequest.title,
        content = boardRequest.content,
        createdBy = "관리자"
      )
    ).let {board ->
      return BoardResponse(
        uuid = board.uuid,
        title = board.title,
        content = board.content,
        createdBy = board.createdBy,
        createdTime = board.createdTime,
        updatedBy = board.updatedBy,
        updatedTime = board.updatedTime
      )
    }
  }
}
