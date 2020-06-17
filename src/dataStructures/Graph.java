package dataStructures;
import java.util.*;

/**
 * Classe per gestionar un graf genèric en informació als nodes i arestes
 * @author Josep Bello
 * @author Lautaro Russo
 *
 * @param <NodeData> Tipus dels nodes
 * @param <EdgeData> Tipus de les arestes
 */
public class Graph<NodeData, EdgeData> {
	private int nNodes;
	private int nEdges;
	private LinkedHashMap<NodeData, LinkedList<EdgeT<NodeData, EdgeData>>> graph = new LinkedHashMap<>();

	/**
	 * Constructor bàsic.
	 */
	public Graph() {
		super();
		this.nNodes = 0;
		this.nEdges = 0;
	}
	
	/**
	 * Constructor per crear una copia
	 * @param nNodes Nombre de nodes
	 * @param nEdges Nombre d'arestes
	 * @param graph Graf
	 */
	private Graph(int nNodes, int nEdges, LinkedHashMap<NodeData, LinkedList<EdgeT<NodeData, EdgeData>>> graph) {
		super();
		this.nNodes = nNodes;
		this.nEdges = nEdges;
		this.graph = new LinkedHashMap<NodeData, LinkedList<EdgeT<NodeData, EdgeData>>>(graph);
	}
	
	/**
	 * Afegeix un node al graf amb el valor passat per paràmetre
	 * @param val Valor a afegir
	 */
	public void addNode(NodeData val) {
		graph.put(val, new LinkedList<EdgeT<NodeData, EdgeData>>());
		nNodes++;
	}
	
	/**
	 * Conecta el nodeA amb el nodeB. Si algun node d'aquests no existeix, es crea
	 * @param valA Contingut del nodeA
	 * @param valB Contingut del nodeB
	 * @param edgeData Contingut a guardar a l'aresta
	 */
	public void addEdge(NodeData valA, NodeData valB, EdgeData edgeData) {
		//Si algun dels nodes no existeix, afegeix-lo
		if(!graph.containsKey(valA))
			this.addNode(valA);
	
		if(!graph.containsKey(valB))
			this.addNode(valB);
		
		EdgeT<NodeData, EdgeData> edge1 = new EdgeT(edgeData, valB);
		graph.get(valA).add(edge1);
		EdgeT<NodeData, EdgeData> edge2 = new EdgeT(edgeData, valA);
		graph.get(valB).add(edge2);
	
		nEdges++;
	}
	
	/**
	 * Elimina una aresta passant els índex dels nodes que ha d'unir
	 * @param idx1 Índex del node A segons ordre d'inserció. 1...N
	 * @param idx2 Índex del node B segons ordre d'inserció. 1...N
	 * @param edgeData Contingut a guardar a l'arestaa
	 * @return True si s'ha pogut afegir, false si algun dels dos nodes no existeix
	 */
	public boolean addEdgeIDX(int idx1, int idx2, EdgeData edgeData) {
		if(idx1 > this.graph.size() || idx2 > this.graph.size()) return false;
		Object[] keys = this.graph.keySet().toArray();
		addEdge((NodeData)keys[idx1-1], (NodeData)keys[idx2-1], edgeData);
		return true;
	}
	
	/**
	 * Elimina un node, i totes les arestes que apunten a aquest passant un index
	 * @param idx Índex del node segons ordre d'inserció. 1...N
	 * @return True si s'ha trobat i pogut eliminar, false sino
	 */
	public boolean removeNodeIDX(int idx) {
		if(idx > this.graph.size()) return false;
		Object[] keys = this.graph.keySet().toArray();
		return removeNode((NodeData)keys[idx-1]);
	}
	
	/**
	 * Elimina un aleatori, i totes les arestes que apunten a aquest
	 * @return True si s'ha trobat i eliminat, false en cualsevol altre cas
	 */
	public boolean removeRand() {
		if(nNodes == 0) return false;
		Object[] keys = this.graph.keySet().toArray();
		Random rand = new Random();
		return removeNode((NodeData)keys[rand.nextInt(nNodes)]);
	}
	
	/**
	 * Elimina el node passat per paràmetre, i totes les seves arestes.
	 * @param val Valor del node a eliminar
	 * @return True si s'ha trobat i eliminat, false en cualsevol altre cas
	 */
	public boolean removeNode(NodeData val) {
		if(this.graph.containsKey(val)) {
			LinkedList<EdgeT<NodeData, EdgeData>> edges = this.graph.remove(val);
			this.nNodes--;
			for(EdgeT<NodeData, EdgeData> edge : edges) {
				EdgeT<NodeData, EdgeData> toRemove = new EdgeT<>(edge.getEdgeVal(), val);
				if(this.graph.containsKey(edge.getNextNode())) { //Si contiene el nodo al que apunta, eliminalo
					this.graph.get(edge.getNextNode()).remove(toRemove);
				}
				this.nEdges--;
			}
			return true;
		}
		return false;
	}

	/**
	 * Operació per obtenir tots els enllaços del node passat per paràmetre. Null si no existeix
	 * @param node Node del cual obtenir els enllaços
	 * @return Els enllaços o null si no existeix el node passat
	 */
	public LinkedList<EdgeT<NodeData, EdgeData>> getLinks(NodeData node) {
		return graph.get(node);
	}

	/**
	 * Getter del nombre de nodes del graf
	 * @return Nombre de nodes
	 */
	public int getnNodes() {
		return nNodes;
	}

	/**
	 * Getter del nombre d'arestes del graf
	 * @return Nombre d'arestes
	 */
	public int getnEdges() {
		return nEdges;
	}

	/**
	 * Mètode per obtenir tots els nodes del graf
	 * @return Set amb tots els nodes del graf
	 */
	public Set<NodeData> getAllNodes() {
		return this.graph.keySet();
	}
	
	
	/**
	 * toString de la classe
	 */
	@Override
	public String toString() {
		return "Graph [nElem=" + nNodes + ", nVertex=" + nEdges + ", graph=" + graph + "]";
	}

	
	/**
	 * HashCode de la classe
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((graph == null) ? 0 : graph.hashCode());
		result = prime * result + nNodes;
		result = prime * result + nEdges;
		return result;
	}

	
	/**
	 * Equals de la claasse
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (graph == null) {
			if (other.graph != null)
				return false;
		} else if (!graph.equals(other.graph))
			return false;
		if (nNodes != other.nNodes)
			return false;
		if (nEdges != other.nEdges)
			return false;
		return true;
	}
	
	/**
	 * Clone de la classe
	 */
	@Override
	public Graph<NodeData, EdgeData> clone() {
		return new Graph<NodeData, EdgeData>(this.nNodes, this.nEdges, this.graph);
	}
	
}
