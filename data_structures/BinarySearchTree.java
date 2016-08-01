/* Joshua Villasenor
 masc0431
 */
package data_structures;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class BinarySearchTree<K,V> implements DictionaryADT<K,V>{
    private int size;
    private int modificationCounter;
    private Node<K,V> root;
    private Node<K,V> [] nodeArray, nArray;
    private K kHolder;
    private int j;
    
    public BinarySearchTree(){
        modificationCounter = 0;
        size = j = 0;
        root = null;
    }
	public boolean contains(K key) {
        if(isEmpty()) return false;
        boolean  temp = find(key, root)!=null;
		return temp;
	}
   
    
	public boolean insert(K k, V v) {
		if(k == null || v == null) return false;
        if(root == null)
            root = new Node<K,V>(k,v);
        else
            add(k,v,root,null,false);
        
        size++;
        modificationCounter++;
        return true;
    }
    
    private void add(K k, V v, Node<K,V> n, Node<K,V> parent, boolean wasLeft){
        if(n == null){
            if(wasLeft)
                parent.leftChild = new Node<K,V>(k,v);
            else
                parent.rightChild = new Node<K,V>(k,v);
        }
        else if(((Comparable<K>)k).compareTo(n.key) < 0)
            add(k,v,n.leftChild,n,true);
        else
            add(k,v,n.rightChild,n,false);
    }

	public boolean remove(K key) {
        
        if(!contains(key)) return false;
        if(size() == 1){
            clear();
            return true;
        }
        Node <K,V> temp = root;
        Node<K,V> newRoot = delete(find(key,temp), temp);
        root = newRoot;
		modificationCounter++;
        size--;
        return true;
	}
    private Node<K,V> lowestLeftNode( Node<K,V> n )
    {
        if( n == null )
            return null;
        else if( n.leftChild == null )
            return n;
        return lowestLeftNode( n.leftChild );
    }
    private Node<K,V> delete(Node<K,V> nToDelete, Node<K,V> iter){
        if( iter == null )
            return iter;
        
        if(((Comparable<K>)nToDelete.key).compareTo(iter.key) < 0 )
            iter.leftChild = delete(nToDelete, iter.leftChild);
        else if(((Comparable<K>)nToDelete.key).compareTo(iter.key) > 0 )
            iter.rightChild = delete(nToDelete, iter.rightChild);
        else if(iter.leftChild != null && iter.rightChild != null)
        {
            iter.key = lowestLeftNode( iter.rightChild ).key;
            iter.rightChild = delete( iter, iter.rightChild );
        }
        else{
            if(iter.leftChild != null )
                iter = iter.leftChild;
            else
                iter =iter.rightChild;
        }
        return iter;
    	
    }
    
    private void addNode(Node<K,V> insert, Node<K,V> n, Node<K,V> parent, boolean wasLeft){
        if(n == null){
            if(wasLeft) parent.leftChild = insert;
            else parent.rightChild = insert;
        }
        else if(((Comparable<K>)insert.key).compareTo(n.key)<0)
            addNode(insert,n.leftChild,n,true);
        else
            addNode(insert,n.rightChild,n,false);
    }

           
	public V getValue(K key) {
        if(isEmpty()) return null;
        return find(key, root).value;
	}
	 private Node<K,V> find(K key, Node<K,V> n){
	        if(n == null) return null;
	        if(((Comparable<K>)key).compareTo(n.key) < 0)
	            return find(key,n.leftChild);
	        if(((Comparable<K>) key).compareTo(n.key) > 0)
	            return find(key, n.rightChild);
	        return n;
	    }

	public K getKey(V value) {
		if(isEmpty()) return null;
        kHolder = null;
        findV(value, root);
   
        return kHolder;
	}
	private void findV(V value, Node<K,V> n){
        if(n != null){
            findV(value, n.leftChild);
            if(((Comparable<V>)value).compareTo(n.value) == 0)
                kHolder = n .key;
            findV(value, n.rightChild);
        }
    }

	public int size() {
		return size;
	}

	public boolean isFull() {
		return false;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		root = null;
        size = 0;
        modificationCounter++;
	}

	public Iterator<K> keys() {
		return new KeyIteratorHelper();
	}

	public Iterator<V> values() {
		return new ValueIteratorHelper();
	}
	
	abstract class IteratorHelper<E> implements Iterator<E>{
		protected Node<K,V> [] holder;
        protected int index, modCheck, i;
        
    	public IteratorHelper(){
            index = i = 0;
            modCheck = modificationCounter;
            nodeArray = new Node[size];
    		makeArray(root);
            holder = nodeArray;
        }
		
		public boolean hasNext() {
			if(isEmpty()) return false;
            if(modCheck != modificationCounter)
            	throw new ConcurrentModificationException();

			return index < size;
		}

		public abstract E next();

		public void remove() {
			throw new UnsupportedOperationException();
			
		}
        private void makeArray(Node <K,V> n){
            if(n != null){
                makeArray(n.leftChild);
                nodeArray[i++] = n;
                makeArray(n.rightChild);
            }
        }
	}
    public class KeyIteratorHelper extends IteratorHelper<K>{
    	
    	public KeyIteratorHelper(){
    		super();
    	}
		public K next() {
			if(!hasNext()) throw new NoSuchElementException();
			return holder[index++].key;
		}    
    }
    public class ValueIteratorHelper extends IteratorHelper<V>{
    	public ValueIteratorHelper(){
    		super();
    	}
		public V next() {
			if(!hasNext()) throw new NoSuchElementException();
			return holder[index++].value;
		}
    }
    public class Node<K,V>{
        private K key;
        private V value;
        private Node<K,V> leftChild;
        private Node<K,V> rightChild;
        
        public Node(K k, V v){
            key = k;
            value = v;
            leftChild = rightChild = null;
        }
    }
}