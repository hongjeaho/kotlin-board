package com.example.border.repository

import com.example.border.entity.Board
import org.springframework.data.repository.CrudRepository

interface BoardRepository : CrudRepository<Board, String> {
}
