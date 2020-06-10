package algorithms;

import java.util.Set;

import dataStructures.Graph;

public class MainClass {

	public static void main(String[] args) {
		Graph <Integer, String> myGraph1 = new Graph<>(true);
		Graph <Integer, String> myGraph2 = new Graph<>(false);
		
		myGraph1.addNode(0);
		myGraph1.addNode(20);
		
		myGraph2.addNode(0);
		myGraph2.addNode(20);
		
		myGraph1.addEdge(0, 20, "Hola");
		myGraph2.addEdge(0, 20, "Hola");
		
		System.out.println(myGraph1.toString());
		System.out.println(myGraph2.toString());
		
		System.out.println(myGraph1.getLinks(0));
		
		myGraph2.removeNode(0);
		System.out.println(myGraph2.toString());
		
		Set<Integer> nodes = myGraph1.getAllNodes();
		System.out.println(nodes);
		
		for(Integer elem : nodes) {
			
		}
		
		
	}

}
