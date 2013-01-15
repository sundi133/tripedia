import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class FindTrip {

	/**
	 * @param args
	 * 
	 * int score = 50;

	 */

	static int score = 10000;
	static ArrayList<Node> listOfNodes = new ArrayList<Node>();
	private static Mongo mongo;
	private static DB db;
	public static void main(String[] args) {

		initDb();
		Graph g = new Graph();
		int max=20;
		Node tmp1 = new Node();
		tmp1.index=1;
		tmp1.place="Statue of Liberty";
		tmp1.score=max-1;
		tmp1.cost=0;

		Node tmp2 = new Node();
		tmp2.index=2;
		tmp2.place="Times Square";
		tmp2.score=max-2;
		tmp2.cost=0;

		Node tmp3 = new Node();
		tmp3.index=3;
		tmp3.place="Brooklyn Bridge";
		tmp3.score=max-3;
		tmp3.cost=0;

		Node tmp4 = new Node();
		tmp4.index=4;
		tmp4.place="Central Park";
		tmp4.score=max-4;
		tmp4.cost=0;


		Node tmp5 = new Node();
		tmp5.index=5;
		tmp5.place="Empire State Building";
		tmp5.score=max-5;
		tmp5.cost=0;

		Node tmp6 = new Node();
		tmp6.index=6;
		tmp6.place="Chrysler Building";
		tmp6.score=max-6;
		tmp6.cost=0;

		Node tmp7 = new Node();
		tmp7.index=7;
		tmp7.place="Fifth Avenue";
		tmp7.score=max-7;
		tmp7.cost=0;

		Node tmp8 = new Node();
		tmp8.index=8;
		tmp8.place="Rockefeller Center";
		tmp8.score=max-8;
		tmp8.cost=0;

		Node tmp9 = new Node();
		tmp9.index=9;
		tmp9.place="Grand Central Terminal";
		tmp9.score=max-9;
		tmp9.cost=0;

		Node tmp10 = new Node();
		tmp10.index=10;
		tmp10.place="Macy's Department Store";
		tmp10.score=max-10;
		tmp10.cost=0;

		Node tmp11 = new Node();
		tmp11.index=11;
		tmp11.place="Wall Street";
		tmp11.score=max-11;
		tmp1.cost=0;

		Node tmp12 = new Node();
		tmp12.index=12;
		tmp12.place="World Trade Center";
		tmp12.score=max-12;
		tmp12.cost=0;

		Node tmp13 = new Node();
		tmp13.index=13;
		tmp13.place="9/11 Memorial";
		tmp13.score=max-13;
		tmp13.cost=0;


		g.addNode(tmp1);
		g.addNode(tmp2);
		g.addNode(tmp3);
		g.addNode(tmp4);
		g.addNode(tmp5);
		g.addNode(tmp6);
		g.addNode(tmp7);
		g.addNode(tmp8);
		g.addNode(tmp9);
		g.addNode(tmp10);
		g.addNode(tmp11);
		g.addNode(tmp12);
		g.addNode(tmp13);

		listOfNodes.add(tmp1);
		listOfNodes.add(tmp2);
		listOfNodes.add(tmp3);
		listOfNodes.add(tmp4);
		listOfNodes.add(tmp5);
		listOfNodes.add(tmp6);
		listOfNodes.add(tmp7);
		listOfNodes.add(tmp8);
		listOfNodes.add(tmp9);
		listOfNodes.add(tmp10);
		listOfNodes.add(tmp11);
		listOfNodes.add(tmp12);
		listOfNodes.add(tmp13);



		//g.addEdgeByIndex(i,j,)
		String jsonTxt;
		try {
			jsonTxt = readJsonFromUrl("http://www.mapquestapi.com/directions/v1/routematrix?key=Fmjtd%7Cluuan101ng%2C7w%3Do5-9685gz&callback=renderMatrixResults");
			int beg = "renderMatrixResults".length()+1;
			int end = jsonTxt.length()-2;
			jsonTxt = jsonTxt.substring(beg,end);
			//System.out.println(jsonTxt);
			JSONParser parser=new JSONParser();

			Object obj;
			obj = parser.parse(jsonTxt);

			JSONObject obj2 = (JSONObject)obj;

			JSONArray dist = (JSONArray) obj2.get("distance");

			JSONArray time = (JSONArray) obj2.get("time");

			for (int i = 0; i < time.size(); i++) {
				JSONArray time_reqd = (JSONArray)time.get(i);
				for (int j = 0; j < time_reqd.size(); j++) {
					g.addEdgeByIndex(i+1,j+1,  (Long) time_reqd.get(j));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*for (int k = 0; k < 13; k++) {
			for (int k2 = 0; k2 < 13; k2++) {
				g.addEdgeByIndex(k+1,k2+1, (long) k*k2);

			}
		}*/


		for (int i = 0; i < listOfNodes.size(); i++) {

			//System.out.println(listOfNodes.get(i).place);
			ArrayList<Path> paths = g.bfsdistance(listOfNodes.get(i),score);

			insertToDb(listOfNodes.get(i).place, listOfNodes.get(i).score,paths);
		}

	}


	private static void initDb() {
		// connect to mongoDB, ip and port number
		try {
			mongo = new Mongo("localhost", 27017);
			// get database from MongoDB,
			// if database doesn't exists, mongoDB will create it automatically
			db = mongo.getDB("tripedia");
			dropcoll(db);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private static void insertToDb(String place, int place_score,
			ArrayList<Path> paths) {

		try {
			DBCollection coll = db.getCollection("tripcollection");
			BasicDBObject doc = new BasicDBObject();
			doc.put("name", place);
			doc.put("popularity", place_score);

			for (int i=0; i < paths.size(); i++) {
				BasicDBObject indoc = new BasicDBObject();
				indoc.put("cost", paths.get(i).getCost());
				indoc.put("score",paths.get(i).getScore());
				indoc.put("path",paths.get(i).getPath());
				doc.put(Integer.toString(i),indoc);
			}
			coll.insert(doc);

			System.out.println(coll.getCount() );	        
			DBCursor cursor = coll.find();
			try {
				while(cursor.hasNext()) {
					System.out.println(cursor.next());
				}
			} finally {
				cursor.close();
			}
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}

	private static void dropcoll(DB db) {

		DBCollection coll = db.getCollection("tripcollection");

		coll.drop();
	}


	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static String readJsonFromUrl(String url) throws IOException  {

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		String line = "";
		String jsontext="";
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

			//nameValuePairs.add(new BasicNameValuePair("key",
			//    "Fmjtd%7Cluuan101ng%2C7w%3Do5-9685gz"));
			//nameValuePairs.add(new BasicNameValuePair("callback",
			//        "renderMatrixResults"));
			nameValuePairs.add(new BasicNameValuePair("json",
					"{locations:[\"40.689269,-74.044737\",\"40.758577,-73.984464\",\"40.706096,-73.996823\",\"40.775309,-73.974767\",\"40.748284,-73.985569\",\"40.7517,-73.975356\",\"40.81687,-73.934584\",\"40.758723,-73.978118\",\"40.752998,-73.977056\",\"40.714353,-74.005973\",\"40.707909,-74.008514\",\"40.711667,-74.013611\",\"40.639379,-74.037732\"],options: {allToAll: true}}"));

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			while ((line = rd.readLine()) != null) {
				jsontext+=line;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsontext;
	}

}
