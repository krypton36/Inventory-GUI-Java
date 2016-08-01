/* Joshua Villasenor
 masc0431
 */
package data_structures;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BalancedTree<K,V> implements DictionaryADT<K,V> {
    TreeMap<K,V> rbTree;
    
    public BalancedTree()
    {
        rbTree= new TreeMap<K, V>();
    }

    public boolean contains(K key) {
       return rbTree.containsKey(key);
    }

    public boolean insert(K key, V value) {
        rbTree.put(key, value);
        return true;
    }

    public boolean remove(K key) {
        V remove=rbTree.remove(key);
        return remove!=null;
    }

    public V getValue(K key) {
        return rbTree.get(key);
    }

    public K getKey(V value) {
        for (Entry<K, V> e : rbTree.entrySet()) {
            if (((Comparable<V>)value).compareTo(e.getValue()) == 0) {
                return e.getKey();
            }
        }
        return null;
    }

    public int size() {
        return rbTree.size();
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public void clear() {
        rbTree.clear();
    }

    public Iterator<K> keys() {
       return rbTree.keySet().iterator();
    }

    public Iterator<V> values() {
        return rbTree.values().iterator();
    }
}