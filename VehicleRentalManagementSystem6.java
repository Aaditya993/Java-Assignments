import java.util.Scanner;

// 1. Design the Parent Vehicle Class
class Vehicle {
    private String vehicleNumber;
    private String vehicleModel;
    private String customerName;
    private int rentalDays;

    public Vehicle(String vehicleNumber, String vehicleModel, String customerName, int rentalDays) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleModel = vehicleModel;
        this.customerName = customerName;
        this.rentalDays = rentalDays;
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public String getVehicleModel() { return vehicleModel; }
    public String getCustomerName() { return customerName; }
    public int getRentalDays() { return rentalDays; }

    // Methods designed to be overridden by child classes
    public String getVehicleType() { return "Vehicle"; }
    public int getRatePerDay() { return 0; }
    public String getUniqueProperty() { return ""; }

    public int calculateRental() {
        return rentalDays * getRatePerDay();
    }

    // Displays details and calculated rental charges
    public void displayReceipt() {
        System.out.println("\n===== Vehicle Rental Receipt =====");
        System.out.println();
        System.out.println("Vehicle Type  : " + getVehicleType());
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Model         : " + vehicleModel);
        System.out.println("Customer Name : " + customerName);
        System.out.println("Rental Days   : " + rentalDays);
        System.out.println(getUniqueProperty());
        System.out.println("Rate Per Day  : ₹" + getRatePerDay());
        System.out.println();
        System.out.println("Total Amount  : ₹" + calculateRental());
        System.out.println();
        System.out.println("Vehicle rented successfully.");
    }
}

// 2. Design the Child Classes
class Car extends Vehicle {
    private int numberOfSeats;

    public Car(String vehicleNumber, String vehicleModel, String customerName, int rentalDays, int numberOfSeats) {
        super(vehicleNumber, vehicleModel, customerName, rentalDays);
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String getVehicleType() { return "Car"; }
    
    @Override
    public int getRatePerDay() { return 1500; }
    
    @Override
    public String getUniqueProperty() { return "Seats         : " + numberOfSeats; }
}

class Bike extends Vehicle {
    private int engineCapacity;

    public Bike(String vehicleNumber, String vehicleModel, String customerName, int rentalDays, int engineCapacity) {
        super(vehicleNumber, vehicleModel, customerName, rentalDays);
        this.engineCapacity = engineCapacity;
    }

    @Override
    public String getVehicleType() { return "Bike"; }
    
    @Override
    public int getRatePerDay() { return 700; }
    
    @Override
    public String getUniqueProperty() { return "Engine (cc)   : " + engineCapacity; }
}

class Scooter extends Vehicle {
    private int storageCapacity;

    public Scooter(String vehicleNumber, String vehicleModel, String customerName, int rentalDays, int storageCapacity) {
        super(vehicleNumber, vehicleModel, customerName, rentalDays);
        this.storageCapacity = storageCapacity;
    }

    @Override
    public String getVehicleType() { return "Scooter"; }
    
    @Override
    public int getRatePerDay() { return 500; }
    
    @Override
    public String getUniqueProperty() { return "Storage (L)   : " + storageCapacity; }
}

// Additional Task: ElectricCar child class
class ElectricCar extends Vehicle {
    private int batteryCapacity;

    public ElectricCar(String vehicleNumber, String vehicleModel, String customerName, int rentalDays, int batteryCapacity) {
        super(vehicleNumber, vehicleModel, customerName, rentalDays);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String getVehicleType() { return "Electric Car"; }
    
    @Override
    public int getRatePerDay() { return 2000; } 
    
    @Override
    public String getUniqueProperty() { return "Battery (kWh) : " + batteryCapacity; }
}

// Main System Class
public class VehicleRentalManagementSystem6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vehicle[] rentals = new Vehicle[100];
        int count = 0;

        menuLoop:
        while (true) {
            System.out.println("\n===== Vehicle Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Rent a Bike");
            System.out.println("3. Rent a Scooter");
            System.out.println("4. Rent an Electric Car");
            System.out.println("5. Display Rental Details");
            System.out.println("6. Calculate Rental Charges");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice >= 1 && choice <= 4) {
                System.out.print("Enter Vehicle Number: ");
                String vNum = scanner.nextLine();
                System.out.print("Enter Model: ");
                String model = scanner.nextLine();
                System.out.print("Enter Customer Name: ");
                String cName = scanner.nextLine();
                System.out.print("Enter Rental Days: ");
                int days = scanner.nextInt();

                if (choice == 1) {
                    System.out.print("Enter Number of Seats: ");
                    int seats = scanner.nextInt();
                    rentals[count] = new Car(vNum, model, cName, days, seats);
                } else if (choice == 2) {
                    System.out.print("Enter Engine Capacity (cc): ");
                    int cc = scanner.nextInt();
                    rentals[count] = new Bike(vNum, model, cName, days, cc);
                } else if (choice == 3) {
                    System.out.print("Enter Storage Capacity (L): ");
                    int storage = scanner.nextInt();
                    rentals[count] = new Scooter(vNum, model, cName, days, storage);
                } else if (choice == 4) {
                    System.out.print("Enter Battery Capacity (kWh): ");
                    int battery = scanner.nextInt();
                    rentals[count] = new ElectricCar(vNum, model, cName, days, battery);
                }

                // Display formatting right after booking matches expected output
                rentals[count].displayReceipt();
                count++;

            } else if (choice == 5) {
                if (count == 0) {
                    System.out.println("No rentals found.");
                } else {
                    for (int i = 0; i < count; i++) {
                        rentals[i].displayReceipt();
                    }
                }
            } else if (choice == 6) {
                if (count == 0) {
                    System.out.println("No rentals found.");
                } else {
                    System.out.println("\n--- Rental Charges Log ---");
                    for (int i = 0; i < count; i++) {
                        System.out.println(rentals[i].getCustomerName() + " (" + rentals[i].getVehicleNumber() + ") : ₹" + rentals[i].calculateRental());
                    }
                }
            } else if (choice == 7) {
                System.out.println("Exiting the system. Goodbye!");
                break menuLoop;
            } else {
                System.out.println("Invalid choice. Please select 1-7.");
            }
        }
        scanner.close();
    }
}