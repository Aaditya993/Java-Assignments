package Assignment_11;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class ProductInventoryManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Set Up the Collections
        HashSet<String> categories = new HashSet<>();
        TreeSet<Integer> productIds = new TreeSet<>();
        TreeMap<Integer, String> productCatalog = new TreeMap<>();

        // 2. Build the Menu
        while (true) {
            System.out.println("\n===== Product Inventory Management System =====");
            System.out.println("1. Add Product Category");
            System.out.println("2. Add Product ID");
            System.out.println("3. Add Product to Catalog");
            System.out.println("4. Display All Products");
            System.out.println("5. Find Nearest Product ID");
            System.out.println("6. Display Products in ID Range");
            System.out.println("7. Remove Product");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // 3 & 4. Implement Core Operations & Handle Exceptions
            switch (choice) {
                case 1:
                    System.out.print("Enter category name: ");
                    String category = scanner.nextLine();
                    if (categories.add(category)) {
                        System.out.println("Category added: " + category);
                    } else {
                        System.out.println("Category already exists (HashSet ignored duplicate).");
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter product ID: ");
                    if (scanner.hasNextInt()) {
                        int pId = scanner.nextInt();
                        if (productIds.add(pId)) {
                            System.out.println("Product ID added successfully.");
                            System.out.print("Sorted IDs in TreeSet: ");
                            Iterator<Integer> it = productIds.iterator();
                            while (it.hasNext()) {
                                System.out.print(it.next() + (it.hasNext() ? ", " : ""));
                            }
                            System.out.println();
                        } else {
                            System.out.println("Product ID already exists.");
                        }
                    } else {
                        System.out.println("Invalid ID.");
                        scanner.next();
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter product ID: ");
                    if (scanner.hasNextInt()) {
                        int id = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.print("Enter product name and price: ");
                        String details = scanner.nextLine();
                        productCatalog.put(id, details);
                        productIds.add(id); // Keep TreeSet in sync just in case
                        System.out.println("Product added to catalog.");
                    } else {
                        System.out.println("Invalid ID format.");
                        scanner.next();
                    }
                    break;
                    
                case 4:
                    System.out.println("\n===== All Products (Sorted by ID) =====");
                    if (productCatalog.isEmpty()) {
                        System.out.println("Catalog is empty.");
                    } else {
                        for (Map.Entry<Integer, String> entry : productCatalog.entrySet()) {
                            System.out.println(entry.getKey() + " -> " + entry.getValue());
                        }
                    }
                    break;
                    
                case 5:
                    System.out.print("Enter ID to search nearest: ");
                    if (scanner.hasNextInt()) {
                        int searchId = scanner.nextInt();
                        try {
                            if (productCatalog.isEmpty()) {
                                // Forcing exception as per requirements to prevent looking up empty collections
                                throw new NoSuchElementException("The catalog is empty. Cannot perform nearest search.");
                            }
                            Integer floor = productCatalog.floorKey(searchId);
                            Integer ceiling = productCatalog.ceilingKey(searchId);
                            
                            System.out.println("Floor ID  : " + (floor != null ? floor : "None found"));
                            System.out.println("Ceiling ID: " + (ceiling != null ? ceiling : "None found"));
                        } catch (NoSuchElementException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid input.");
                        scanner.next();
                    }
                    break;
                    
                case 6:
                    try {
                        if (productCatalog.isEmpty()) {
                            throw new NoSuchElementException("The catalog is empty.");
                        }
                        System.out.print("Enter range start ID (from): ");
                        int fromId = scanner.nextInt();
                        System.out.print("Enter range end ID (to): ");
                        int toId = scanner.nextInt();
                        
                        System.out.println("\n===== Products in Range =====");
                        // Using subMap (+1 to make the 'to' inclusive for the user query)
                        SortedMap<Integer, String> subMap = productCatalog.subMap(fromId, toId + 1);
                        if (subMap.isEmpty()) {
                            System.out.println("No products found in this range.");
                        } else {
                            for (Map.Entry<Integer, String> entry : subMap.entrySet()) {
                                System.out.println(entry.getKey() + " -> " + entry.getValue());
                            }
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Invalid input for range.");
                        scanner.nextLine();
                    }
                    break;
                    
                case 7:
                    System.out.print("Enter Product ID to remove: ");
                    if (scanner.hasNextInt()) {
                        int removeId = scanner.nextInt();
                        if (productCatalog.containsKey(removeId)) {
                            productCatalog.remove(removeId);
                            productIds.remove(removeId);
                            System.out.println("Product removed successfully.");
                        } else {
                            System.out.println("Product ID not found.");
                        }
                    } else {
                        System.out.println("Invalid ID.");
                        scanner.next();
                    }
                    break;
                    
                case 8:
                    System.out.println("Exiting the manager. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid option. Please choose between 1 and 8.");
            }
        }
    }
}