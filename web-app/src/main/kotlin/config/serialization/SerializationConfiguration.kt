package config.serialization

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.jakarta.xmlbind.JakartaXmlBindAnnotationModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter

/**
 * Provides configuration of JSON and XML serializers and respective message converters.
 */
@Configuration
public class SerializationConfiguration
{
	/**
	 * Configures instance of [ObjectMapper] for JSON serialization.
	 *
	 * The configuration includes -
	 * - Serialization of NULL values
	 * - Formatting of output values
	 * - Required modules
	 *
	 * @return fully configured instance of [ObjectMapper].
	 */
	@Bean(name = ["objectMapper", "jsonMapper"])
	fun jsonMapper(): JsonMapper
	{
		val objectMapper = JsonMapper()

		// Configuring JSON mapper
		configureMapper(objectMapper)

		return objectMapper
	}

	/**
	 * Configures instance of [ObjectMapper] for XML serialization.
	 *
	 * The configuration includes -
	 * - Serialization of NULL values
	 * - Formatting of output values
	 * - Required modules
	 *
	 * @return fully configured instance of [ObjectMapper].
	 */
	@Bean(name = ["xmlMapper"])
	fun xmlMapper(): XmlMapper
	{
		val xmlMapper = XmlMapper()

		// Configuring XML mapper
		configureMapper(xmlMapper)

		return xmlMapper
	}

	/**
	 * Provides message converter for JSON.
	 *
	 * @return instance of message converter for JSON.
	 */
	@Bean(name = ["mappingJackson2HttpMessageConverter"])
	fun mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter
	{
		val jsonMapper = ObjectMapper()

		// Configuring JSON mapper
		configureMapper(jsonMapper)

		return MappingJackson2HttpMessageConverter(jsonMapper)
	}

	/**
	 * Provides message converter for XML.
	 *
	 * @return instance of message converter for XML.
	 */
	@Bean("mappingJackson2XmlHttpMessageConverter")
	fun mappingJackson2XmlHttpMessageConverter(): MappingJackson2XmlHttpMessageConverter
	{
		val xmlMapper = XmlMapper()

		// Configuring XML mapper
		configureMapper(xmlMapper)

		return MappingJackson2XmlHttpMessageConverter(xmlMapper)
	}

	companion object
	{
		/**
		 * Configures [ObjectMapper] with application defaults.
		 *
		 * @param objectMapper instance to be configured.
		 */
		private fun configureMapper(objectMapper: ObjectMapper)
		{
			objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) // Disabling failure generation for unknown properties
			objectMapper.disable(SerializationFeature.INDENT_OUTPUT) // Disabling output formatting
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // Disabling generating dates as millisecond values
			objectMapper.registerModule(JacksonXmlModule()) // Enables serialization to XML
			objectMapper.registerModule(JakartaXmlBindAnnotationModule()) // Enables usage of XML-binding annotations for XML and JSON serialization
			objectMapper.registerModule(KotlinModule.Builder().build()) // Enables serialization for Kotlin
			objectMapper.registerModules(Hibernate5JakartaModule()) // Enables support for serializing lazy-loaded hibernate entities
			objectMapper.registerModules(Jdk8Module()) // Enables support for JDK 8 data types, e.g. Optional
			objectMapper.registerModule(JavaTimeModule()) // Enables serialization for Java 8 timestamps
		}
	}
}
