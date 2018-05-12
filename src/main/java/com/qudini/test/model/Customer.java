package com.qudini.test.model;

import java.util.Objects;

import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qudini.test.ser.JsonJodaDateTimeSerializer;

public class Customer {

	private long id;
	private String name;
	@JsonSerialize(using = JsonJodaDateTimeSerializer.class)
	@JsonProperty( "duetime" )
	private DateTime dueTime;
	@JsonSerialize(using = JsonJodaDateTimeSerializer.class)
	@JsonProperty( "jointime" )
	private DateTime joinTime;

	public long getId() {
		return id;
	}

	public void setId( long id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public DateTime getDueTime() {
		return dueTime;
	}

	public void setDueTime( DateTime dueTime ) {
		this.dueTime = dueTime;
	}

	public DateTime getJoinTime() {
		return joinTime;
	}

	public void setJoinTime( DateTime joinTime ) {
		this.joinTime = joinTime;
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;
		Customer customer = ( Customer ) o;
		return id == customer.id &&
				Objects.equals( name, customer.name ) &&
				Objects.equals( dueTime, customer.dueTime ) &&
				Objects.equals( joinTime, customer.joinTime );
	}

	@Override
	public int hashCode() {

		return Objects.hash( id, name, dueTime, joinTime );
	}
}
