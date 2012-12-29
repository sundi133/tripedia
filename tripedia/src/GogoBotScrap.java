import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class GogoBotScrap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Document doc;
		Graph g = new Graph();
		
		try {
			doc = Jsoup.connect("http://www.aviewoncities.com/nyc/nycattractions.htm").get();
			Elements newsHeadlines = doc.select("a[class=target]");
			for (int i = 0; i < newsHeadlines.size(); i++) {
				System.out.println(newsHeadlines.get(i).html());
				Node tmp = new Node();
				tmp.index=i+1;
				tmp.place=newsHeadlines.get(i).html();
				tmp.score=i+1;
				tmp.cost=0;
				g.addNode(tmp);
				

			}
			
			

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
