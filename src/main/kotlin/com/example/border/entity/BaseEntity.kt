package com.example.border.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.time.LocalDateTime
import java.util.*

abstract class BaseEntity() {
  @Id
  var uuid: String = UUID.randomUUID().toString()
    protected set

  @Version var version: Int = 0
    protected set

  @CreatedBy
  var createdBy: String? = null
    protected set

  @CreatedDate
  var createdTime: LocalDateTime? = null
    protected set

  @LastModifiedDate
  var updatedTime: LocalDateTime? = null
    protected set
  @LastModifiedBy
  var updatedBy: String? = null
    protected set
}
