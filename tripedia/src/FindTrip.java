
public class FindTrip {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Graph g = new Graph();
		
		Node tmp1 = new Node();
		tmp1.index=1;
		tmp1.place="a";
		tmp1.score=10;
		tmp1.cost=0;
		
		Node tmp2 = new Node();
		tmp2.index=2;
		tmp2.place="b";
		tmp2.score=20;
		tmp2.cost=0;
		
		Node tmp3 = new Node();
		tmp3.index=3;
		tmp3.place="c";
		tmp3.score=15;
		tmp3.cost=0;
		
		Node tmp4 = new Node();
		tmp4.index=4;
		tmp4.place="d";
		tmp4.score=7;
		tmp4.cost=0;
		

		g.addNode(tmp1);
		g.addNode(tmp2);
		g.addNode(tmp3);
		g.addNode(tmp4);
		
		g.addEdge(tmp1, tmp2, 10);
		g.addEdge(tmp2, tmp3, 20);
		g.addEdge(tmp3, tmp4, 30);
		g.addEdge(tmp4, tmp2, 40);
		
		int score = 50;
		
		g.bfsdistance(tmp4,score);
		
	}

}
