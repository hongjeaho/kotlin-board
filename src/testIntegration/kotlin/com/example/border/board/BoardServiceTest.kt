package com.example.border.board

import com.example.border.dto.board.BoardRequest
import com.example.border.service.board.BoardService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BoardServiceTest(
  private val boardService: BoardService,
) : BehaviorSpec({

  given("게시판 등록 (정상)") {
    `when`("게시글 생성") {
      val result = boardService.save(
        BoardRequest(
          title = "등록 테스트 입니다.",
          content = "내용 입니다."
        )
      )
      then("게시글 생성 확인") {
        val testResult = boardService.findById(result.uuid)
        result.uuid shouldBe testResult?.uuid
        result.title shouldBe testResult?.title
        result.content shouldBe testResult?.content
      }
    }
  }
})
