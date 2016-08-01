import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Iterator;

public class InventoryDriverGUI extends JFrame {
		private final int width = 575;
		private final int height = 870;
        private int SIZE;
        private JTextField SKU, rSKU, description, vendor, cost, retail;
		private JButton enterButton, randomButton, removeButton, clear;
		private JLabel prompt;
		private JPanel panelNorth;
		private JPanel panelSouth;
		private JPanel panelCenter;
		private Container contentPane;
		private JTextArea console;
		JScrollPane linkScrollPane;
		String startupText = "";
        ProductLookup startProgram;
        String [] rStockNumbers;
        int temp2 = 0;
    

		public static void main(String[] args){
			InventoryDriverGUI window = new InventoryDriverGUI();
			window.setVisible(true);
	    }

    public InventoryDriverGUI() {
        
            String temp1 = "";
        
            while(temp1.isEmpty()){
                temp1= JOptionPane.showInputDialog("Please enter size of inventory table: ");
            }
            temp2 = Integer.parseInt(temp1);
            SIZE = temp2 + 10;
            startProgram = new ProductLookup(SIZE);
			setSize(width, height);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			contentPane = getContentPane();
			contentPane.setLayout(new BorderLayout());
			
			panelNorth = new JPanel();
			panelCenter = new JPanel();
			panelSouth = new JPanel();
			
			buildTopPanel();
			contentPane.add(panelNorth, BorderLayout.NORTH);
			
			buildBottomPanel();
			contentPane.add(panelSouth, BorderLayout.SOUTH);
			
			buildCenterPanel();
			panelCenter.setBackground(Color.WHITE);
			contentPane.add(panelCenter, BorderLayout.CENTER);
			
			console.setText(startupText);
		}

		public void buildTopPanel() {
			panelNorth = new JPanel();
			panelNorth.setBackground(Color.white);
			panelNorth.setLayout(new GridLayout(0,1)); //FlowLayout
			
			prompt = new JLabel("SKU: ");
			panelNorth.add(prompt);
			SKU = new JTextField(10);
			panelNorth.add(SKU);
            
            prompt = new JLabel("Description: ");
			panelNorth.add(prompt);
            description = new JTextField(30);
            panelNorth.add(description);
            
            prompt = new JLabel("Vendor: ");
			panelNorth.add(prompt);
            vendor = new JTextField(15);
            panelNorth.add(vendor);
            
            prompt = new JLabel("Cost: ");
			panelNorth.add(prompt);
            cost = new JTextField(15);
            panelNorth.add(cost);
            
            prompt = new JLabel("Retail Number: ");
			panelNorth.add(prompt);
            retail = new JTextField(15);
            panelNorth.add(retail);
			
    
			enterButton = new JButton("Add Item");
			enterButton.setBackground(Color.WHITE);
			panelNorth.add(enterButton);
            enterButton.addActionListener(new ButtonListener());
            
            randomButton = new JButton("Random Entries");
			randomButton.setBackground(Color.WHITE);
			panelNorth.add(randomButton);
            randomButton.addActionListener(new RandomButtomListener());
            
            clear = new JButton("Clear");
            clear.setBackground(Color.WHITE);
            panelNorth.add(clear);
            clear.addActionListener(new ClearButtonListener());
            
            
            prompt = new JLabel("SKU To Remove: ");
			panelNorth.add(prompt);
			rSKU = new JTextField(10);
			panelNorth.add(rSKU);
            
            removeButton = new JButton("Remove");
			removeButton.setBackground(Color.WHITE);
			panelNorth.add(removeButton);
            removeButton.addActionListener(new RemoveButtonListener());
			
			
		}

		public void buildBottomPanel() {
			JLabel bottom = new JLabel("Created by Joshua Villasenor.");
			panelSouth.add(bottom);
		}
		

