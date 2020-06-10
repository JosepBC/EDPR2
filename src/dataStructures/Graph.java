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
	private LinkedHashMap<NodeData, LinkedList<EdgeT>> graph = new LinkedHashMap<>();
	
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((edgeVal == null) ? 0 : edgeVal.hashCode());
			result = prime * result + ((nextNode == null) ? 0 : nextNode.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EdgeT other = (EdgeT) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (edgeVal == null) {
				if (other.edgeVal != null)
					return false;
			} else if (!edgeVal.equals(other.edgeVal))
				return false;
			if (nextNode == null) {
				if (other.nextNode != null)
					return false;
			} else if (!nextNode.equals(other.nextNode))
				return false;
			return true;
		}

		private Graph getEnclosingInstance() {
			return Graph.this;
		}
				
	}
	/**
	 * 
	 * @param repeat Determina si volem afegir l'enllaç A->B i B->A o no
	 */
	public Graph() {
		super();
		this.nElem = 0;
		this.nVertex = 0;
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
		
		EdgeT edge1 = new EdgeT(edgeData, valB);
		graph.get(valA).add(edge1);
		EdgeT edge2 = new EdgeT(edgeData, valA);
		graph.get(valB).add(edge2);
	
		nVertex++;
	}
	
	/**
	 * Elimina una aresta passant els index dels nodes que linka.
	 * @param idx1 Índex d'un node
	 * @param idx2 Índex de l'altre node
	 * @param edgeData Contingut de l'aresta
	 */
	public void addEdgeIDX(int idx1, int idx2, EdgeData edgeData) {
		Object[] keys = this.graph.keySet().toArray();
		addEdge((NodeData)keys[idx1-1], (NodeData)keys[idx2-1], edgeData);
	}
	
	/**
	 * Elimina un node, i totes les arestes que apunten a aquest passant un index. 
	 * @param idx Índex d'inserció, començant per 1
	 */
	public void removeNodeIDX(int idx) {
		Object[] keys = this.graph.keySet().toArray();
		removeNode((NodeData)keys[idx-1]);
	}
	
	/**
	 * Elimina el node passat per paràmetre, i totes les seves arestes.
	 * @param val Valor del node a eliminar
	 */
	public void removeNode(NodeData val) {
		LinkedList<EdgeT> edges = this.graph.remove(val);
		if(edges != null) {
			nElem--;
			for(EdgeT edge : edges) {
				EdgeT toRemove = new EdgeT(edge.getEdgeVal(), val);
				this.graph.get(edge.getNextNode()).remove(toRemove);
				nVertex--;
			}
		}
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

	public Set<NodeData> getAllNodes() {
		return this.graph.keySet();
	}
	
	@Override
	public String toString() {
		return "Graph [nElem=" + nElem + ", nVertex=" + nVertex + ", graph=" + graph + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((graph == null) ? 0 : graph.hashCode());
		result = prime * result + nElem;
		result = prime * result + nVertex;
		return result;
	}

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
		if (nElem != other.nElem)
			return false;
		if (nVertex != other.nVertex)
			return false;
		return true;
	}
	
	

}
