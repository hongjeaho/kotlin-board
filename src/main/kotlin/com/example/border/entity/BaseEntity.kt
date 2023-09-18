package com.example.border.entity

import org.springframework.data.annotation.Version
import java.time.LocalDateTime

abstract class BaseEntity(
  val createdBy:String
) {
  @Version var version:Int = 0
    protected set

  val createdTime: LocalDateTime = LocalDateTime.now()

  var updatedTime: LocalDateTime? = null
    protected set
  var updatedBy:String? = null
    protected set
}
