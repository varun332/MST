package apps;
import java.util.ArrayList;
import structures.Graph; 

public class Driver {
	public static void main(String[] args) throws Exception{
		
		String[] testGraph = {"graph2.txt"};
		for(int i=0; i<testGraph.length;i++){
			Graph A = new Graph(testGraph[i]);
			A.print();
			MST X = new MST(); 
			PartialTreeList one = null; 
			ArrayList<PartialTree.Arc> FinalX = null; 
			
			try{
				one = X.initialize(A);
			}catch (Exception e){
				System.out.println("ERROR: X initialize");
			}
			try {
				FinalX = X.execute(one);
				System.out.println(FinalX);
			}catch (Exception e){
				System.out.println("ERROR: X executes");
			}
			System.out.println();
		}
	}
}