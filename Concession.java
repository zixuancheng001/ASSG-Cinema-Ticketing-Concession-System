import java.util.*;
import java.io.*;


class Item{
	//data fields
    public String name;
    public double price;
    public int stock;
    
    //constructor
    private int id;
    
    public Item(int id, String name, double price, int stock) {
    	this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    //getters
    public int getId(){
    	return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getStock() {
        return stock;
    }
    
    //setters
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    //display method
    // The format employees see
    public String toString() {
        return String.format("%-10s | Price: %6.2f | Stock: %d", name, price, stock);
    }
    
    // The format that customers see
    public String toCustomerString() {
        return String.format("%-12s | Price: RM%-6.2f", name, price);
    }
}


public class Concession {
    
    //data field
    private ConcessionManager system = new ConcessionManager();
    private Item[] cartItems = new Item[50];
	private int[] cartQty = new int[50];
	private int cartCount = 0;
	
	public Item[] getCartItems(){
		return cartItems;
	}
	
	public int[] getCartQty(){
		return cartQty;
	}
	
	public int getCartCount(){
		return cartCount;
	}

    //constructor
    public Concession() {
        File file = new File("items.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("\nThe Item Is Empty, Please Add Item First...");
            } catch (IOException e) {
                System.out.println("Error creating file.");
            }
        }
        system.loadFromFile();
    }
    
    
    public void addToCart(Item item, int qty) {
	    cartItems[cartCount] = item;
	    cartQty[cartCount] = qty;
	    cartCount++;
	}
    
    //customer buy items and generate receipt
    public void customer(Payment payment) {
        while (true) {
        	System.out.println("Enter Item ID:");
            system.displayItems();
            

            String cInput = CinemaSystem.Input.scanner.nextLine();
            if (!cInput.matches("\\d+")) {
                System.out.println("\nInvalid input! Please enter a number.");
                continue;
            }
            int id = Integer.parseInt(cInput);
            
            if (id == 0) return;

            System.out.print("Quantity: ");
            String qInput = CinemaSystem.Input.scanner.nextLine();
            if (!qInput.matches("\\d+")) {
                System.out.println("\nInvalid input! Please enter a number.");
                continue;
            }
            int q = Integer.parseInt(qInput);

            Item item = system.getItemById(id);
            if (item == null) {
            	System.out.println("\n>>> Invalid Item ID.");
            	continue;
            }
            
            system.purchaseById(id, q);
            addToCart(item, q);
            

            System.out.println("\n+------------------------------------------------------------------------+");
            System.out.println("|                           Add more items?                              |");
            System.out.println("+------------------------------------------------------------------------+");
            System.out.println("|                          [1] Yes    [2] No                             |");
            System.out.println("+------------------------------------------------------------------------+");
            System.out.print("\nEnter Your Choice: ");
            String more = CinemaSystem.Input.scanner.nextLine();
            if (!more.equals("1")) return;
        }
    }
    
    //staff manage items (add/delete)
    public void staff() {
        system.displayInventory();
        while (true) {
            System.out.println("\n+------------------------------------------------------------------------+");
            System.out.println("|        1. Add        |        2. Delete        |        3. Back        |");
            System.out.println("+------------------------------------------------------------------------+");
            System.out.print("Enter Your Choice: ");

            String sInput = CinemaSystem.Input.scanner.nextLine();
            if (!sInput.matches("\\d+")) {
                System.out.println("\nInvalid input! Please enter a number.");
                continue;
            }
            int s = Integer.parseInt(sInput);
            
            //add
            if (s == 1) {
                System.out.println("\n+------------------------------Add Item----------------------------------+");
                System.out.print("Create Item Name : ");
                String n = CinemaSystem.Input.scanner.nextLine();

                System.out.print("Set Item Price   : ");
                String pInput = CinemaSystem.Input.scanner.nextLine();
                
                if (!pInput.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("Invalid price!");
                    continue;
                }
                double p = Double.parseDouble(pInput);
                

                System.out.print("Enter Item Stock : ");
                String kInput = CinemaSystem.Input.scanner.nextLine();
                
                if (!kInput.matches("\\d+")) {
                    System.out.println("Invalid stock!");
                    continue;
                }
                int k = Integer.parseInt(kInput);

                system.addItem(n, p, k);
                system.saveToFile();
                System.out.println("Item added successfully.");
                system.displayInventory();
            
            //delete
            } else if (s == 2) {
                System.out.println("\n+-----------------------------Delete Item--------------------------------+");
                System.out.print("\nEnter Item ID to Delete : ");
                String input = CinemaSystem.Input.scanner.nextLine();
                
                if (!input.matches("\\d+")) {
                    System.out.println("\nInvalid input! Please enter a number.");
                    continue;
                }
                
                int id = Integer.parseInt(input);
                
                system.deleteItemById(id);
                system.saveToFile();
                system.displayInventory();
            
                
            //exit
            }else if (s == 3) {
                break;
            }
        }
        return;
        }

    class ConcessionManager {
    	//data field
        private Item[] items = new Item[100];
        private int size = 0;
        private final String FILE_NAME = "items.txt";
        private int nextId = 1;
        
        //load data from file
        public void loadFromFile() {
            try {
                size = 0;
                File file = new File(FILE_NAME);
                if (!file.exists()) return;

                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int stock = Integer.parseInt(parts[3]);
                    if (size >= items.length) {
                        System.out.println("Inventory full!");
                        return;
                    }
                    items[size++] = new Item(id, name, price, stock);
                    
                    if (id >= nextId) nextId = id + 1;
                }
                fileScanner.close();
            } catch (Exception e) {
                System.out.println("Error loading file.");
            }
        }
        
        //save data to file
        public void saveToFile() {
            try {
                PrintWriter writer = new PrintWriter(FILE_NAME);
                for (int i = 0; i < size; i++) {
                    Item item = items[i];
                    writer.println(item.getId() + "," + item.name + "," + item.price + "," + item.stock);
                }
                writer.close();
            } catch (Exception e) {
                System.out.println("Error saving file.");
            }
        }

        public void addItem(String name, double price, int stock) {
            if (size >= items.length) {
                System.out.println("Inventory full!");
                return;
            }
            items[size++] = new Item(nextId++,name, price, stock);
            
        }

        public void deleteItemById(int id) {
        	for (int i = 0; i < size; i++) {
                if (items[i].getId() == id) {

                    for (int j = i; j < size - 1; j++) {
                        items[j] = items[j + 1];
                    }

                    items[size - 1] = null;
                    size--;

                    System.out.println("\n>>> Item ID '" + id + "' deleted.");
                    return;
                 }
             }
             System.out.println("\n>>> Item not found.");
        
        }
        
        //display menu (CUSTOMER VIEW)
        public void displayItems() {
            System.out.println("\n----------------------------------- MENU -----------------------------------");
            if (size == 0) {
                System.out.println("Menu is empty!!!");
            } else {
                for (int i = 0; i < size; i++) {
                	System.out.println(items[i].getId() + ". " + items[i].toCustomerString());
                }
            }
            System.out.println("----------------------------------------------------------------------------");
        }
        
        
        public Item getItemById(int id) {
            for (int i = 0; i < size; i++) {
                if (items[i].getId() == id) {
                    return items[i];
                } 
            }
            return null;
        }
        
        //display menu (STAFF VIEW)
        public void displayInventory() {
            System.out.println("\n-------------------------- INTERNAL INVENTORY --------------------------");
            if (size == 0) {
                System.out.println("Inventory is empty....");
            } else {
                for (int i = 0; i < size; i++) {
                    System.out.println("ID: " + items[i].getId() + " | " + items[i]);
                }
            }
            System.out.println("--------------------------------------------------------------------------");
        }
       
        
        //purchase item
        public void purchaseById(int id, int quantity) {
        	Item item = getItemById(id);

            if (item == null) {
                System.out.println("\n>>> Invalid selection.");
                return;
            }
            
            if (quantity <= 0) {
                System.out.println("\n>>> Invalid quantity!");
                return;
            }


            if (item.stock == 0) {
                System.out.println("\n>>> Sorry, '" + item.name + "' is currently SOLD OUT.");
            } else if (item.stock >= quantity) {
                double total = item.price * quantity;
                item.stock -= quantity;
                System.out.println("\n================================ RECEIPT =================================");
                System.out.println("Item       :      " + item.name);
                System.out.println("Quantity   :      " + quantity);
                System.out.println("Total      :    RM" + String.format("%.2f", total));
                System.out.println("==========================================================================");
                System.out.println();
                System.out.println("Click Enter To Continue...");
                CinemaSystem.Input.scanner.nextLine();
                saveToFile();
            } else {
                System.out.println("\n>>> ERROR: Insufficient stock!");
                System.out.println("\n>>> Sorry, we only have [" + item.stock + "] " + item.name + " available.");
            }
        }
    }
    }