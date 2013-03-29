package com.terra;

import java.net.UnknownHostException;

import com.ComConfiguration;
import com.ComFactory;
import com.db.mongodb.Conn.LocalMongoConnFactory;
import com.db.mongodb.DB.DBBuilder;
import com.mongodb.MongoException;
import com.terra.db.mongodb.TerraDBBuilder;

public class TerraComConfiguration extends ComConfiguration {

	@Override
	public void initFactory() throws UnknownHostException, MongoException {

		ComFactory fac = ComFactory.getComFactory();
		fac.insertCom("MongoConn", new LocalMongoConnFactory().getConn());// Type:Mongo
		DBBuilder builder = new TerraDBBuilder();
		builder.buildConnection();
		builder.buildDB();
		fac.insertCom("TerraDB", builder.getDb());// Type:DB

		// TODO Auto-generated method stub

	}

}
