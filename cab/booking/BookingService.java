package cab.booking;

public class BookingService {
    // 2. Model the Cab Type 
    public enum CabType { MINI, SEDAN, SUV }
    
    // 4. Apply the Booking Fee (Final variable)
    private final int BOOKING_FEE = 50;
    
    // 6. Store the Pickup Location (Inner Class)
    public class PickupLocation {
        private String location;
        
        public PickupLocation(String location) {
            this.location = location;
        }
        
        public String getLocation() {
            return location;
        }
    }
    
    // 5. Build the Booking Summary (StringBuilder)
    public String generateSummary(String passengerName, CabType type, Double baseFare, PickupLocation pickup) {
        // Unboxing: Converting Wrapper 'Double' object to primitive 'double'
        double primitiveBase = baseFare; 
        double finalFare = primitiveBase + BOOKING_FEE;
        
        StringBuilder summary = new StringBuilder();
        summary.append("===== Smart Cab Booking System =====\n\n");
        summary.append("Passenger Name: ").append(passengerName).append("\n");
        summary.append("Cab Type: ").append(type).append("\n");
        summary.append("Base Fare: ₹").append(baseFare.intValue()).append("\n");
        summary.append("Booking Fee: ₹").append(BOOKING_FEE).append("\n");
        summary.append("Final Fare: ₹").append((int)finalFare).append("\n\n");
        summary.append("Pickup Location: ").append(pickup.getLocation()).append("\n");
        
        return summary.toString();
    }
}