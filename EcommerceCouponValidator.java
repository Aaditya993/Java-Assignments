

import java.util.Scanner;

class InvalidCouponException extends Exception {
    public InvalidCouponException(String message) {
        super(message);
    }
}

public class EcommerceCouponValidator {

    static double getDiscountPercent(double amount, String code) throws InvalidCouponException {
    
        int minOrder = switch (code.toUpperCase()) {
            case "SAVE10" -> 1000;
            case "SAVE20" -> 2000;
            case "SAVE30" -> 3000;
            default -> throw new InvalidCouponException("Invalid coupon code: " + code);
        };

 
        if (amount < minOrder) {
            throw new InvalidCouponException(code.toUpperCase() + " requires a minimum order of Rs. " + minOrder);
        }

 
        return minOrder / 100.0;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter order amount: Rs. ");
            double amount = sc.nextDouble();

            System.out.print("Enter coupon code: ");
            String code = sc.next();

            double percent = getDiscountPercent(amount, code);
            double discountAmount = amount * (percent / 100);
            double finalAmount = amount - discountAmount;

            System.out.println("\nCoupon Applied Successfully!");
            System.out.println("Discount: " + (int) percent + "% (Rs. " + discountAmount + ")");
            System.out.println("Final Payable Amount: Rs. " + finalAmount);

        } catch (InvalidCouponException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}