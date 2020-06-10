package algorithms;

import dataStructures.Graph;

public class MainClass {

	public static void main(String[] args) {
		Graph <Integer, String> myGraph1 = new Graph<>();
		Graph <Integer, String> myGraph2 = new Graph<>();
		
		myGraph1.addNode(0);
		myGraph1.addNode(20);
		
		myGraph2.addNode(0);
		myGraph2.addNode(20);
		
		myGraph1.addEdge(0, 20, "Hola", true);
		myGraph2.addEdge(0, 20, "Hola", false);
		
		System.out.println(myGraph1.toString());
		System.out.println(myGraph2.toString());
		
		System.out.println(myGraph1.getLinks(0));
		
		myGraph2.removeNode(0);
		System.out.println(myGraph2.toString());
	}

}
