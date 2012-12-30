import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;



public class jsonplaces {

	public static void main(String[] args) {


		String jsonTxt;
		try {
			jsonTxt = readJsonFromUrl("http://www.mapquestapi.com/directions/v1/routematrix?key=Fmjtd%7Cluuan101ng%2C7w%3Do5-9685gz&callback=renderMatrixResults&json={locations:[\"40.689269,-74.044737\",\"40.758577,-73.984464\",\"40.706096,-73.996823\",\"40.775309,-73.974767\",\"40.748284,-73.985569\",\"40.7517,-73.975356\",\"40.81687,-73.934584\",\"40.758723,-73.978118\",\"40.752998,-73.977056\",\"40.714353,-74.005973\",\"40.707909,-74.008514\",\"40.711667,-74.013611\",\"40.639379,-74.037732\"],options: {allToAll: true}}");
			JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);        
			System.out.println("size: "+ json.size());
		} catch (IOException e) {
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
		/*InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      return jsonText;
		    } finally {
		      is.close();
		    }*/

		String response="";
		try{
			URL yahoo = new URL(url);
			URLConnection yc = yahoo.openConnection();
			yc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							yc.getInputStream()));
			String inputLine;


			while ((inputLine = in.readLine()) != null){
				response+=inputLine;
			}
			System.out.print(response);
			in.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return response;

	}

}
