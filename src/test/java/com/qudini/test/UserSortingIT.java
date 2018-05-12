package com.qudini.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.qudini.test.model.Customer;


@RunWith( SpringRunner.class )
@AutoConfigureWebTestClient
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class UserSortingIT {

	@Autowired
	private WebTestClient webTestClient;

	private static final int NUM_OF_USERS = 100000;

	private List<Customer> testData = new ArrayList<>();

	private static final String API_ENDPOINT = "/api/userInfo/sort";

	@Before
	public void fillTestData() {
		Random longRandom = new Random( Long.SIZE - 1 );
		for ( int i = 0; i < NUM_OF_USERS; i++ ) {
			Customer customer = new Customer();
			long rand = Math.abs( longRandom.nextLong() );
			customer.setId( rand );
			customer.setName( "testN" + rand );
			customer.setDueTime( new DateTime( rand ).withZone( DateTimeZone.UTC ) );
			customer.setJoinTime( DateTime.now().withZone( DateTimeZone.UTC ) );
			testData.add( customer );
		}
	}

	@Test
	public void testSorting() {
		List<Customer> res = webTestClient.post().uri( API_ENDPOINT )
				.contentType( MediaType.APPLICATION_JSON_UTF8 )
				.accept( MediaType.APPLICATION_JSON_UTF8 )
				.body( BodyInserters.fromObject( testData ) )
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType( MediaType.APPLICATION_JSON_UTF8 )
				.expectBodyList( Customer.class )
				.hasSize( NUM_OF_USERS )
				.returnResult().getResponseBody();

		Assert.assertTrue( res.containsAll( testData ) );
		testData.sort( Comparator.comparing( Customer::getDueTime ) );
		Assert.assertEquals( res, testData );
	}
}
