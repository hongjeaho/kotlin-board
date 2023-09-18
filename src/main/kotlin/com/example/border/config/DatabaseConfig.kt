package com.example.border.config

import com.example.border.config.DatabaseConfig.Companion.CUSTOM_DOMAIN_NAMED_PARAMETER_JDBC_OPERATIONS
import com.example.border.config.DatabaseConfig.Companion.CUSTOM_DOMAIN_TRANSACTION_MANAGER
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.AliasFor
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource
import kotlin.reflect.KClass

@EnableTransactionManagement
@EnableJdbcAuditing
@EnableJdbcRepositories(
  basePackages = ["com.example.border.repository"],
  transactionManagerRef = CUSTOM_DOMAIN_TRANSACTION_MANAGER,
  jdbcOperationsRef = CUSTOM_DOMAIN_NAMED_PARAMETER_JDBC_OPERATIONS
)
@Configuration
class DatabaseConfig {
  companion object {
    const val CUSTOM_DOMAIN_TRANSACTION_MANAGER = "customDomainTransactionManager"
    const val CUSTOM_DOMAIN_NAMED_PARAMETER_JDBC_OPERATIONS =
      "customDomainNamedParameterJdbcOperations"
    const val CUSTOM_DOMAIN_DATA_SOURCE = "customDomainDataSource"
    private const val CUSTOMER_DOMAIN_JDBC_TEMPLATE = "customDomainJdbcTemplate"
  }

  @Bean
  @Qualifier(CUSTOM_DOMAIN_DATA_SOURCE)
  @ConfigurationProperties("graphql.domain.datasource")
  fun customDomainDataSource(): HikariDataSource =
    DataSourceBuilder.create().type(HikariDataSource::class.java).build()

  @Bean
  @Qualifier(CUSTOM_DOMAIN_TRANSACTION_MANAGER)
  fun customDomainTransactionManager(@Qualifier(CUSTOM_DOMAIN_DATA_SOURCE) dataSource: DataSource): PlatformTransactionManager =
    DataSourceTransactionManager(dataSource)

  @Bean
  @Qualifier(CUSTOM_DOMAIN_NAMED_PARAMETER_JDBC_OPERATIONS)
  fun customDomainNamedParameterJdbcOperations(): NamedParameterJdbcOperations =
    NamedParameterJdbcTemplate(customDomainDataSource())

  @Bean
  @Qualifier(CUSTOMER_DOMAIN_JDBC_TEMPLATE)
  fun customDomainJdbcTemplate(@Qualifier(CUSTOM_DOMAIN_DATA_SOURCE) dataSource: DataSource): JdbcTemplate =
    JdbcTemplate(dataSource)
}

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Transactional(CUSTOM_DOMAIN_TRANSACTION_MANAGER)
annotation class CustomTransactional(
  @get:AliasFor(annotation = Transactional::class) val propagation: Propagation = Propagation.REQUIRED,
  @get:AliasFor(annotation = Transactional::class) val isolation: Isolation = Isolation.DEFAULT,
  @get:AliasFor(annotation = Transactional::class) val timeout: Int = 600,
  @get:AliasFor(annotation = Transactional::class) val readOnly: Boolean = false,
  @get:AliasFor(annotation = Transactional::class) val rollbackFor: Array<KClass<out Exception>> = [],
  @get:AliasFor(annotation = Transactional::class) val rollbackForClassName: Array<String> = [],
  @get:AliasFor(annotation = Transactional::class) val noRollbackFor: Array<KClass<out Exception>> = [],
  @get:AliasFor(annotation = Transactional::class) val noRollbackForClassName: Array<String> = [],
)
