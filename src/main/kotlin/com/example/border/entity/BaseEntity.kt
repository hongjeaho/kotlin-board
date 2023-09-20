package com.example.border.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import java.time.LocalDateTime
import java.util.*

abstract class BaseEntity(
  val createdBy: String,
) {
  @Id
  var uuid: String = UUID.randomUUID().toString()
    protected set

  @Version var version: Int = 0
    protected set

  var createdTime: LocalDateTime = LocalDateTime.now()
    protected set

  var updatedTime: LocalDateTime? = null
    protected set
  var updatedBy: String? = null
    protected set
}
