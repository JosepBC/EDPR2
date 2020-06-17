package dataStructures;
import java.util.LinkedList;
import java.util.Set;

/**
 * Main de prova per les estructures de dades
 * @author Josep Bello
 * @author Lautaro Russo
 *
 */
public class MainClass {

	public static void main(String[] args) {
		/*Graph <Integer, String> myGraph = new Graph<>();
		
		myGraph.addNode(10);
		myGraph.addNode(20);
		
		myGraph.addEdge(10, 20, "Hola");
		myGraph.addEdgeIDX(1, 2, "hola3");
		myGraph.addEdge(0, 20, "Hola2");
		
		System.out.println("Pre-remove: ");
		System.out.println(myGraph+"\n");
		Graph <Integer, String> copy = myGraph.clone();
		copy.removeNodeIDX(1);
		System.out.println("Copy:\n"+copy+"\n");
		System.out.println("Original:\n"+myGraph+"\n");
		
		System.out.println("Get links of node with content 10:");
		LinkedList<EdgeT<Integer, String>> links = myGraph.getLinks(10);
		for(EdgeT<Integer, String> edge : links) {
			System.out.println(edge);
		}
		System.out.println(links+"\n");
		
		System.out.println("Get links of node with content 1000:");
		LinkedList<EdgeT<Integer, String>> links2 = myGraph.getLinks(1000);
		System.out.println(links2+"\n");
		
		myGraph.removeNode(10);
		System.out.println("Post remove node with content 10: ");
		System.out.println(myGraph+"\n");
		
		System.out.println("All nodes: ");
		Set<Integer> edges = myGraph.getAllNodes();
		System.out.println(edges+"\n");	
		
		System.out.println("Remove rand result: " + myGraph.removeRand());
		System.out.println("New nodes of the graph: ");
		System.out.println(myGraph.getAllNodes());

*/
/*
		NodeT<String> oneNode = new NodeT<String>("Hola", (float)10.0);
		NodeT<String> anotherNode = new NodeT<String>("Hola2", (float)12.0);*/
		
		MaxHeap<Integer> myHeap = new MaxHeap<>();
		myHeap.insert(20);
		myHeap.insert(15);
		myHeap.insert(2);
		myHeap.insert(14);
		myHeap.insert(10);
		myHeap.print();
		myHeap.coolPrint();
		

		System.out.println("Root: "+myHeap.extractRoot());
		myHeap.print();
		myHeap.coolPrint();

		//myHeap.coolPrint();
		/*
		Integer root = myHeap.extractRoot();
		System.out.println("Root: "+root);
		myHeap.print();
		myHeap.coolPrint();*/
	}

}
