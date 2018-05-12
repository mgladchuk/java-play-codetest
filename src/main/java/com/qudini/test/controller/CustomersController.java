package com.qudini.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qudini.test.model.Customer;
import com.qudini.test.service.CustomersService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping( "/api/customer" )
public class CustomersController {

	@Autowired
	private CustomersService userInfoService;

	@PostMapping( "/sort" )
	public Flux<Customer> sortByDueDate( @RequestBody List<Customer> users ) {
		return Flux.fromIterable( userInfoService.sortByDueDate( users ) );
	}

}
