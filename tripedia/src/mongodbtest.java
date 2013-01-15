import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class mongodbtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		try {
			// connect to mongoDB, ip and port number
			Mongo mongo = new Mongo("localhost", 27017);
 
			// get database from MongoDB,
			// if database doesn't exists, mongoDB will create it automatically
			DB db = mongo.getDB("tripedia");
 
			DBCollection coll = db.getCollection("tripcollection");

			BasicDBObject doc = new BasicDBObject();
	        doc.put("name", "place1");
	        doc.put("rank", "10");
	        doc.put("popularity", "30");
	        
	        //BasicDBObject paths = new BasicDBObject();
	        //paths.put("x", 203);
	        //paths.put("y", 102);
	        //doc.put("info", paths);
	        for (int i=0; i < 10; i++) {
	        	BasicDBObject indoc = new BasicDBObject();
		        indoc.put("test"+i, i);
		        indoc.put("res"+i,i);
	            doc.put(Integer.toString(i),indoc);
	        }
	        coll.insert(doc);
	        
	        System.out.println(coll.getCount() +  doc.getString("0"));	        
	        DBCursor cursor = coll.find();
	        try {
	            while(cursor.hasNext()) {
	                System.out.println(cursor.next());
	            }
	        } finally {
	            cursor.close();
	        }
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

}
