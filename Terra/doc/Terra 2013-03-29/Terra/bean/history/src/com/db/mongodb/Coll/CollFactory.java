package com.db.mongodb.Coll;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public abstract class CollFactory {
	private DB db;

	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}

	public DBCollection getColl(String key) {
		return this.db.getCollection(key);
	}

	public CollFactory() {
		this.initDB();
	}

	public abstract void initDB();

}
