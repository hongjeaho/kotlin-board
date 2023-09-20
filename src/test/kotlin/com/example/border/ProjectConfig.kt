package com.example.border

import io.kotest.common.ExperimentalKotest
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.config.LogLevel
import io.kotest.core.names.DuplicateTestNameMode
import io.kotest.core.spec.IsolationMode
import io.kotest.core.test.AssertionMode
import io.kotest.extensions.spring.SpringExtension
import org.testcontainers.containers.MySQLContainer

object ProjectConfig : AbstractProjectConfig() {

  override val parallelism = 3
  override val logLevel: LogLevel = LogLevel.Error
  override val assertionMode: AssertionMode = AssertionMode.None
  override val globalAssertSoftly = true
  override val duplicateTestNameMode = DuplicateTestNameMode.Error
  override val failOnIgnoredTests = false
  override val isolationMode = IsolationMode.SingleInstance


  @ExperimentalKotest
  override var testCoroutineDispatcher = true

  override fun extensions() = listOf(SpringExtension)

  override suspend fun beforeProject() {

  }

  override suspend fun afterProject() {
    // clearAllMocks()
  }
}
