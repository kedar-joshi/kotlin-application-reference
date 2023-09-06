package config.persistence

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * Provides beans and configuration required for database persistence.
 */
@EnableTransactionManagement
@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = ["dev.workingtheory.common.model", "dev.workingtheory.reference.model"])
public class HibernateConfiguration
{

}