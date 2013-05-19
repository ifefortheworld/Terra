package com.db.mongodb.DB;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public abstract class DBBuilder {
	private Mongo conn;
	private DB db;
	
	protected Mongo getConn() {
		return conn;
	}

	protected void setConn(Mongo conn) {
		this.conn = conn;
	}

	public DB getDb() {
		return db;
	}

	protected void setDb(DB db) {
		this.db = db;
	}


	protected DBBuilder() {

	}
	
	public abstract void buildConnection() throws UnknownHostException, MongoException;
	public abstract void buildDB();
}
