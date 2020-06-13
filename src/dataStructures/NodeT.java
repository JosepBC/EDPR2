package dataStructures;

/**
 * Pequeña clase para utilizar como nodos del heap de la P2 de ED.
 * Diseñada para guardar una clave y un valor. 
 * - La clave es de tipo float y es lo que usara el heap para ordenar
 * - El valor es de tipo T, generico, pensado para guardar exactamente el mismo valor que en el grafo.
 * @author Josep Bello
 * @author Lautaro Russo
 *
 * @param <NodeData> Tipo del nodo que hay en el grafo. 
 */
public class NodeT<NodeData> implements Comparable<NodeT<NodeData>> {
	private NodeData contentOfNode;
	private Float heapKey;
	
	/**
	 * Unico constructor de la classe. Guarda todos los datos necesarios.
	 * @param contentOfNode Contenido del nodo del grafo. Se usara despues para buscar en el grafo el nodo.
	 * @param heapKey Parametro que se usara para ordenar en el heap. Diseñado para guardar el grado/strength del nodo que contiene como valor el pametro anterior
	 */
	public NodeT(NodeData contentOfNode, Float heapKey) {
		this.contentOfNode = contentOfNode;
		this.heapKey = heapKey;
	}
	
	public NodeData getContentOfNode() {
		return contentOfNode;
	}

	public Float getHeapKey() {
		return heapKey;
	}

	@Override
	public int compareTo(NodeT<NodeData> o) {
		return this.getHeapKey().compareTo(o.heapKey);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentOfNode == null) ? 0 : contentOfNode.hashCode());
		result = prime * result + ((heapKey == null) ? 0 : heapKey.hashCode());
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
	
	

}
