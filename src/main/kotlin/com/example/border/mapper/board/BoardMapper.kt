package com.example.border.mapper.board

import com.example.border.dto.board.BoardResponse
import com.example.border.entity.board.Board
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface BoardMapper {

  fun toBoardResponse(board: Board): BoardResponse

  companion object {
    val INSTANCE: BoardMapper = Mappers.getMapper(BoardMapper::class.java)
  }
}
