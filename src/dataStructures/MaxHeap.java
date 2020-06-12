package dataStructures;

import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
	private ArrayList<T> maxHeap = new ArrayList<>();
	private int nElem = 0;
	
	/**
	 * Cambia l'element de l'idx1 per el del idx2
	 * @param idx1
	 * @param idx2
	 * @return
	 */
	private boolean swap(int idx1, int idx2) {
		if(idx1 > maxHeap.size() - 1 || idx2 > maxHeap.size() - 1) return false;
		T tmp = maxHeap.get(idx1);
		maxHeap.set(idx1, maxHeap.get(idx2));
		maxHeap.set(idx2, tmp);
		return true;
	}
	
	public boolean insert(T val) {
		maxHeap.add(val);
		int currentIDX = maxHeap.size() - 1;
		int fatherIDX = currentIDX / 2;
		while(fatherIDX >= 0 && (maxHeap.get(fatherIDX)).compareTo(val) < 0) {
			swap(currentIDX, fatherIDX);
			currentIDX = fatherIDX;
			fatherIDX = currentIDX / 2; 
		}
		//maxHeap.add(currentIDX, val);
		return true;
	}
	
	private T getLeftChild(int fatherIDX) {
		if(fatherIDX * 2 > maxHeap.size() - 1) return null;
		return maxHeap.get(fatherIDX * 2);
	}
	
	private T getRightChild(int fatherIDX) {
		if((fatherIDX * 2) + 1 > maxHeap.size() - 1) return null;
		return maxHeap.get((fatherIDX * 2) + 1);
	}
	
	private void removeRoot() {
		T last = maxHeap.get(maxHeap.size() - 1);
		swap(0, maxHeap.size() - 1);
		maxHeap.remove(maxHeap.size() - 1);
		
		int fatherIDX = 1;
		int sonIDX = 2;
		
		if((sonIDX < maxHeap.size()) && (getRightChild(fatherIDX).compareTo(getLeftChild(fatherIDX)) > 0)) sonIDX++;
		
		while(sonIDX <= maxHeap.size() && maxHeap.get(sonIDX).compareTo(last) > 0) {
			swap(fatherIDX, sonIDX);
			fatherIDX = sonIDX;
			sonIDX = fatherIDX * 2;
			if((sonIDX < maxHeap.size()) && (getRightChild(fatherIDX).compareTo(getLeftChild(fatherIDX)) > 0)) sonIDX++;
		}
		
	}
	
	public T extractRoot() {
		T ret =  maxHeap.get(0);
		removeRoot();
		return ret;
	}
	
	public void print() {
		 for (int i = 1; i < maxHeap.size() / 2; i++) { 
	            System.out.print(" PARENT : " + maxHeap.get(i - 1) + " LEFT CHILD : " + 
	                      maxHeap.get((2 * i) - 1)+ " RIGHT CHILD :" + maxHeap.get(2 * i)); 
	            System.out.println(); 
	     } 
		 
		 System.out.println(maxHeap.toString());
		 
	}
	
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
}
