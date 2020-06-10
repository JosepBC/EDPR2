package dataStructures;
import java.util.*;

/**
 * 
 * @author Josep Bello
 * @author Lautaro Russo
 *
 * @param <NodeData> Tipus dels nodes
 * @param <EdgeData> Tipus de les arestes
 */
public class Graph<NodeData, EdgeData> {
	int nElem;
	int nVertex;
	private HashMap<NodeData, LinkedList<EdgeT>> graph = new HashMap<>();
	boolean repeat;
	
	/**
	 * Petita classe per guardar l'informació d'un vèrtex i un enllaç
	 * @author Josep Bello
	 * @author Lautaro Russo
	 *
	 */
	private class EdgeT {
		private EdgeData edgeVal;
		private NodeData nextNode;
		
		public EdgeT(EdgeData edge, NodeData linkedNode) {
			this.edgeVal = edge;
			this.nextNode = linkedNode;
		}
		
		public EdgeData getEdgeVal() {
			return this.edgeVal;
		}
		
		public NodeData getNextNode() {
			return this.nextNode;
		}

		@Override
		public String toString() {
			return "EdgeT [edgeVal=" + edgeVal + ", nextNode=" + nextNode + "]";
		}
		
	}
	/**
	 * 
	 * @param repeat Determina si volem afegir l'enllaç A->B i B->A o no
	 */
	public Graph(boolean repeat) {
		super();
		this.nElem = 0;
		this.nVertex = 0;
		this.repeat = repeat;
	}
	
	/**
	 * Afegeix un node al graf amb el valor passat per paràmetre.
	 * @param val
	 * @return
	 */
	public void addNode(NodeData val) {
		graph.put(val, new LinkedList<EdgeT>());
		nElem++;
	}
	
	/**
	 * Conecta el nodeA amb el nodeB. Si algun node d'aquests no existeix, es crea.
	 * @param nodeA
	 * @param nodeB
	 * @param edgeData
	 */
	public void addEdge(NodeData valA, NodeData valB, EdgeData edgeData) {
		//Si algun dels nodes no existeix, afegeix-lo
		if(!graph.containsKey(valA))
			this.addNode(valA);
	
		if(!graph.containsKey(valB))
			this.addNode(valB);
		
		/*
		 * Si no hi ha repetits:
		 * Afegeix com a node el element que te un hashcode més gran, l'altre com a enllaç.
		 * Seria interessant que la classe dels nodes tingui ben implementat hashCode
		 */
		if(this.repeat) {
			EdgeT edge1 = new EdgeT(edgeData, valB);
			graph.get(valA).add(edge1);
			EdgeT edge2 = new EdgeT(edgeData, valA);
			graph.get(valB).add(edge2);
		} else {
			if(valA.hashCode() > valB.hashCode()) {
				EdgeT edge = new EdgeT(edgeData, valB);
				graph.get(valA).add(edge);
			} else {
				EdgeT edge = new EdgeT(edgeData, valA);
				graph.get(valB).add(edge);
			}
		}

		
		nVertex++;
	}
	
	/**
	 * Elimina el node passat per paràmetre, i totes les seves arestes.
	 * @param node
	 */
	public void removeNode(NodeData val) {
		if(graph.remove(val) != null)
			nElem--;
	}
	
	/**
	 * Operació per obtenir tots els enllaços del node passat per paràmetre. Null si no existeix
	 * @param node
	 * @return Els enllaços o null si no existeix el node passat
	 */
	public LinkedList<EdgeT> getLinks(NodeData node) {
		return graph.get(node);
	}

	public int getnElem() {
		return nElem;
	}

	public int getnVertex() {
		return nVertex;
	}

	@Override
	public String toString() {
		return "Graph [nElem=" + nElem + ", nVertex=" + nVertex + ", graph=" + graph + "]";
	}

}
