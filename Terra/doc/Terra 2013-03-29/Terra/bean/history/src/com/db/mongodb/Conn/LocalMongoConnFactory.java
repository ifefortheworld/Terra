package com.db.mongodb.Conn;

import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class LocalMongoConnFactory extends MongoConnFactory {

	public LocalMongoConnFactory() throws UnknownHostException,MongoException {
		super();
	}

	protected void init() throws UnknownHostException,MongoException {
		this.setConn(new Mongo("localhost"));
	

	}
	
}
