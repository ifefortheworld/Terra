package com;

import java.net.UnknownHostException;

import com.db.mongodb.Conn.LocalMongoConnFactory;
import com.db.mongodb.DB.DBBuilder; 
import com.mongodb.MongoException;

public abstract class ComConfiguration {
	public abstract void initFactory() throws UnknownHostException, MongoException;
	public void resetFactory(){
		ComFactory.getComFactory().removeAllCom();
	}
}
