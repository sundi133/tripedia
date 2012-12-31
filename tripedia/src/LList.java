class LLNode{
	String place;
	int score;
	int index;
	LLNode next;

}

public class LList {

	LLNode head=null;
	int score;

	public void addToList(Node newnode){

		LLNode tmp = new LLNode();
		tmp.index = newnode.index;
		tmp.score = newnode.score;
		tmp.place = newnode.place;
		tmp.next = null;
	    score+=tmp.score;
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
			head = head.next;
		}
	}

	public int costathead(){
		if(head == null)
			return -1;
		return 
			head.score;
	}

	public void display(){
		LLNode tmp = head;
		int score  = 0;
		while(tmp!=null){
			score+=tmp.score;
			if(tmp.next==null){
				System.out.print(tmp.place );
				
			}else{
				System.out.print(tmp.place +"<--");
				
			}
			tmp = tmp.next;
		}
		
		System.out.print(" ------ ");
		
		System.out.println("Total Ttime " + score);
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
}
