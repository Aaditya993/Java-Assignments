
import cab.booking.BookingService;
import java.util.Scanner;

// Interface required to implement the Anonymous Class
interface Confirmation {
    void showMessage();
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingService service = new BookingService();
        
        // 3. Using Wrapper Classes 
        Integer passengerId = 101; 
        
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Cab Type (MINI, SEDAN, SUV): ");
        BookingService.CabType type = BookingService.CabType.valueOf(scanner.nextLine().toUpperCase());
        
        System.out.print("Enter Base Fare: ");
        double primitiveFare = scanner.nextDouble();
        // 3. Autoboxing: primitive 'double' automatically converted to Wrapper 'Double'
        Double baseFare = primitiveFare; 
        
        scanner.nextLine(); // Consume newline left by nextDouble()
        
        System.out.print("Enter Pickup Location: ");
        String locationInput = scanner.nextLine();
        
        // Instantiating the Inner Class
        BookingService.PickupLocation pickup = service.new PickupLocation(locationInput);
        
        System.out.println("\n" + service.generateSummary(name, type, baseFare, pickup));
        
        // 7. Confirm the Booking (Anonymous Class)
        Confirmation confirmation = new Confirmation() {
            @Override
            public void showMessage() {
                System.out.println("Booking confirmed successfully.");
            }
        };
        
        confirmation.showMessage();
        scanner.close();
    }
}