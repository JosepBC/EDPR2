package algorithms;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Set;

import dataStructures.Graph;

public class MainClass {

	/*
	 * Llegir una xarxa format Pajek [4,5], que és un tipus d’arxiu de text que
	 * permet emmagatzemar l’estructura de la xarxa, així com els atributs als nodes
	 * i a les arestes. La xarxa es suposa no dirigida.
	 */
	private static Graph<Integer, String> generaGraph(String path) {
		Graph<Integer, String> graph = new Graph<>(true);

		try {
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			String lane = myReader.nextLine();
			String[] words;
			
			if (lane.split(" ")[0].equals("*Vertices")) {

				while(myReader.hasNextLine()) {
					lane = myReader.nextLine();
					if(lane.equals("*Edges")) {
						break;
					}
					words = lane.split(" ");
					//vértice
				}
				while(myReader.hasNextLine()) { 
					lane = myReader.nextLine();
					words = lane.split(" ");
					System.out.println(words[0]);
					//añadir arista 
				}
			}
			else { 
				myReader.close(); 
				throw new IllegalArgumentException();
			}
			myReader.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return graph;
	}

	/*
	 * Detecció de components connexes utilitzant un algorisme d’exploració com els
	 * explicats a classe.
	 */
	private static void getGraphInfo(Graph<Integer, String> graph, int[] info) {
		int numNodesVisit = 0, NCC = 0, GCC = 0, SLCC = 0;
		// lista de nodos del grafo
		while(numNodesVisit < graph.getnElem()) {
			
		}
		/*
		 * sacar lista de nodos del grafo
		 * mirar todas las componentes conexas del primer nodo
		 * mirar todas las componentes conexas del siguiente nodo sin visitar
		 * componentes conexas ++ / comparar con la pirmera y segunda componente mas grande.
		 * repetir hasta que no queden nodos sin visitar
		 */
	}

	/*
	 * Anàlisi de percolació. Visualització de l’evolució del nombre de components
	 * connexes de la xarxa, i de la mida de les dues components connexes més grans,
	 * a mesura que anem extirpant nodes de la xarxa.
	 */
	private static void percoloracio(Graph<Integer, String> graph, int mode) {

		switch (mode) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		}

	}

	public static void main(String[] args) {
		Graph<Integer, String> myGraph1 = new Graph<>(true);
		Graph<Integer, String> myGraph2 = new Graph<>(false);

		generaGraph("networks/airports_UW.net");

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

		for (Integer elem : nodes) {

		}

	}

}
