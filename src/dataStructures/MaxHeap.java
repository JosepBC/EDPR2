package dataStructures;

import java.util.ArrayList;

/**
 * Classe per gestionar un MaxHeap genèric
 * @author Josep Bello
 * @author Lautaro Russo
 *
 * @param <T> Tipus de la classe, ha d'implementar Comparable
 */
public class MaxHeap<T extends Comparable<T>> {
	private ArrayList<T> maxHeap = new ArrayList<T>();
	
	private boolean swap(int idx1, int idx2) {
		if(idx1 > maxHeap.size() - 1 || idx2 > maxHeap.size() - 1) return false;
		T tmp = maxHeap.get(idx1);
		maxHeap.set(idx1, maxHeap.get(idx2));
		maxHeap.set(idx2, tmp);
		return true;
	}
	
	/**
	 * Mètode per inserir un node al heap
	 * @param val Node a inserir
	 */
	public void insert(T val) {
		maxHeap.add(val);
		int currentIDX = maxHeap.size() - 1;
		int fatherIDX = currentIDX / 2;
		while(fatherIDX >= 0 && (maxHeap.get(fatherIDX)).compareTo(val) < 0) {
			swap(currentIDX, fatherIDX);
			currentIDX = fatherIDX;
			fatherIDX = (currentIDX - 1) / 2; 
		}
	}
	
	/**
	 * Obtenir l'índex del fill esquerre d'un pare
	 * @param fatherIDX Índex del pare
	 * @return El fill esquerre o null si no en te o es null
	 */
	private T getLeftChild(int fatherIDX) {
		if(fatherIDX * 2 + 1 > maxHeap.size() - 1) return null;
		return maxHeap.get((fatherIDX * 2) + 1);
	}
	
	/**
	 * Obtenir l'índex del fill dret d'un pare
	 * @param fatherIDX Índex del pare
	 * @return El fill dret o null si no en te o es null
	 */
	private T getRightChild(int fatherIDX) {
		if((fatherIDX * 2) + 2 > maxHeap.size() - 1) return null;
		return maxHeap.get((fatherIDX * 2) + 2);
	}
	
	/**
	 * Mètode per comprobar si existeix el fill dret
	 * @param fatherIDX Índex del pare
	 * @return True si existeix, false sino
	 */
	private boolean existRightChild(int fatherIDX) {
		return((fatherIDX * 2) + 2 > maxHeap.size() - 1);
	}
	
	/**
	 * Mètode per comprobar si existeix el fill esquerre
	 * @param fatherIDX Índex del pare
	 * @return True si existeix, false sino
	 */
	private boolean existLeftChild(int fatherIDX) {
		return((fatherIDX * 2) + 1 > maxHeap.size() - 1);
	}
	
	/**
	 * Mètode per obtenir l'arrel del heap
	 * @return Arrel del heap
	 */
	public T extractRoot() {
		if(maxHeap.size() == 0) return null;
		T root = maxHeap.get(0);
		T last = maxHeap.get(maxHeap.size() - 1);
		swap(0, maxHeap.size() - 1);
		maxHeap.remove(maxHeap.size() - 1);
		
		int fatherIDX = 0;
		int sonIDX = 1;
		
		if(existRightChild(fatherIDX) && 
				existLeftChild(fatherIDX) &&
				sonIDX < maxHeap.size() && 
				getRightChild(fatherIDX).compareTo(getLeftChild(fatherIDX)) > 0) sonIDX++;
		
		while(sonIDX < maxHeap.size() && maxHeap.get(sonIDX).compareTo(last) > 0) {
			swap(fatherIDX, sonIDX);
			fatherIDX = sonIDX;
			sonIDX = (fatherIDX * 2) + 1;
			if(existRightChild(fatherIDX) && 
					existLeftChild(fatherIDX) &&
					sonIDX < maxHeap.size() && 
					getRightChild(fatherIDX).compareTo(getLeftChild(fatherIDX)) > 0) sonIDX++;
		}
		
		return root;
	}
	
	/**
	 * Print bàsic per fer debugg
	 */
	public void print() {
		System.out.println(maxHeap.toString());
        for (int i = 0; i < maxHeap.size() / 2; i++) { 
            System.out.print("idx: "+i+" PARENT : " + maxHeap.get(i) + " LEFT CHILD : " + 
                      getLeftChild(i) + " RIGHT CHILD :" + getRightChild(i)); 
            System.out.println(); 
        } 
		 
	}
	
	/**
	 * Print en forma d'arbre trobat per internet, no funciona del tot be ja que començem desde el 0
	 */
	public void coolPrint() {
		StringBuilder sb = new StringBuilder();
	    int max=0;
	    for(int i=0;i<10;i++){
	        for(int j=0;j<Math.pow(2,i)&&j+Math.pow(2,i)<10;j++){

	            if(j>max){
	                max=j;
	            }
	        }

	    }

	    for(int i=0;i<maxHeap.size();i++){
	        for(int j=0;j<Math.pow(2,i)&&j+Math.pow(2,i)<maxHeap.size();j++){

	            for(int k=0;(k<max/((int)Math.pow(2, i)));k++){
	                sb.append(" ");
	            }
	            sb.append(maxHeap.get(j+(int)Math.pow(2,i)-1)+" ");

	        }
	        sb.append("\n");

	    }
	    
	    System.out.println(sb.toString());
	}
	
	/**
	 * Getter del nombre d'elements del heap
	 * @return Nombre d'elements del heap
	 */
	public int getnElem() {
		return maxHeap.size();
	}
}
