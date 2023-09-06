package dev.workingtheory.reference.web.rest.controller

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlRootElement
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/hello")
public class HelloWorldController
{
	private val logger = LogManager.getLogger(HelloWorldController::class.java)

	@RequestMapping("/{name}")
	fun sayHello(@PathVariable("name") name: String): ResponseEntity<Greeting>
	{
		return ResponseEntity.ok(Greeting("Hello, $name"))
	}
}

@XmlRootElement(name = "greeting")
@XmlAccessorType(XmlAccessType.FIELD)
public data class Greeting(val message: String)
{

}