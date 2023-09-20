package com.example.border.board

import com.example.border.dto.board.BoardRequest
import com.example.border.exception.NotFoundBoardException
import com.example.border.service.board.BoardService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

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

  given("게시글 수정 (정상)") {
    val result = boardService.save(
      BoardRequest(
        title = "등록 테스트 입니다.",
        content = "내용 입니다."
      )
    )

    `when`("게시글 제목, 및 내용  변경") {
      val update = boardService.update(result.uuid , BoardRequest(
        title = "(수정) 등록 테스트 입니다.",
        content = "(수정) 내용 입니다."
      ))

      then("게시글 수정 검증 ") {
        result.uuid shouldBe update.uuid
        result.title shouldNotBe update.title
      }
    }

    `when`("게시글 수정 할 게시글이 없을때 ") {
      then("NotFoundBoardException") {
        val exception = shouldThrow<NotFoundBoardException> {
          boardService.update(UUID.randomUUID().toString() , BoardRequest(
            title = "(수정) 등록 테스트 입니다.",
            content = "(수정) 내용 입니다."
          ))
        }

        exception.message shouldBe "수정 가능한 게시물이 없습니다."
      }
    }
  }
})
