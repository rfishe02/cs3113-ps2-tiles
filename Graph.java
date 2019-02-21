import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Graph {
	HashMap<Integer,Node> vert;
	HashMap<String,Integer> weig;
	
	public Graph() {
		vert = new HashMap<>();
		weig = new HashMap<>();
	}
	
	public Node getNode(int num) {
		return vert.get(num);
	}
	
	public Node addNode(int num) {
		Node result = null;
		
		if(vert.containsKey(num)) {
			result = vert.get(num);
		} else {
			result = new Node(num);
			vert.put(num, result);
		}
		
		return result;
	}
	
	public void addLink(int st, int end, int dist) {
		Node a = addNode(st);
		Node b = addNode(end);
		
		weig.put(st+","+end, dist);
		a.adj.add(b);
	}
	
	public int getWeight(Node a, Node b) {
		String key = a.num+","+b.num;
		int res = -1;
		
		if(weig.containsKey(key)) {
			res = weig.get(key);
		}
		
		return res;
	}
	
	public boolean buildGraph(String filename) {
		boolean result = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String[] p;
			String[] c;
			String[] w;
			String read;
			int st;
			int dest;
			int dist;
			
			while((read = br.readLine())!=null) {
				p = read.split(" ");
				st = Integer.parseInt(p[0]);
				
				c = p[1].split(",");
				for(String grp : c) {
					w = grp.split("-");
					dest = Integer.parseInt(w[0]);
					dist = Integer.parseInt(w[1]);
					
					addLink(st,dest,dist);
				}
			}
			result = true;
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public void printAdjList() {
		for(Node p : vert.values()) {
			System.out.print(p.num+" => ");
			
			for(Node c : p.adj) {
				System.out.print(c.num+" +"+getWeight(p,c)+" => ");
			}
			System.out.print("null");
			System.out.println();
		}
	}
}
