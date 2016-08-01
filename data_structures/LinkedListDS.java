/* Joshua Villasenor
	masc0431
 */
package data_structures;
import java.util.*;

public class LinkedListDS<E> implements ListADT<E> {
	private int size;
	private Node <E> head, tail;
	private Node <E> pElement,cElement;
	
	public LinkedListDS(){
		size = 0; 
	}
	
	public void addFirst(E obj) {
		Node <E> newNode = new Node <E>(obj);
		
		if(isEmpty()){
			head = tail = newNode;
		}	
		else{
			newNode.next = head;
			head = newNode;
		}
		size++;
	}

	public void addLast(E o) {
		
		if(isEmpty()){
			addFirst(o);
		}
		else{
			Node <E> newNode = new Node <E>(o);
			tail.next = newNode;
			tail = newNode;
			size++;
		}
	}

	public E removeFirst() {
		if(head == null) return null;
		E tmp = head.data;
		head = head.next;
		return tmp;	
	}
	
	public E removeLast() {
		if(tail == null) return null;
		E temp = tail.data;
		remove(tail.data);
		return temp;
	}

	public E peekFirst() {
		if(head == null ) return null;
		return head.data;
	}
	
	public E peekLast() {
		if(tail == null) return null;
 		return tail.data;
	}

	
	public E find(E obj) {
		if(contains(obj))	
		return cElement.data;
		return null;
	}

	public boolean remove(E obj) {
		boolean rTF = contains(obj);

		if(cElement == null) return false;
		
		if(head == tail){
			if(rTF)
			makeEmpty();
			return rTF;
		}
		
		if(cElement == head){
			head = head.next;
		}
	
		if(cElement == tail){
			pElement.next = null;
			tail = pElement;
		}
		else{
			pElement.next = cElement.next;	
		}
		size--;
		return rTF;
	}
	
	public void makeEmpty() {
		head = null;
		tail = null;
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public boolean contains(E obj) {
		cElement = head;
		pElement = head;
    
		
		while(cElement != null && ((Comparable<E>)obj).compareTo(cElement.data)!=0){
			pElement = cElement;
			cElement = cElement.next;
		}
		if(cElement == null) return false;
		return cElement != null;
	}

	public boolean isEmpty() {
		return head==null;
	}

	public boolean isFull() {
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	
	@SuppressWarnings("hiding")
	private class Node<E>{
		E data;
		Node<E> next;
		
		public Node(E dat){
			data = dat;
			next = null;
		}
	}
	
	class IteratorHelper implements Iterator<E>{
		protected Node <E> iterIndex;

		public IteratorHelper(){
			iterIndex = head;
		}
		
		public boolean hasNext(){
		 return iterIndex != null;
		}
		
		public E next(){	
			if(!hasNext()) throw new NoSuchElementException();
			
			if(iterIndex == null) return iterIndex.data;
			
			E tmp = iterIndex.data;
			iterIndex = iterIndex.next;
			
			return tmp;	
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}