import java.net.UnknownHostException;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class FilesCollCRUD {
	private Mongo conn;
	private DB db;
	private DBCollection coll;
	public FilesCollCRUD() throws UnknownHostException,MongoException{
		this.conn = new Mongo("localhost");
		this.db = conn.getDB("DBTerra");
		this.coll = db.getCollection("Coll-Files");
	}
	public void insertDoc(DBObject obj){
		this.coll.insert(obj);
	}
	public void insertDoc(FilesCollDocument doc){
		DBObject obj = new BasicDBObject();
		obj.put("file-title", doc.getFileTitle());
//		obj.put("file-type",doc)
//		obj.put(arg0, arg1)
		
	}
}
