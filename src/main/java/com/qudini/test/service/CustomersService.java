package com.qudini.test.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qudini.test.model.Customer;

@Service
public class CustomersService {

	public List<Customer> sortByDueDate( List<Customer> users){
		users.sort( Comparator.comparing( Customer::getDueTime ) );
		return users;
	}
}
