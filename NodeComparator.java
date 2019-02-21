import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

	public int compare(Node o1, Node o2) {
		if(o1.pathCost < o2.pathCost) {
			return -1;
		} else if(o1.pathCost > o2.pathCost) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
