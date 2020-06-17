package algorithms;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
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
	private static Graph<String, Float> generaGraph(String path) {
		Graph<String, Float> graph = new Graph<>();
		
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
					graph.addEdgeIDX(A, B, Float.parseFloat(matcher.group()));
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
	/**
	 * Explora en amplitud tots el nodes d'un arbre de un graph pasat per referencia eliminant els nodes visitats de la llista
	 * @param graph rep el graph a explorar
	 * @param llista llista de nodes visitats
	 * @return grau de la component conexa
	 */
	private static Float BFS(Graph<String, Float> graph, Set<String> llista) {
		ArrayList <String> cola = new ArrayList<String>();
		ArrayList <String> elim = new ArrayList<String>();
		Iterator<String> aux = llista.iterator();
		Float grau = (float) 0;
		cola.add(aux.next());
		while (!cola.isEmpty()) {
			// sacar adyacéncias del primero de la cola
			for(EdgeT<String, Float> node : graph.getLinks(cola.get(0))) {
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
			grau++;
		}
		return grau;
	}

	/**
	 * Explora un graph contant el nombre de components conexes, i guardant la mes gran y la segona mesgran
	 * @param graph graph a explorar
	 * @param info vector amb les dades a retornar per referéncia
	 */
	private static void getGraphInfo(Graph<String, Float> graph, Float[] info) {
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
	/**
	 * Algorisme de percoloració mitjançant atacks aleatoris, recull les dades i les afegéix a un fitxer
	 * @param graph graph base que hem d'analitzar
	 * @param path directori del fitxer on emmagatzemar les dades recollides
	 */
	private static void randomAtack(Graph<String, Float> graph, String path) {
		try {
			Float  OP, NCC = (float) 0;
			Float numNodes = (float) graph.getnNodes();
			Float[] info = new Float[3]; 
			File result = new File(path);
			FileWriter writer = new FileWriter(result);
			writer.write("OP,NCC,GCC,SLCC\n");
			while(graph.getnNodes() > 0) {
				ArrayList <String> insert = new ArrayList<String>();
				OP = graph.getnNodes() / numNodes;
				insert.add(OP.toString());
				getGraphInfo(graph.clone(), info);
				NCC = info[0]+(numNodes-graph.getnNodes());
				insert.add(((Float)(NCC/numNodes)).toString());
				insert.add(((Float)(info[1]/numNodes)).toString());
				insert.add(((Float)(info[2]/numNodes)).toString());
				writer.write(insert.stream().collect(Collectors.joining(",")));
				writer.write("\n");
				//System.out.println(graph);
				graph.removeRand();
			}
			writer.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Algorisme de percoloració mitjançant atacks seguint l'ordre d'un max heap, recull les dades i les afegéix a un fitxer
	 * @param graph graph base que hem d'analitzar
	 * @param heap estructura ordenada max-heap, determina el criteri de l'atac
	 * @param path directori del fitxer on emmagatzemar les dades recollides
	 */
	private static void heapAtack(Graph<String, Float> graph, MaxHeap<NodeT<String>> heap, String path) {
		try {
			Float  OP, numNodes = (float) graph.getnNodes(), NCC = (float)0;
			Float[] info = new Float[3]; 
			File result = new File(path);
			FileWriter writer = new FileWriter(result);
			writer.write("OP,NCC,GCC,SLCC\n");
			
			while(graph.getnNodes() > 0) {
				ArrayList <String> insert = new ArrayList<String>();
				OP = graph.getnNodes() / numNodes;
				insert.add(OP.toString());
				getGraphInfo(graph.clone(), info);
				NCC = info[0]+(numNodes-graph.getnNodes());
				insert.add(((Float)(NCC/numNodes)).toString());
				insert.add(((Float)(info[1]/numNodes)).toString());
				insert.add(((Float)(info[2]/numNodes)).toString());
				writer.write(insert.stream().collect(Collectors.joining(",")));
				writer.write("\n");
				graph.removeNode(heap.extractRoot().getContentOfNode());
				//System.out.println("Graph: "+graph.getnElem());
				//System.out.println("Heap: "+heap.getnElem()+"\n");
			}
			writer.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Genera un max-heap amb els nodes ordenats segons el seu grau
	 * @param graph graph base d'on treure els nodes.
	 * @return dades estructurades en forma de max-heap
	 */
	private static MaxHeap<NodeT<String>> genGradHeap(Graph<String, Float> graph) {
		MaxHeap<NodeT<String>> heap = new MaxHeap<>();
		Set<String> nodes = graph.getAllNodes();
		for (String content : nodes) {
			heap.insert(new NodeT<String>(content, (float)graph.getLinks(content).size()));
		}
		
		return heap;
	}
	/**
	 * Genera un max-heap amb els nodes ordenats segons el pes total de les conexions del node
	 * @param graph graph base d'on treure els nodes.
	 * @return dades estructurades en forma de max-heap
	 */
	private static MaxHeap<NodeT<String>> genStrHeap(Graph<String, Float> graph) {
		MaxHeap<NodeT<String>> heap = new MaxHeap<>();
		Set<String> nodes = graph.getAllNodes();
		for (String content : nodes) {
			Float key = (float) 0;
			LinkedList<EdgeT<String, Float>> links = graph.getLinks(content);
			for(EdgeT<String, Float> link : links) {
				key += link.getEdgeVal();
			}
			heap.insert(new NodeT<String>(content, key));
		}
		
		return heap;
	}
	
	/** 
	 * Anàlisi de percolació. Visualització de l’evolució del nombre de components connexes de la xarxa, i de la mida de les dues components connexes més grans, a mesura que anem extirpant nodes de la xarxa.
	 * @param graph graph a analitzar
	 * @param mode criteri d'atack
	 * @param path directori del fitxer on emmagatzemar les dades
	 */
	
	private static void percoloracio(Graph<String, Float> graph, int mode, String path){
		switch (mode) {
			case 0:
				System.out.println("Iniciando random attack....");
				randomAtack(graph, path);
				System.out.println("Random attack done");
				break;
			case 1:
				System.out.println("Iniciando garde attack....");
				heapAtack(graph, genGradHeap(graph), path);
				System.out.println("Grade attack done");
				break;
			case 2:
				System.out.println("Iniciando strength attack....");
				heapAtack(graph, genStrHeap(graph), path);
				System.out.println("Strength attack done");
				break;
		}	
	}

	public static void main(String[] args) throws IOException {
		Graph<String, Float> graph = new Graph<>();
		double inicio, fin, tiempo = 0;
		final int EXEC = 100;
		File result = new File("tiempos.csv");
		FileWriter writer = new FileWriter(result);
		writer.write("Red, Random, grade, strength\n");
		
		//Red wtw
		//Random
		tiempo = 0;
		graph = generaGraph("networks/wtw2000-sym.net");
		for(int i = 1; i <= EXEC; i++) {
			inicio = System.currentTimeMillis();
			percoloracio(graph.clone(), 0, "wtw/random/randomWtw"+i+".csv");
	        fin = System.currentTimeMillis();
	        graph = generaGraph("networks/wtw2000-sym.net");
	        tiempo += (double) ((fin - inicio)/1000);
	        
		}
		System.out.println("Tiempo medio en random wtw: "+tiempo/EXEC+" segundos");
		writer.write("Wtw,"+tiempo/EXEC+",");
		
		//Grade
		tiempo = 0;
		graph = generaGraph("networks/wtw2000-sym.net");
		inicio = System.currentTimeMillis();
		percoloracio(graph, 1, "wtw/gradeWtw.csv");
        fin = System.currentTimeMillis();
        tiempo = (double) ((fin - inicio)/1000);
        System.out.println("Grade wtw: "+tiempo +" segundos");
        writer.write(tiempo+",");
        
        //Strength
        tiempo = 0;
		graph = generaGraph("networks/wtw2000-sym.net");
		inicio = System.currentTimeMillis();
		percoloracio(graph, 2, "wtw/strengthWtw.csv");
        fin = System.currentTimeMillis();
        tiempo = (double) ((fin - inicio)/1000);
        System.out.println("Strentgh wtw: "+tiempo +" segundos");		
        writer.write(tiempo+"\n");
		System.out.println();
		
		//Red email URV
		//Random
		tiempo = 0;
		graph = generaGraph("networks/email_URV-edges_betw.net");
		for(int i = 1; i <= EXEC; i++) {
			inicio = System.currentTimeMillis();
			percoloracio(graph.clone(), 0, "email/random/randomEmail"+i+".csv");
	        fin = System.currentTimeMillis();
	        tiempo += (double) ((fin - inicio)/1000);
	        graph = generaGraph("networks/email_URV-edges_betw.net");
		}
		System.out.println("Tiempo medio en random email: "+tiempo/EXEC+" segundos");
		writer.write("Email,"+tiempo/EXEC+",");
		
		//Grade
		tiempo = 0;
		graph = generaGraph("networks/email_URV-edges_betw.net");
		inicio = System.currentTimeMillis();
		percoloracio(graph, 1, "email/gradeEmail.csv");
        fin = System.currentTimeMillis();
        tiempo = (double) ((fin - inicio)/1000);
        System.out.println("Grade email: "+tiempo +" segundos");
        writer.write(tiempo+",");
        
        //Strength
        tiempo = 0;
        graph = generaGraph("networks/email_URV-edges_betw.net");
		inicio = System.currentTimeMillis();
		percoloracio(graph, 2, "email/strengthEmail.csv");
        fin = System.currentTimeMillis();
        tiempo = (double) ((fin - inicio)/1000);
        System.out.println("Strentgh email: "+tiempo +" segundos");		
        writer.write(tiempo+"\n");
		System.out.println();
		
		//Red airports
		//Random
		tiempo = 0;
		graph = generaGraph("networks/airports_UW.net");
		for(int i = 1; i <= EXEC; i++) {
			inicio = System.currentTimeMillis();
			percoloracio(graph.clone(), 0, "airports/random/randomAirports"+i+".csv");
	        fin = System.currentTimeMillis();
	        tiempo += (double) ((fin - inicio)/1000);
	        graph = generaGraph("networks/airports_UW.net"); 
		}
		System.out.println("Tiempo medio en random aeropuertos: "+tiempo/EXEC+" segundos");
		writer.write("Airports,"+tiempo/EXEC+",");
		
		//Grade
		tiempo = 0;
		graph = generaGraph("networks/airports_UW.net");
		inicio = System.currentTimeMillis();
		percoloracio(graph, 1, "airports/gradeAirports.csv");
        fin = System.currentTimeMillis();
        tiempo = (double) ((fin - inicio)/1000);
        System.out.println("Grade airports: "+tiempo +" segundos");
        writer.write(tiempo+",");
        
        //Strength
        tiempo = 0;
        graph = generaGraph("networks/airports_UW.net");
		inicio = System.currentTimeMillis();
		percoloracio(graph, 2, "airports/strengthAirports.csv");
        fin = System.currentTimeMillis();
        tiempo = (double) ((fin - inicio)/1000);
        System.out.println("Strentgh airports: "+tiempo +" segundos");
        writer.write(tiempo+"\n");
        
        
		//Red distribución electirca
        //Random
        tiempo = 0;
		graph = generaGraph("networks/powergrid_USA-edges_betw.net");
		for(int i = 1; i <= EXEC; i++) {
			inicio = System.currentTimeMillis();
			percoloracio(graph.clone(), 0, "electrica/random/randomElectrica"+i+".csv");
	        fin = System.currentTimeMillis();
	        tiempo += (double) ((fin - inicio)/1000);
	        graph = generaGraph("networks/powergrid_USA-edges_betw.net");
		}
		System.out.println("Tiempo medio en random red electrica: "+tiempo/EXEC+" segundos");
		writer.write("Electrica,"+tiempo/EXEC+",");
		
		//Grade
		tiempo = 0;
		graph = generaGraph("networks/powergrid_USA-edges_betw.net");
		inicio = System.currentTimeMillis();
		percoloracio(graph, 1, "electrica/gradeElectrica.csv");
        fin = System.currentTimeMillis();
        tiempo = (double) ((fin - inicio)/1000);
        System.out.println("Grade electrica: "+tiempo +" segundos");
        writer.write(tiempo+",");
        
        //Strength
        tiempo = 0;
		graph = generaGraph("networks/powergrid_USA-edges_betw.net");
		inicio = System.currentTimeMillis();
		percoloracio(graph, 2, "electrica/strengthElectrica.csv");
        fin = System.currentTimeMillis();
        tiempo = (double) ((fin - inicio)/1000);
        System.out.println("Strentgh electrica: "+tiempo +" segundos");
        writer.write(tiempo+"\n");
        
        writer.close();
	}
}
