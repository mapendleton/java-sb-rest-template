package com.gapinc.seri.restservice;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public HttpEntity<Greeting> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

		Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
        
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());

        return new ResponseEntity<>(greeting, HttpStatus.OK);
	}
}