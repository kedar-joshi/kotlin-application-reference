package config.async

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync

/**
 * Provides beans and configuration required for application thread pool.
 */
@EnableAsync
@Configuration
public class ExecutorServiceConfiguration
{

}