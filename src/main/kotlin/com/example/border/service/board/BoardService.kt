package com.example.border.service.board

import com.example.border.common.exception.NotFoundBoardException
import com.example.border.common.extension.ifFalse
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
  fun findById(uuid: String): BoardResponse = boardRepository.findByIdOrNull(uuid)
    ?.let { BoardMapper.INSTANCE.toBoardResponse(it) }
    ?: throw NotFoundBoardException("게시물이 없습니다.")

  fun save(boardRequest: BoardRequest): BoardResponse {
    boardRepository.save(
      Board(
        title = boardRequest.title,
        content = boardRequest.content
      )
    ).let { board ->
      return BoardMapper.INSTANCE.toBoardResponse(board)
    }
  }

  fun update(uuid: String, boardRequest: BoardRequest): BoardResponse {
    val board = boardRepository.findByIdOrNull(uuid) ?: throw NotFoundBoardException("수정 가능한 게시물이 없습니다.")
    board.update(boardRequest)

    boardRepository.save(board).let {
      return BoardMapper.INSTANCE.toBoardResponse(it)
    }
  }

  fun delete(uuid: String) {
    boardRepository.existsByUuid(uuid)
      .ifFalse { throw NotFoundBoardException("삭제 가능한 게시물이 없습니다.") }

    boardRepository.deleteById(uuid)
  }
}
