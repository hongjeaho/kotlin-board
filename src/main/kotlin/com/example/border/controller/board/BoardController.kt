package com.example.border.controller.board

import com.example.border.dto.board.BoardRequest
import com.example.border.dto.board.BoardResponse
import com.example.border.dto.board.BoardSearchRequest
import com.example.border.service.board.BoardService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.awt.print.Pageable
import java.time.LocalDateTime
import java.util.UUID

@RestController
@RequestMapping("/boards")
class BoardController(
  private val boardService: BoardService,
) {

  @GetMapping
  fun list(
    pageable: Pageable,
    boardSearchRequest: BoardSearchRequest,
  ): Page<BoardResponse> {
    return Page.empty()
  }

  @GetMapping("/{uuid}")
  fun detail(@PathVariable("uuid") uuid: String) = boardService.findById(uuid)

  @PostMapping
  fun save(@RequestBody boardRequest: BoardRequest): BoardResponse {
    return boardService.save(boardRequest)
  }

  @PutMapping("/{uuid}")
  fun update(
    @PathVariable("uuid") uuid: String,
    @RequestBody boardRequest: BoardRequest,
  ) = boardService.update(uuid, boardRequest)

  @DeleteMapping("/{uuid}")
  fun delete(@PathVariable("uuid") uuid: String) = uuid
}
