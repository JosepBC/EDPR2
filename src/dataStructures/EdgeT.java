package dataStructures;

/**
 * Petita classe per guardar l'informació d'un vèrtex i un enllaç
 * @author Josep Bello
 * @author Lautaro Russo
 *
 */
public class EdgeT<NodeData, EdgeData> {
	private EdgeData edgeVal;
	private NodeData nextNode;
	
	/**
	 * Constructor de la classe amb tots els paràmetres per crear una instància.
	 * @param edge Informació a guardar a l'aresta
	 * @param linkedNode Node al qual enllaça l'aresta
	 */
	public EdgeT(EdgeData edge, NodeData linkedNode) {
		this.edgeVal = edge;
		this.nextNode = linkedNode;
	}
	
	/**
	 * Getter del valor de l'aresta
	 * @return Valor de l'aresta
	 */
	public EdgeData getEdgeVal() {
		return this.edgeVal;
	}
	
	/**
	 * Getter del node enllaçat
	 * @return Node enllaçat
	 */
	public NodeData getNextNode() {
		return this.nextNode;
	}

	/**
	 * toString de la classe
	 */
	@Override
	public String toString() {
		return "EdgeT [edgeVal=" + edgeVal + ", nextNode=" + nextNode + "]";
	}

	/**
	 * hashCode de la classe
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edgeVal == null) ? 0 : edgeVal.hashCode());
		result = prime * result + ((nextNode == null) ? 0 : nextNode.hashCode());
		return result;
	}

	/**
	 * equals de la classe
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeT other = (EdgeT) obj;
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

	/**
	 * clone de la classe
	 */
	@Override
	public EdgeT<NodeData, EdgeData> clone(){
		return new EdgeT<NodeData, EdgeData>(this.edgeVal, this.nextNode);
	}
	
	

}