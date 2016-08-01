/* Joshua Villasenor
	masc0431
 */
package data_structures;

import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class Hashtable<K, V> implements DictionaryADT<K,V>{
	private int size, tableSize, maxTableSize;
    private int modificationCounter;
	private LinkedListDS <Wrapper<K,V>>[] dictionaryList;
	
    public Hashtable(int max){
    	size = 0;
        modificationCounter = 0;
    	maxTableSize = max;
    	tableSize = (int)(max * 1.3);
    	dictionaryList = new LinkedListDS[tableSize];
    	for(int i =0; i < tableSize; i++)
            dictionaryList[i] = new LinkedListDS<Wrapper<K,V>>();
    }
    
	public boolean contains(K key) {
        Wrapper<K,V> temp = new Wrapper<K,V> (key, null);
		return dictionaryList[getIndex(key)].contains(temp);
	}
    
	public boolean insert(K key, V value) {
        if(isFull()) return false;
        Wrapper<K,V> newWrapper = new Wrapper<K,V>(key, value);
		dictionaryList[getIndex(key)].addFirst(newWrapper);
        modificationCounter++;
        size++;
		return true;
	}
    private int getIndex(K key){
        return (key.hashCode() & 0x7FFFFFFF)% tableSize;
    }

	public boolean remove(K key) {
        if(!contains(key)) return false;
        Wrapper<K,V> temp = new Wrapper<K,V>(key, null);
        dictionaryList[getIndex(key)].remove(temp);
		modificationCounter++;
        size--;
		return true;
	}

	public V getValue(K key) {
        Wrapper<K,V> temp2, temp = new Wrapper<K,V>(key, null);
        temp2 = dictionaryList[getIndex(key)].find(temp);
        if(temp2 == null) return null;
		return temp2.value;
	}
	 
	public K getKey(V value) {
        K temp = null;
        for(int i  = 0; i < tableSize; i++)
            if(!dictionaryList[i].isEmpty())
                for(Wrapper<K,V> w : dictionaryList[i]){
                    if(((Comparable<V>)value).compareTo(w.value) == 0){
                        temp = w.key;
                        break;
                    }
                }
		return temp;
	}
	 
	public int size() {
        return size;
	}
	 
	public boolean isFull() {
		return size >= maxTableSize;
	}
	 
	public boolean isEmpty() {
		return size == 0;
	}
	 
	public void clear() {
        for(int i = 0; i < tableSize; i++){
            dictionaryList[i].makeEmpty();
        }
        size = 0;
		modificationCounter++;
	}
	 
	public Iterator <K> keys() {
		return new KeyIteratorHelper();
	}
	 
	public Iterator <V> values() {
		return new ValueIteratorHelper();
	}
   
    abstract class IteratorHelper<E> implements Iterator<E>{
        protected int index,j, modCheck;
        protected Wrapper<K,V>[] wrapperHolder;
        
        public IteratorHelper(){
            index = j = 0;
            modCheck = modificationCounter;
            wrapperHolder = new Wrapper[size];
            
            for(int i = 0 ; i < tableSize; i++)
                for(Wrapper<K,V> w : dictionaryList[i])
                    wrapperHolder[j++] = w;
            shellSort(wrapperHolder);
        }
        public boolean hasNext(){
            if(isEmpty()) return false;
            if(modCheck != modificationCounter)
            	throw new ConcurrentModificationException();
            return index < size;
        }
        public abstract E next();
        public void remove(){
            throw new UnsupportedOperationException();
        }
        
        private void shellSort(Wrapper<K,V> [] arr){
            int in, out, max = size;
            Wrapper <K,V> temp;
            int h = 1;
            while ( h <= size/3)
                h = h*3+1;
            while (h > 0){
                for(out = h; out < size; out++){
                    temp = arr[out];
                    in = out;
                    while(in > h - 1 && arr[in-h].compareTo(temp) >= 0){
                        arr[in] = arr[in-h];
                        in -=h;
                    }
                    arr[in] = temp;
                }
                h=(h-1)/3;
            }
        } 
    }
    
    public class KeyIteratorHelper extends IteratorHelper<K>{       
        public KeyIteratorHelper(){
          super();
        }
      
        public K next(){
            if(!hasNext()) throw new NoSuchElementException();  
            return wrapperHolder[index++].key;
        }
    }
    
    public class ValueIteratorHelper extends IteratorHelper<V>{
        public ValueIteratorHelper(){
           super();
        }
        public V next(){
            if(!hasNext()) throw new NoSuchElementException();    
            return wrapperHolder[index++].value;
        } 
    }
	private class Wrapper<K,V> implements Comparable<Wrapper<K,V>>{
		K key;
		V value;
		
		public Wrapper(K inKey, V inValue ){
			key = inKey;
			value = inValue;
		}
		
		public int compareTo(Wrapper<K, V> otherWrapper) {
			return ((Comparable<K>) key).compareTo(otherWrapper.key);
		}	
	}
}