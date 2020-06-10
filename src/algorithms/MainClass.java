package algorithms;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Iterator;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import dataStructures.Graph;

public class MainClass {

	/*
	 * Llegir una xarxa format Pajek [4,5], que és un tipus d’arxiu de text que
	 * permet emmagatzemar l’estructura de la xarxa, així com els atributs als nodes
	 * i a les arestes. La xarxa es suposa no dirigida.
	 */
	private static Graph<String, String> generaGraph(String path) {
		Graph<String, String> graph = new Graph<>();

		try {
			File myObj = new File(path);
			Scanner myReader = new Scanner(myObj);
			String lane = myReader.nextLine();
			String[] words;
			Pattern pattern = Pattern.compile("(\\w+)([0-9]*\\.?[0-9]*)");
			Matcher matcher;
			if (lane.split(" ")[0].equals("*Vertices")) {

				while(myReader.hasNextLine()) {
					lane = myReader.nextLine();
					if(lane.equals("*Edges")) {
						break;
					}
					matcher = pattern.matcher(lane);
					int i = 0;
					String info = "";
					matcher.find();
					while (matcher.find()) {
							if (i != 0) {
								info = info.concat(" ");
								
							}
							i++;
							info = info.concat(matcher.group());
					    }
					graph.addNode(info);
				}
				while(myReader.hasNextLine()) { 
					lane = myReader.nextLine();
					matcher = pattern.matcher(lane);
					matcher.find();
					Integer A = Integer.parseInt(matcher.group());
					matcher.find();
					Integer B = Integer.parseInt(matcher.group());
					matcher.find();
					graph.addEdgeIDX(A, B, matcher.group());
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
	private static int DFS(Graph<String, String> graph, String inici, Set<String> llista) {
		/*
		 * crear cola
		 * borrar inici de llista
		 * sacar adyacencias de inici
		 * 
		 */
		return 0;
	}

	/*
	 * Detecció de components connexes utilitzant un algorisme d’exploració com els
	 * explicats a classe.
	 */
	private static void getGraphInfo(Graph<String, String> graph, Integer[] info) {
		int NCC = 0, GCC = 1, SLCC = 1, grau;
		Set<String> nodes = graph.getAllNodes();
		System.out.println(nodes.size());
		while(!nodes.isEmpty()) {
			grau = DFS(graph,nodes.iterator().next(), nodes);
			
			if (grau >= GCC) {
				SLCC = GCC;
				GCC = grau;
			} else if (grau > SLCC)
				SLCC = grau;
			NCC++;
		}
		info[0] = NCC;
		info[1] = GCC;
		info[2] = SLCC;
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
		Graph<String, String> myGraph1 = new Graph<>();
		//Graph<Integer, String> myGraph2 = new Graph<>();

		//generaGraph("networks/wtw2000-sym.net");
		//myGraph1 = generaGraph("networks/airports_UW.net");
		myGraph1 = generaGraph("networks/wtw2000-sym.net");
		Integer[] info = new Integer[3]; 
		getGraphInfo(myGraph1, info);
		System.out.println(info[0]);
		System.out.println(info[1]);
		System.out.println(info[2]);
		
		/*yGraph1.addNode(0);
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

		}*/

	}

}
