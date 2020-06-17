package dataStructures;

/**
 * Petita classe par utilizar com nodes del heap de la P2 de ED
 * Disenyada per guardar una clau i un valor
 * La clau sera l'utilitzada per ordenar el heap
 * @author Josep Bello
 * @author Lautaro Russo
 *
 * @param <NodeData> Tipus del valor
 */
public class NodeT<NodeData> implements Comparable<NodeT<NodeData>> {
	private NodeData contentOfNode;
	private Float heapKey;
	
	/**
	 * Únic constructor de la classe, amb totes les dades necessaries
	 * @param contentOfNode Identificador del node al graf, el seu contingut, s'utilitzara despres per buscar-lo.
	 * @param heapKey Paràmetre que utilitzarem per ordenar el heap
	 */
	public NodeT(NodeData contentOfNode, Float heapKey) {
		this.contentOfNode = contentOfNode;
		this.heapKey = heapKey;
	}
	
	/**
	 * Getter del identificador del node, contingut
	 * @return Identificador del node
	 */
	public NodeData getContentOfNode() {
		return contentOfNode;
	}

	/**
	 * Getter de la clau que s'utilitzará per ordenar en el heap
	 * @return Valor de la clau
	 */
	public Float getHeapKey() {
		return heapKey;
	}

	/**
	 * Mètode per comparar amb un altre node
	 * @param o Node amb el que comparar
	 * @return -1, 0 o 1 en funció de si el valor passat es mes petit, igual o mes gran
	 */
	@Override
	public int compareTo(NodeT<NodeData> o) {
		return this.getHeapKey().compareTo(o.heapKey);
	}


	/**
	 * hashCode de la classe
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentOfNode == null) ? 0 : contentOfNode.hashCode());
		result = prime * result + ((heapKey == null) ? 0 : heapKey.hashCode());
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
		NodeT other = (NodeT) obj;
		if (contentOfNode == null) {
			if (other.contentOfNode != null)
				return false;
		} else if (!contentOfNode.equals(other.contentOfNode))
			return false;
		if (heapKey == null) {
			if (other.heapKey != null)
				return false;
		} else if (!heapKey.equals(other.heapKey))
			return false;
		return true;
	}

	/**
	 * toString de la classe
	 */
	@Override
	public String toString() {
		return "NodeT [contentOfNode=" + contentOfNode + ", heapKey=" + heapKey + "]";
	}
	

}
