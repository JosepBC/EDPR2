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

	
			
}