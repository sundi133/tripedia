import java.net.UnknownHostException;
 
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

class LLNode{
	String place;
	int score;
	int index;
	int cost;
	LLNode next;

}

public class LList {

	LLNode head=null;
	int score;
	int cost;

	public void addToList(Node newnode){

		LLNode tmp = new LLNode();
		tmp.index = newnode.index;
		tmp.score = newnode.score;
		tmp.place = newnode.place;
		tmp.cost = newnode.cost;
		tmp.next = null;
	    score+=tmp.score;
	    cost+=tmp.cost;
		if(head==null){
			head = tmp;
		}else{
			tmp.next = head;
			head = tmp;
		}

	}

	public void remove(){
		if(head==null)
			return;
		else{

		    score-=head.score;
		    cost-=head.cost;
			head = head.next;
		}
	}

	public int costathead(){
		if(head == null)
			return -1;
		return 
			head.score;
	}

	public Path display(String place, int place_score){
		LLNode tmp = head;
		int score  = 0;
		String path = "";
		while(tmp!=null){
			score+=tmp.score;
			if(tmp == head){
				path="(" +tmp.cost/60 + " mins)" + tmp.place ;
				
			}else{
				if(tmp.next==null){
					path= tmp.place  + " -->" + path;
					
				}else{
					path="(" +tmp.cost/60 + " mins)" + tmp.place  + " -->" + path;
						
				}
				
			}
			tmp = tmp.next;
		}
		
	
		//System.out.print(" ----- Path Statistics----- ");
		
		//System.out.println("Total time " + cost + ", Popularity Score " + score + ", Path : " + path );
		
		Path p = new Path();
		p.cost=cost;
		p.score=score;
		p.path=path;
		return p;
		
		/*
		 * params
		 * place
		 * place_score
		 * cost
		 * score
		 * path
		 * 
		 */

		
	
	}


	public boolean exists(Node node) {
		LLNode tmp = head;
		while(tmp!=null){
			if(tmp.index == node.index)
				return true;
			tmp = tmp.next;
		}
		
		return false;
	}

	public int getscore() {
		// TODO Auto-generated method stub
		return score;
	}

	public int getcost() {
		// TODO Auto-generated method stub
		return cost;
	}

	
	
}
