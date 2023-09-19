package com.example.border.service

import com.example.border.config.CustomTransactional
import com.example.border.dto.board.BoardRequest
import com.example.border.dto.board.BoardResponse
import com.example.border.entity.Board
import com.example.border.repository.BoardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
 @CustomTransactional
class BoardService(
  private val boardRepository: BoardRepository
) {
  fun findById(uuid:String) = boardRepository.findByIdOrNull(uuid)

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
