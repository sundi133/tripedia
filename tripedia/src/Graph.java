import java.util.Stack;


public class Graph {

	Node matrix[][] = new Node[100][100];

	public void addNode(Node a){

		matrix[a.index][a.index] = a;
		a.cost=0;
		//		for (int i = 0; i < matrix[a.index].length; i++) {
		//			matrix[a.index][i] = -1;
		//		}

	}

	public void addEdge(Node s, Node e, int cost){

		matrix[s.index][e.index] = e;
		matrix[e.index][s.index] = s;
		
		e.cost = cost;
		s.cost = cost;
	}

	public void bfsdistance(Node s, int score){

		Stack<Node> st = new Stack<Node>();

		LList allpaths = new LList();
		allpaths.addToList(s);
		st.push(s);
		while(!st.isEmpty()){
			Node curr = st.peek();
			//System.out.println("curr index---"+curr.index);
			
			Node tmp = findNext(curr);
			if(tmp==null){
				//print path
				//System.out.println("display");
				allpaths.display();
				st.pop();
				allpaths.remove();
				
			}
			else{
				//System.out.println(tmp.index +"t"+ (tmp.isVisited));
				if(allpaths.exists(tmp)){
					//System.out.println("Path exists");
					
					continue;
				}
				if(allpaths.getscore() + tmp.score > score){
					continue;
				}
				allpaths.addToList(tmp);
				st.push(tmp);
			}
		}
	}

	private Node findNext(Node curr) {
		for (int i = 1; i < matrix[curr.index].length; i++) {
			//System.out.print(i +" ");
			if(matrix[curr.index][i]!=null){
				if(i==curr.index){
                     continue; 
				}
				
				if(matrix[curr.index][i]!=null && matrix[curr.index][i].cost > 0 && matrix[curr.index][i].isVisited == false){
					//System.out.println("found i" + i);
					matrix[curr.index][i].isVisited = true;
					return matrix[curr.index][i];
				}

			}

		}
		//System.err.println("null");
		return null;
	}


}
