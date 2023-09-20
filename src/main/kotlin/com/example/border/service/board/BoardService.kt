package com.example.border.service.board

import com.example.border.config.CustomTransactional
import com.example.border.dto.board.BoardRequest
import com.example.border.dto.board.BoardResponse
import com.example.border.entity.board.Board
import com.example.border.mapper.board.BoardMapper
import com.example.border.repository.board.BoardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@CustomTransactional
class BoardService(
  private val boardRepository: BoardRepository,
) {
  @CustomTransactional(readOnly = true)
  fun findById(uuid: String) = boardRepository.findByIdOrNull(uuid)

  fun save(boardRequest: BoardRequest): BoardResponse {
    boardRepository.save(
      Board(
        title = boardRequest.title,
        content = boardRequest.content,
        createdBy = "관리자"
      )
    ).let { board ->
      return BoardMapper.INSTANCE.toBoardResponse(board)
    }
  }
}
