package dataStructures;

public class MainClass {

	public static void main(String[] args) {
		Graph <Integer, String> myGraph = new Graph<>();
		
		myGraph.addNode(10);
		myGraph.addNode(20);
		
		myGraph.addEdge(10, 20, "Hola");
		myGraph.addEdgeIDX(1, 2, "hola3");
		myGraph.addEdge(0, 20, "Hola2");
		
		System.out.println("Pre-remove: ");
		System.out.println(myGraph+"\n");
		
		System.out.println("Get links of node with content 0:");
		System.out.println(myGraph.getLinks(0)+"\n");
		
		myGraph.removeNode(10);
		System.out.println("Post remove node with content 10: ");
		System.out.println(myGraph+"\n");
		
		System.out.println("All nodes: ");
		System.out.println(myGraph.getAllNodes());	
	}

}
