package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		PartialTreeList answer = new PartialTreeList();
		for (int i = 0; i < graph.vertices.length; i++) {
			PartialTree myTree = new PartialTree(graph.vertices[i]);
			Vertex.Neighbor myNeighbor = graph.vertices[i].neighbors;
			
			while (myNeighbor != null) {
				PartialTree.Arc myArc = new PartialTree.Arc(graph.vertices[i], myNeighbor.vertex, myNeighbor.weight);
				myTree.getArcs().insert(myArc);
				myNeighbor = myNeighbor.next;
			}
			
			answer.append(myTree);
		}
		
		return answer;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		ArrayList<PartialTree.Arc> answer = new ArrayList<PartialTree.Arc>();
		while (ptlist.size() != 1){
			PartialTree myFirstTree = ptlist.remove();
			PartialTree.Arc mySecondArc = myFirstTree.getArcs().deleteMin();
			if (mySecondArc.v1.getRoot().parent == mySecondArc.v2.getRoot().parent) {
				boolean isTrue = true;
				
				while (isTrue) {
					if (mySecondArc.v1.getRoot().parent == mySecondArc.v2.getRoot().parent){
						mySecondArc = myFirstTree.getArcs().deleteMin();
					}
					
					else {
						isTrue = false;
					}
				}
			}
			answer.add(mySecondArc);
			PartialTree mySecondTree = ptlist.removeTreeContaining(mySecondArc.v2.getRoot().parent);
			myFirstTree.merge(mySecondTree);
			ptlist.append(myFirstTree);
		}

		return answer;
	}
}
