package com.terra.db.mongodb;
import java.net.UnknownHostException;

import com.ComFactory;
import com.db.mongodb.DB.DBBuilder;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
public class TerraDBBuilder extends DBBuilder{

	@Override
	public void buildConnection() throws UnknownHostException, MongoException {
		this.setConn((Mongo) ComFactory.getComFactory().getCom("MongoConn"));
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildDB() {
		// TODO Auto-generated method stub
		this.setDb(this.getConn().getDB("TerraDB"));
	}
	
}
