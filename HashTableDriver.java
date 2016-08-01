import data_structures.*;
import java.util.Scanner;
import java.util.Iterator;

public class HashTableDriver{
    Hashtable <String, String> hashTable;
    private static final int SIZE = 1000;
    private static int [] array = new int[SIZE];
    private static String [] s, s2;
    
    public HashTableDriver(){
        s2 = new String [100];
        hashTable = new Hashtable <String, String>(100);
    }
    public void printKey(){
        int i = 0;
        System.out.print("<-(_K_E_Y_)->\n");
        for(; i< 20; i++)
            System.out.print(s[i]+ " ");
        System.out.println(" ");
        for(; i< 40; i++)
            System.out.print(s[i]+ " ");
        System.out.println(" ");
        for(; i< 60; i++)
            System.out.print(s[i]+ " ");
        System.out.println(" ");
        for(; i< 80; i++)
            System.out.print(s[i]+ " ");
        System.out.println(" ");
        for(; i< 100; i++)
            System.out.print(s[i]+ " ");
        System.out.print("\n<-(_K_E_Y_)->\n");
    }
    public void printValue(){
        int i = 0;
        System.out.print("\n<-(_V_A_L_U_E_)->\n");
        for(; i< 100; i++){
            if(i%10 == 0 && i != 0)
                System.out.println(" ");
            System.out.print(s2[i]+ " ");
        }
        System.out.print("\n<-(_V_A_L_U_E_)->\n");
    }

    public void TestTable(){
        String a;
        String name;
        Scanner aIn = new Scanner(System.in);
        Scanner bIn = new Scanner(System.in);
        
        s = getRandStockNumbers();
        
        for(int i = 0; i < 100; i++){
            s2[i] = getRandString(10);
            hashTable.insert(s[i], s2[i] );
        }
        System.out.println(hashTable.size());
    }
    public void testInteratorKeys(){
        int i = 0 ;
        Iterator items = hashTable.keys();
        System.out.print("\n<-_I_T_E_R_A_T_O_R_->\n");
        while(items.hasNext()){
            if((i%20) == 0 && i != 0)
                System.out.println(" ");
            System.out.print(items.next()+ " ");
            i++;
            if( i > 100 ) break;
        }
        System.out.print("\n<-_I_T_E_R_A_T_O_R_->\n");
        if(i > 100) System.out.println("FAILED");
    }
    
    public void testInteratorValues(){
        int i = 0 ;
        Iterator items = hashTable.values();
        System.out.print("\n<-_I_T_E_R_A_T_O_R_->\n");
        while(items.hasNext()){
            if((i%10) == 0 && i != 0)
                System.out.println(" ");
            System.out.print(items.next()+ " ");
            i++;
            if( i > 100 ) break;
        }
        System.out.print("\n<-_I_T_E_R_A_T_O_R_->\n");
        if(i > 100) System.out.println("FAILED");
    }
    
    public void checkContains(){
        System.out.println(hashTable.contains(s[1]));
    }
  
    public void testGetValue(){
        System.out.println(" ");
        for(int i = 0; i < 100; i++){
            if(i%10 == 0 && i !=0)
                System.out.println(" ");
            System.out.print(hashTable.getValue(s[i]) + " ");
        }
        System.out.println(" ");
    }
    public void testSize(){
        System.out.println(hashTable.size());
    }
    public void testGetKey(){
        System.out.println(" ");
        for(int i = 0; i < 100; i++){
            if(i%20 == 0 && i !=0)
                System.out.println(" ");
            System.out.print(hashTable.getKey(s2[i]) + " ");
        }
        System.out.println(" ");
    }
    public static String getRandString(int length)  {
        String randString = "";
        byte b;
        
        for(int i=0; i < length; i++) {
            b = (byte) (26*Math.random()+97);
            randString += (char) b;
        }
        return randString;
    }
    
    private static String[] getRandStockNumbers() {
        
        String [] str = new String[SIZE];
        String temp = "";
        int where=0;
        byte [] b = {0x41,0x41,0x41,0x30,0x30,0x30};
        
        for(int i=0; i < 10; i++)
            for(int j=0; j < 10; j+=(int)5*Math.random()+1)
                for(int k=0; k < 10; k+=(int)5*Math.random()+1)
                    for(int l=0; l < 26; l+=(int)2*Math.random()+1)
                        for(int m=0; m < 26; m+=(int) 2*Math.random()+1)
                            for(int n=0; n < 26; n++) {
                                if(where >= SIZE) break;
                                str[where++] = ""+
                                ((char)(b[0]+n)) +
                                ((char)(b[1]+m)) +
                                ((char)(b[2]+l)) +
                                ((char)(b[3]+k)) +
                                ((char)(b[4]+j)) +
                                ((char)(b[5]+i));
                            }
        return str;
    }
    
    public static void main(String[] args){
        HashTableDriver runTest = new HashTableDriver();
        runTest.TestTable();
        runTest.checkContains();
        runTest.testGetValue();
        runTest.testSize();
        runTest.testGetKey();
        runTest.testInteratorKeys();
        runTest.testInteratorValues();
        System.out.println(" ");
        System.out.println(" ");
        runTest.printValue();
        runTest.printKey();
    }

}