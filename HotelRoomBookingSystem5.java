import java.util.Scanner;

class Room {
    // Private data members for encapsulation
    private int roomNumber;
    private String roomType;
    private String customerName;
    private int numberOfDays;
    private double pricePerDay;
    private boolean bookingStatus;
    
    // Static variable to track total bookings
    private static int totalBookings = 0;

    // Parameterized constructor using 'this' keyword
    public Room(int roomNumber, String roomType, double pricePerDay) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerDay = pricePerDay;
        this.customerName = "None";
        this.numberOfDays = 0;
        this.bookingStatus = false;
    }

    // Getters and Setters
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public int getNumberOfDays() { return numberOfDays; }
    public void setNumberOfDays(int numberOfDays) { this.numberOfDays = numberOfDays; }
    
    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }
    
    public boolean isBookingStatus() { return bookingStatus; }
    public void setBookingStatus(boolean bookingStatus) { this.bookingStatus = bookingStatus; }

    // Static method to get total bookings
    public static int getTotalBookings() { return totalBookings; }

    // Method to book a room
    public void bookRoom(String customerName, int numberOfDays) {
        this.customerName = customerName;
        this.numberOfDays = numberOfDays;
        this.bookingStatus = true;
        totalBookings++;
        
        System.out.println("\n===== Booking Confirmation =====");
        System.out.println("Room Number   : " + this.roomNumber);
        System.out.println("Room Type     : " + this.roomType);
        System.out.println("Customer Name : " + this.customerName);
        System.out.println("Number of Days: " + this.numberOfDays);
        System.out.println("Price Per Day : ₹" + (int)this.pricePerDay);
        System.out.println("\nTotal Bill    : ₹" + (int)calculateBill());
        System.out.println("\nRoom booked successfully.");
    }

    // Method to display room details
    public void displayRoomDetails() {
        System.out.println("\nRoom Number   : " + this.roomNumber);
        System.out.println("Room Type     : " + this.roomType);
        System.out.println("Customer Name : " + (this.bookingStatus ? this.customerName : "N/A"));
        System.out.println("Number of Days: " + this.numberOfDays);
        System.out.println("Price Per Day : ₹" + (int)this.pricePerDay);
        System.out.println("Status        : " + (this.bookingStatus ? "Booked" : "Available"));
    }

    // Method to calculate bill
    public double calculateBill() {
        return this.numberOfDays * this.pricePerDay;
    }

    // Method to cancel booking
    public void cancelBooking() {
        this.customerName = "None";
        this.numberOfDays = 0;
        this.bookingStatus = false;
        totalBookings--;
        System.out.println("Booking cancelled successfully. Room is now available.");
    }
}

public class HotelRoomBookingSystem5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Creating appropriate objects for different rooms
        Room[] rooms = {
            new Room(101, "Standard", 2000),
            new Room(201, "Deluxe", 3500),
            new Room(301, "Premium", 5000)
        };

        menuLoop:
        while (true) {
            System.out.println("\n===== Hotel Room Booking System =====");
            System.out.println("1. Book Room");
            System.out.println("2. Display Room Details");
            System.out.println("3. Calculate Bill");
            System.out.println("4. Check Room Status");
            System.out.println("5. Cancel Booking");
            System.out.println("6. Display Total Bookings");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    Room availableRoom = null;
                    for (Room r : rooms) {
                        if (!r.isBookingStatus()) {
                            availableRoom = r;
                            break;
                        }
                    }
                    if (availableRoom != null) {
                        System.out.print("Enter Customer Name: ");
                        scanner.nextLine(); // Consume newline
                        String name = scanner.nextLine();
                        System.out.print("Enter Number of Days: ");
                        if (scanner.hasNextInt()) {
                            int days = scanner.nextInt();
                            availableRoom.bookRoom(name, days);
                        } else {
                            System.out.println("Invalid days.");
                            scanner.next();
                        }
                    } else {
                        System.out.println("Sorry, no rooms are currently available.");
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter Room Number to display details (101, 201, 301): ");
                    int detailsRoom = scanner.nextInt();
                    boolean foundDetails = false;
                    for (Room r : rooms) {
                        if (r.getRoomNumber() == detailsRoom) {
                            r.displayRoomDetails();
                            foundDetails = true;
                        }
                    }
                    if (!foundDetails) System.out.println("Room not found.");
                    break;
                    
                case 3:
                    System.out.print("Enter Room Number to calculate bill (101, 201, 301): ");
                    int billRoom = scanner.nextInt();
                    boolean foundBill = false;
                    for (Room r : rooms) {
                        if (r.getRoomNumber() == billRoom && r.isBookingStatus()) {
                            System.out.println("Total Bill for Room " + billRoom + ": ₹" + (int)r.calculateBill());
                            foundBill = true;
                        }
                    }
                    if (!foundBill) System.out.println("Room is either not found or not currently booked.");
                    break;
                    
                case 4:
                    System.out.println("\n--- Room Status ---");
                    for (Room r : rooms) {
                        System.out.println("Room " + r.getRoomNumber() + " (" + r.getRoomType() + ") - " + (r.isBookingStatus() ? "Booked" : "Available"));
                    }
                    break;
                    
                case 5:
                    System.out.print("Enter Room Number to cancel booking (101, 201, 301): ");
                    int cancelRoom = scanner.nextInt();
                    boolean foundCancel = false;
                    for (Room r : rooms) {
                        if (r.getRoomNumber() == cancelRoom && r.isBookingStatus()) {
                            r.cancelBooking();
                            foundCancel = true;
                        }
                    }
                    if (!foundCancel) System.out.println("Room is either not found or not currently booked.");
                    break;
                    
                case 6:
                    System.out.println("\nTotal Bookings Active/Historical: " + Room.getTotalBookings());
                    break;
                    
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    break menuLoop;
                    
                default:
                    System.out.println("Invalid choice. Please select 1-7.");
            }
        }
        scanner.close();
    }
}