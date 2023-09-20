package com.example.border.repository.board

import com.example.border.entity.board.Board
import org.springframework.data.repository.CrudRepository

interface BoardRepository : CrudRepository<Board, String>
