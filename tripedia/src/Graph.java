import java.lang.reflect.Array;
import java.util.ArrayList;
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

	
	
	
	public ArrayList<Path> bfsdistance(Node s, int score){

		Stack<Node> st = new Stack<Node>();
		LList allpaths = new LList();
		allpaths.addToList(s);
		st.push(s);
		ArrayList<Path> paths = new ArrayList<Path>();
		while(!st.isEmpty()){
			Node curr = st.peek();
			Node tmp = findNext(curr);
			if(tmp==null){
				Path p = allpaths.display(s.place,s.score);
				paths.add(p);
				Node removed = st.pop();
				allpaths.remove();
			}
			else{
				if(allpaths.exists(tmp)){
					continue;
				}
				if(allpaths.getcost() + tmp.cost > score){
					continue;
				}
				allpaths.addToList(tmp);
				st.push(tmp);
			}
		}
		
		markAllUnvisted();
		return paths;
	}
 
	private void markAllUnvisted() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j]!=null)
					matrix[i][j].isVisited=false;
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

	public void addEdgeByIndex(int i, int j, Long time) {
		
		Node src =  new Node();
		src.cost  =  time.intValue();
		src.index = matrix[j][j].index;
		src.place = matrix[j][j].place;
		src.score = matrix[j][j].score;
		src.isVisited = false;
		
		matrix[i][j] = src;
		
	}

	public void printgraph() {
		// TODO Auto-generated method stub
		for (int i = 0; i < matrix.length; i++) {
			
			for (int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j]!=null){
					System.out.print(i + "," +j + "," +matrix[i][j].index + "," +matrix[i][j].cost + " ");
							
				}else{
					//System.out.print("0,0 ");
					
				}
				
			}
			System.out.println();
		}
		
	}


}
