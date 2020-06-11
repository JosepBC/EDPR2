package algorithms;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner; // Import the Scanner class to read text file
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.ArrayList;

import dataStructures.*;

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
	private static Float BFS(Graph<String, String> graph, Set<String> llista) {
		ArrayList <String> cola = new ArrayList<String>();
		ArrayList <String> elim = new ArrayList<String>();
		Iterator<String> aux = llista.iterator();
		Float NCC = (float) 0;
		cola.add(aux.next());
		while (!cola.isEmpty()) {
			// sacar adyacéncias del primero de la cola
			for(EdgeT<String, String> node : graph.getLinks(cola.get(0))) {
				// añadir las que no estén en la cola ya
				String nextNode = node.getNextNode();
				if (!cola.contains(nextNode) && !elim.contains(nextNode)) {
					cola.add(node.getNextNode());
				}
			}
			// borrar el cabeza de cola de llista
			llista.remove(cola.get(0));
			// borrar el cabeza de cola de la cola
			elim.add(cola.get(0));
			cola.remove(0);
			NCC++;
		}
		return NCC;
	}

	/*
	 * Detecció de components connexes utilitzant un algorisme d’exploració com els
	 * explicats a classe.
	 */
	private static void getGraphInfo(Graph<String, String> graph, Float[] info) {
		Float NCC = (float) 0, GCC = (float) 0, SLCC = (float) 0, grau;
		Set<String> nodes = graph.getAllNodes();
		while(!nodes.isEmpty()) {
			grau = BFS(graph, nodes);
			
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
	private static void randomAtack(Graph<String, String> graph, String path) {
		try {
			Float  OP, numNodes = (float) graph.getnVertex();
			Float[] info = new Float[3]; 
			File result = new File(path);
			FileWriter writer = new FileWriter(result);
			writer.write("OP,NCC,GCC,SLCC\n");
			
			while(graph.getnElem() > 0) {
				ArrayList <String> insert = new ArrayList<String>();
				OP = numNodes / graph.getnElem();
				insert.add(OP.toString());
				getGraphInfo(graph.clone(), info);
				insert.add(info[0].toString());
				insert.add(info[1].toString());
				insert.add(info[2].toString());
				writer.write(insert.stream().collect(Collectors.joining(","))+"\n");
				graph.removeRand();
				graph.removeRand();
				graph.removeRand();
			}
			writer.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void gradeAtack(Graph<String, String> graph, Heap <Float> heap, String path) {
		try {
			Float  OP, numNodes = (float) graph.getnVertex();
			Float[] info = new Float[3]; 
			File result = new File(path);
			FileWriter writer = new FileWriter(result);
			writer.write("OP,NCC,GCC,SLCC\n");
			
			while(graph.getnElem() > 0) {
				ArrayList <String> insert = new ArrayList<String>();
				OP = numNodes / graph.getnElem();
				insert.add(OP.toString());
				getGraphInfo(graph, info);
				insert.add(info[0].toString());
				insert.add(info[1].toString());
				insert.add(info[2].toString());
				writer.write(insert.stream().collect(Collectors.joining(","))+"\n");
			}
			writer.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void strengthAtack(Graph<String, String> graph, Heap <Float> heap, String path) {
		try {
			Float  OP, numNodes = (float) graph.getnVertex();
			Float[] info = new Float[3]; 
			File result = new File(path);
			FileWriter writer = new FileWriter(result);
			writer.write("OP,NCC,GCC,SLCC\n");
			
			while(graph.getnElem() > 0) {
				ArrayList <String> insert = new ArrayList<String>();
				OP = numNodes / graph.getnElem();
				insert.add(OP.toString());
				getGraphInfo(graph, info);
				insert.add(info[0].toString());
				insert.add(info[1].toString());
				insert.add(info[2].toString());
				writer.write(insert.stream().collect(Collectors.joining(","))+"\n");
			}
			writer.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Anàlisi de percolació. Visualització de l’evolució del nombre de components
	 * connexes de la xarxa, i de la mida de les dues components connexes més grans,
	 * a mesura que anem extirpant nodes de la xarxa.
	 */
	private static void percoloracio(Graph<String, String> myGraph1, Heap <Float> heap, int mode){
		String path = "result.csv";
		switch (mode) {
		case 0:
			System.out.println("Starting random attack....");
			randomAtack(myGraph1, path);
			System.out.println("Random attack done");
			break;
		case 1:
			System.out.println("Starting grad attack....");
			gradeAtack(myGraph1, heap, path);
			System.out.println("Random attack done");
			break;
		case 2:
			System.out.println("Starting strength attack....");
			strengthAtack(myGraph1, heap, path);
			System.out.println("Random attack done");
			break;
		}	
	}

	public static void main(String[] args) {
		Graph<String, String> myGraph1 = new Graph<>();
		//Graph<Integer, String> myGraph2 = new Graph<>();

		//generaGraph("networks/wtw2000-sym.net");
		//myGraph1 = generaGraph("networks/airports_UW.net");
		myGraph1 = generaGraph("networks/wtw2000-sym.net");
		//myGraph1 = generaGraph("networks/email_URV-edges_betw.net");
		//myGraph1 = generaGraph("networks/powergrid_USA-edges_betw.net");
		//Integer[] info = new Integer[3]; 
		//getGraphInfo(myGraph1, info);
		percoloracio(myGraph1,new Heap<Float>(), 0);
		
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
