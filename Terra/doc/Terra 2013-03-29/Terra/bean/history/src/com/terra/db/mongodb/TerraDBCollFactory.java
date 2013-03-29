package com.terra.db.mongodb;

import com.db.mongodb.Coll.CollFactory;
import com.mongodb.DB;
import com.ComFactory;

public class TerraDBCollFactory extends CollFactory{

	@Override
	public void initDB() {
		// TODO Auto-generated method stub
		this.setDb((DB)ComFactory.getComFactory().getCom("TerraDB"));
	}

}
