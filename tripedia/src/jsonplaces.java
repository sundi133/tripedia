import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import org.apache.commons.*;

import org.apache.commons.lang3.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class jsonplaces {

	public static void main(String[] args) {


		String jsonTxt;
		try {
			jsonTxt = readJsonFromUrl("http://www.mapquestapi.com/directions/v1/routematrix?key=Fmjtd%7Cluuan101ng%2C7w%3Do5-9685gz&callback=renderMatrixResults");
			int beg = "renderMatrixResults".length()+1;
			int end = jsonTxt.length()-2;
			jsonTxt = jsonTxt.substring(beg,end);
			System.out.println(jsonTxt);
			JSONParser parser=new JSONParser();

			Object obj;
			obj = parser.parse(jsonTxt);
			
			JSONObject obj2 = (JSONObject)obj;

			JSONArray dist = (JSONArray) obj2.get("distance");
			
			JSONArray time = (JSONArray) obj2.get("time");
			
			
			for (int i = 0; i < time.size(); i++) {
				JSONArray time_reqd = (JSONArray)time.get(i);
				for (int j = 0; j < time_reqd.size(); j++) {
					System.out.println(time_reqd.get(j));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


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