		public void buildCenterPanel() {
			console = new JTextArea();
			JPanel insidePanel = new JPanel();
			insidePanel.setBackground(Color.WHITE);
			
			JScrollPane insideScrollPane=new JScrollPane(this.console);
			insideScrollPane.setBackground(Color.WHITE);
			insideScrollPane.setBounds(30, 15, 560, 280);
			insideScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			insideScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	 
			insidePanel.setLayout(null);
			insidePanel.setPreferredSize(new Dimension(50+560, 15+280));
			insidePanel.add(insideScrollPane);
	 
			this.linkScrollPane = new JScrollPane(insidePanel);
	 
			this.getContentPane().add(this.linkScrollPane, BorderLayout.CENTER);
			
			panelCenter.add(insidePanel);
		}
		
    private class ButtonListener implements ActionListener {
        
			
			public void actionPerformed(ActionEvent e) {
                boolean isValidNum = false;
				String tempString1 = cost.getText();
                String tempString2 = retail.getText();
                float cost2 = 0, retail2 = 0;
                
                try{
                    cost2 = Float.parseFloat(tempString1);
                    retail2 = Float.parseFloat(tempString2);
                    StockItem tempItem = null;
                    tempItem = new StockItem(SKU.getText(), description.getText(), vendor.getText(), cost2, retail2);
                    startProgram.addItem(SKU.getText(), tempItem);
                    
                    Iterator val = startProgram.values();
                    console.setText("");
                    while(val.hasNext()){
                        console.append(val.next().toString() + "\n");
                    }
                    
                    SKU.setText("");
                    description.setText("");
                    vendor.setText("");
                    cost.setText("");
                    retail.setText("");
                }
                catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "Not a Valid number");
                }
                
                
				
			} 
		}
    private class RemoveButtonListener implements ActionListener{
     
        public void actionPerformed(ActionEvent e){
            startProgram.deleteItem(rSKU.getText());
            Iterator val = startProgram.values();
            console.setText("");
            while(val.hasNext()){
                console.append(val.next().toString() + "\n");
            }
            rSKU.setText("");
            
        }
    }
    private class ClearButtonListener implements ActionListener{
        int i = SIZE-1;
        public void actionPerformed(ActionEvent e){
            startProgram = new ProductLookup(SIZE);
            console.setText("");
            
        }
    }
    
    private class RandomButtomListener implements ActionListener {
        
        
        String [] rDescriptions;
        String [] rVendor;
        float [] rRetail;
        float [] rCost;
        StockItem tempItem;
        
        public RandomButtomListener(){
            rStockNumbers = new String[SIZE];
            rDescriptions = new String[SIZE];
            rVendor = new String[SIZE];
            rRetail = new float[SIZE];
            rCost = new float[SIZE];
            tempItem = null;
            
            
                    }
        public void actionPerformed(ActionEvent e) {
            rStockNumbers = getRandStockNumbers();
            for(int i = 0; i < SIZE; i++)
                rDescriptions[i] = getRandString(9);
            for(int i = 0; i < SIZE; i++)
                rVendor[i] = getRandString(5);
            for(int i = 0; i < SIZE; i++)
                rCost[i] = (float)Math.floor(((Math.random() * 100)));
            for(int i = 0; i < SIZE; i++)
                rRetail[i] = (float)Math.floor(((Math.random() * 100)));
            
            for(int i = 0; i < (SIZE); i++){
                tempItem = new StockItem(rStockNumbers[i], rDescriptions[i], rVendor[i], rCost[i], rRetail[i]);
                startProgram.addItem(rStockNumbers[i], tempItem);
            }

            Iterator val = startProgram.values();
            console.setText("");
            while(val.hasNext()){
                console.append(val.next().toString() + "\n");
            }

            
        }
        private String[] getRandStockNumbers() {
            
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
        public String getRandString(int length)  {
            String randString = "";
            byte b;
            
            for(int i=0; i < length; i++) {
                b = (byte) (26*Math.random()+97);
                randString += (char) b;
            }
            return randString;
        }
        
    }
 
}
		
