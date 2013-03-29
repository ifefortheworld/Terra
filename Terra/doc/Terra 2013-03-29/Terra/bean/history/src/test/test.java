package test;
import java.net.UnknownHostException;


import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.terra.TerraComConfiguration;
import com.terra.db.mongodb.TerraDBCollFactory;
public class test {

	public static void main(String[] args) throws UnknownHostException, MongoException {
		new TerraComConfiguration().initFactory();
		DBCollection coll = (new TerraDBCollFactory()).getColl("file-coll");
		
//		CollFactory coll = new CollFactory
//		if(conn != null){
//			System.out.println("Success!");
//
//			System.out.println(conn);
//		}
//		else{
//			System.out.println("Failed");
//		}
	}
		
}
