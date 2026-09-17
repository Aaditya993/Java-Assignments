import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// 1. Simple Book Class
class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
}

public class DigitalLibrary {
    public static void main(String[] args) {
        // 2. Collections Setup (As required by the rubric)
        ArrayList<Book> catalog = new ArrayList<>();
        HashMap<String, Integer> copyCounts = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        // 3. Menu-Driven Loop
        while (true) {
            System.out.println("\n===== Digital Library Catalog Manager =====");
            System.out.println("1.Add  2.Issue  3.Return  4.View Catalog  5.Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline character

            if (choice == 1) {
                System.out.print("Title: ");
                String title = sc.nextLine();
                System.out.print("Author: ");
                String author = sc.nextLine();
                System.out.print("Copies: ");
                int copies = sc.nextInt();
                sc.nextLine(); // Consume newline

                catalog.add(new Book(title, author));
                copyCounts.put(title, copies); // Map exact title to copy count
                System.out.println("Book added successfully.");

            } else if (choice == 2) {
                System.out.print("Enter title to issue: ");
                String searchTitle = sc.nextLine();
                issueBook(catalog, copyCounts, searchTitle);

            } else if (choice == 3) {
                System.out.print("Enter title to return: ");
                String searchTitle = sc.nextLine();
                returnBook(catalog, copyCounts, searchTitle);

            } else if (choice == 4) {
                System.out.println("\n--- Current Catalog ---");
                for (Book b : catalog) {
                    System.out.println("Title: " + b.getTitle() + " | Author: " + b.getAuthor() + " | Copies: " + copyCounts.get(b.getTitle()));
                }
            } else if (choice == 5) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }

    // Helper method for Issuing
    static void issueBook(ArrayList<Book> catalog, HashMap<String, Integer> copyCounts, String searchTitle) {
        for (Book b : catalog) {
            // 4. Case-insensitive and whitespace-resilient lookup
            if (b.getTitle().trim().equalsIgnoreCase(searchTitle.trim())) {
                int currentCopies = copyCounts.get(b.getTitle());
                
                if (currentCopies > 0) {
                    copyCounts.put(b.getTitle(), currentCopies - 1);
                    System.out.println("Book issued. Copies remaining: " + (currentCopies - 1));
                } else {
                    System.out.println("Currently unavailable."); // Exact string from requirements
                }
                return; // Exit method once book is found
            }
        }
        System.out.println("Book not found in the catalog.");
    }

    // Helper method for Returning
    static void returnBook(ArrayList<Book> catalog, HashMap<String, Integer> copyCounts, String searchTitle) {
        for (Book b : catalog) {
            if (b.getTitle().trim().equalsIgnoreCase(searchTitle.trim())) {
                int currentCopies = copyCounts.get(b.getTitle());
                copyCounts.put(b.getTitle(), currentCopies + 1);
                System.out.println("Book returned. Copies now: " + (currentCopies + 1));
                return; 
            }
        }
        System.out.println("Book not found in the catalog.");
    }
}