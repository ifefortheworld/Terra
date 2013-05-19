package com.db.mongodb.Conn;

import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public abstract class MongoConnFactory {
	private Mongo conn;

	public Mongo getConn() {
		return conn;
	}

	protected void setConn(Mongo conn) {
		this.conn = conn;
	}
	
	protected MongoConnFactory() throws UnknownHostException,MongoException{
		this.init();
	}
	
	protected abstract void init() throws UnknownHostException,MongoException;
}
