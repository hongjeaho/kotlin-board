package com.example.border.board

import com.example.border.common.exception.NotFoundBoardException
import com.example.border.dto.board.BoardRequest
import com.example.border.service.board.BoardService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class BoardServiceTest(
  private val boardService: BoardService,
) : BehaviorSpec({

  given("게시판 등록 테스트)") {
    `when`("게시글 생성") {
      val result = boardService.save(
        BoardRequest(
          title = "등록 테스트 입니다.",
          content = "내용 입니다."
        )
      )
      then("게시글 생성 확인") {
        val testResult = boardService.findById(result.uuid)
        result.uuid shouldBe testResult.uuid
        result.title shouldBe testResult.title
        result.content shouldBe testResult.content
      }
    }
  }

  given("게시글 수정 테스트") {
    val result = boardService.save(
      BoardRequest(
        title = "등록 테스트 입니다.",
        content = "내용 입니다."
      )
    )

    `when`("게시글 제목, 및 내용 수정") {
      val update = boardService.update(
        result.uuid,
        BoardRequest(
          title = "(수정) 등록 테스트 입니다.",
          content = "(수정) 내용 입니다."
        )
      )

      then("게시글 수정 검증 ") {
        result.uuid shouldBe update.uuid
        result.title shouldNotBe update.title
      }
    }

    `when`("게시글 수정 실폐 (게시글이 없을 때) ") {
      then("NotFoundBoardException") {
        val exception = shouldThrow<NotFoundBoardException> {
          boardService.update(
            UUID.randomUUID().toString(),
            BoardRequest(
              title = "(수정) 등록 테스트 입니다.",
              content = "(수정) 내용 입니다."
            )
          )
        }

        exception.message shouldBe "수정 가능한 게시물이 없습니다."
      }
    }
  }

  given("게시글 삭제 테스트") {
    val result = boardService.save(
      BoardRequest(
        title = "등록 테스트 입니다.",
        content = "내용 입니다."
      )
    )

    `when`("삭제 성공") {
      boardService.delete(result.uuid)
      then("삭제 검증") {
        val exception = shouldThrow<NotFoundBoardException> {
          boardService.findById(result.uuid)
        }
        exception.message shouldBe "게시물이 없습니다."
      }
    }

    `when`("삭제 실폐") {
      val exception = shouldThrow<NotFoundBoardException> {
        boardService.delete(result.uuid)
      }
      then("삭제 예외 검증") {
        exception.message shouldBe "삭제 가능한 게시물이 없습니다."
      }
    }
  }
})
