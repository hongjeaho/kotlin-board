package com.example.border.mapper.board

import com.example.border.entity.board.Board
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BoardMapperTest : BehaviorSpec({
  given("Board entity to dto") {
    val entity = Board(title = "타이틀", content = "내용", createdBy = "관리자")
    `when`("mapper 생성") {
      val response = BoardMapper.INSTANCE.toBoardResponse(entity)
      then("mapper 검증") {
        entity.uuid shouldBe response.uuid
        entity.title shouldBe response.title
      }
    }
  }
})
