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

  val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0.26").apply {
    withDatabaseName("samplestore")
    withUsername("root")
    withPassword("12345")
  }

  @ExperimentalKotest
  override var testCoroutineDispatcher = true

  override fun extensions() = listOf(SpringExtension)

  override suspend fun beforeProject() {
    mysqlContainer.portBindings.add("3366:3306")
    mysqlContainer.start()
  }
  override suspend fun afterProject() {
    // clearAllMocks()
    mysqlContainer.stop()
  }
}
