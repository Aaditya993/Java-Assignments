package Assignment_12;


import java.util.Scanner;

// 1. Set Up the Shared Ticket Pool
class TicketPool {
    private int availableTickets;

    public TicketPool(int totalTickets) {
        this.availableTickets = totalTickets;
    }

    // synchronized method to prevent concurrent access issues (overselling)
    public synchronized boolean sellTicket(String counterName) {
        if (availableTickets > 0) {
            availableTickets--;
            System.out.println(counterName + " sold ticket. Remaining: " + availableTickets);
            return true;
        }
        return false;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void resetPool(int tickets) {
        this.availableTickets = tickets;
        System.out.println("Ticket pool reset to " + tickets + " tickets.");
    }
}

// 3. Thread class implementation
class CounterThread extends Thread {
    private TicketPool pool;

    public CounterThread(String name, TicketPool pool) {
        super(name);
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            // Repeatedly sell tickets until the pool is empty
            while (pool.sellTicket(getName())) {
                Thread.sleep(500); // 4. Simulate time taken to sell a ticket
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted: " + e.getMessage());
        }
    }
}

// 3. Runnable interface implementation
class CounterRunnable implements Runnable {
    private TicketPool pool;

    public CounterRunnable(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            // Thread.currentThread().getName() gets the name of the thread running this task
            while (pool.sellTicket(Thread.currentThread().getName())) {
                Thread.sleep(500); // 4. Simulate time taken to sell a ticket
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted: " + e.getMessage());
        }
    }
}

public class MultiCounterTicketBookingSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketPool sharedPool = new TicketPool(10); // Initializing with 10 tickets
        
        CounterThread counterA = null;
        Thread counterB = null;

        // 2. Build the Menu
        while (true) {
            System.out.println("\n===== Multi-Counter Ticket Booking Simulator =====");
            System.out.println("1. Start Counter using Thread Class");
            System.out.println("2. Start Counter using Runnable Interface");
            System.out.println("3. Set Thread Priority");
            System.out.println("4. Display Thread Status");
            System.out.println("5. Display Available Tickets");
            System.out.println("6. Wait for All Counters to Finish");
            System.out.println("7. Reset Ticket Pool");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
                continue;
            }
            
            int choice = scanner.nextInt();
            System.out.println();
            
            switch (choice) {
                case 1:
                    if (counterA == null || !counterA.isAlive()) {
                        counterA = new CounterThread("Counter-A", sharedPool);
                        System.out.println("Starting Counter-A (Thread class)...");
                        counterA.start();
                    } else {
                        System.out.println("Counter-A is already running.");
                    }
                    break;
                    
                case 2:
                    if (counterB == null || !counterB.isAlive()) {
                        counterB = new Thread(new CounterRunnable(sharedPool), "Counter-B");
                        System.out.println("Starting Counter-B (Runnable interface)...");
                        counterB.start();
                    } else {
                        System.out.println("Counter-B is already running.");
                    }
                    break;
                    
                case 3:
                    // Demonstrating priority setting before or during thread execution
                    if (counterA != null) {
                        counterA.setPriority(8); // Setting higher priority
                        System.out.println("Counter-A priority set to 8.");
                    } else {
                        System.out.println("Counter-A is not initialized yet.");
                    }
                    
                    if (counterB != null) {
                        counterB.setPriority(5); // Setting normal priority
                        System.out.println("Counter-B priority set to 5.");
                    } else {
                        System.out.println("Counter-B is not initialized yet.");
                    }
                    break;
                    
                case 4:
                    System.out.println("===== Thread Status =====");
                    if (counterA != null) {
                        System.out.println("Name: " + counterA.getName() + " | Priority: " + counterA.getPriority() + " | Alive: " + counterA.isAlive());
                    } else {
                        System.out.println("Counter-A has not been created.");
                    }
                    
                    if (counterB != null) {
                        System.out.println("Name: " + counterB.getName() + " | Priority: " + counterB.getPriority() + " | Alive: " + counterB.isAlive());
                    } else {
                        System.out.println("Counter-B has not been created.");
                    }
                    break;
                    
                case 5:
                    System.out.println("Available tickets: " + sharedPool.getAvailableTickets());
                    break;
                    
                case 6:
                    // Using join() to wait for threads to finish, safely wrapping in try-catch
                    try {
                        System.out.println("Waiting for all counters to finish (join)...");
                        if (counterA != null && counterA.isAlive()) {
                            counterA.join();
                        }
                        if (counterB != null && counterB.isAlive()) {
                            counterB.join();
                        }
                        System.out.println("All counters finished selling.");
                        System.out.println("Final available tickets: " + sharedPool.getAvailableTickets());
                    } catch (InterruptedException e) {
                        System.out.println("Main thread interrupted while waiting: " + e.getMessage());
                    }
                    break;
                    
                case 7:
                    sharedPool.resetPool(10);
                    break;
                    
                case 8:
                    System.out.println("Exiting the simulator. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid option. Please choose between 1 and 8.");
            }
        }
    }
}