package config.persistence

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["dev.workingtheory.common.model", "dev.workingtheory.reference.model"])
public class HibernateConfiguration
{

}