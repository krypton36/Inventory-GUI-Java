/* Joshua Villasenor
 masc0431
 */
import data_structures.*;
import java.util.Iterator;

public class ProductLookup {
    Hashtable<String, StockItem> inventory;
    
    
    // Constructor.  There is no argument-less constructor, or default size
    public ProductLookup(int maxSize){
    	inventory = new Hashtable(maxSize);
    }
    
    // Adds a new StockItem to the dictionary
    public void addItem(String SKU, StockItem item){
    	inventory.insert(SKU, item);
    }
    
    // Returns the StockItem associated with the given PhoneNumber, if it is
    // in the PhoneBook, null if it is not.
    public StockItem getItem(String SKU){
    	return inventory.getValue(SKU);
    }
    
    // Returns the retail price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getRetail(String SKU){
        StockItem temp = inventory.getValue(SKU);
        if(temp == null) return (float)-.01;
    	return temp.getRetail();
    }
    
    // Returns the cost price associated with the given SKU value.
    // -.01 if the item is not in the dictionary
    public float getCost(String SKU){
        StockItem temp = inventory.getValue(SKU);
        if(temp == null) return (float)-.01;
    	return temp.getCost();
    }
    
    // Returns the description of the item, null if not in the dictionary.
    public String getDescription(String SKU){
        StockItem temp = inventory.getValue(SKU);
        if(temp == null) return null;
    	return temp.getDescription();
    }
    
    // Deletes the StockItem associated with the SKU if it is
    // in the PhoneBook.  Returns true if it was found and
    // deleted, otherwise false.
    public boolean deleteItem(String SKU){
    	return inventory.remove(SKU);
    }
    
    // Prints a directory of all StockItems with their associated
    // price, in sorted order (ordered by SKU).
    public void printAll(){
        Iterator all = keys();
        while(all.hasNext()){
            System.out.println(all.next().toString());
        }
    	
    }
    
    // Prints a directory of all StockItems from the given vendor,
    // in sorted order (ordered by SKU).
    public void print(String vendor){
        Iterator allValues = values();
        while(allValues.hasNext()){
            System.out.println(allValues.next());
        }

    }
    
    // An iterator of the SKU keys.
    public Iterator<String> keys() {
		return inventory.keys();
	}
    
    // An iterator of the StockItem values.
    public Iterator<StockItem> values() {
		return inventory.values();
	}
}