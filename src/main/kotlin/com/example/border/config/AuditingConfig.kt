package com.example.border.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing
import java.util.*

@EnableJdbcAuditing(auditorAwareRef = "auditorProvider")
@Configuration
class AuditingConfig {
  @Bean
  fun auditorProvider(): AuditorAware<String> = AuditorAware {
//    (SecurityContextHolder.getContext().authentication
//      .principal as? ApiClient)
//      ?.getUserSeq()
    Optional.of("관리자")
  }
}
