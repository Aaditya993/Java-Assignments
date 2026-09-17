
public class FoodDeliveryRatingAnalyzer3 {
    public static void main(String[] args) {
        // 1. Store the Ratings in a 2-D array
        int[][] ratings = {
            {4, 5, 4, 5}, // Partner 1
            {3, 4, 5, 4}, // Partner 2
            {5, 5, 5, 5}, // Partner 3
            {4, 3, 4, 3}, // Partner 4
            {5, 4, 5, 4}  // Partner 5
        };
        
        // Arrays to store partner IDs and totals
        int[] partnerIds = {1, 2, 3, 4, 5};
        int[] totals = new int[5];
        
        System.out.println("===== Food Delivery Rating Analysis =====\n");
        
        // 2. Compute Totals and Averages using overloaded methods
        totals = calculateTotal(ratings); // Using overloaded method for entire 2-D array
        
        for (int i = 0; i < ratings.length; i++) {
            // Using overloaded method for a single row
            int singleTotal = calculateTotal(ratings[i]);
            double average = calculateAverage(singleTotal, ratings[i].length);
            
            System.out.println("Partner " + partnerIds[i] + " Total   : " + singleTotal);
            System.out.printf("Partner %d Average : %.2f\n\n", partnerIds[i], average);
        }
        
        // 3. Search (Linear Search demonstration)
        int searchTarget = 3;
        System.out.println("--- Linear Search ---");
        linearSearch(ratings, searchTarget);
        System.out.println();
        
        // 4. Sort and Rank (Bubble Sort)
        bubbleSort(totals, partnerIds);
        
        System.out.println("===== Ranking =====\n");
        for (int i = 0; i < totals.length; i++) {
            System.out.println((i + 1) + ". Partner " + partnerIds[i]);
        }
        System.out.println("\nHighest Rated Partner: Partner " + partnerIds[0]);
    }
    
    // Method to calculate total rating for a single partner (row)
    public static int calculateTotal(int[] row) {
        int total = 0;
        for (int rating : row) {
            total += rating;
        }
        return total;
    }
    
    // Overloaded Method to calculate total rating for all partners (2-D array)
    public static int[] calculateTotal(int[][] allRatings) {
        int[] totalsArray = new int[allRatings.length];
        for (int i = 0; i < allRatings.length; i++) {
            int sum = 0;
            for (int j = 0; j < allRatings[i].length; j++) {
                sum += allRatings[i][j];
            }
            totalsArray[i] = sum;
        }
        return totalsArray;
    }
    
    // Method to calculate average rating
    public static double calculateAverage(int total, int parametersCount) {
        return (double) total / parametersCount;
    }
    
    // Method for Linear Search
    public static void linearSearch(int[][] ratings, int target) {
        boolean found = false;
        for (int i = 0; i < ratings.length; i++) {
            for (int j = 0; j < ratings[i].length; j++) {
                if (ratings[i][j] == target) {
                    System.out.println("Rating " + target + " found for Partner " + (i + 1) + " at parameter index " + j);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Rating " + target + " not found.");
        }
    }
    
    // Method for Bubble Sort (ranks highest to lowest and syncs partner IDs)
    public static void bubbleSort(int[] totals, int[] partnerIds) {
        int n = totals.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (totals[j] < totals[j + 1]) {
                    // Swap totals
                    int tempTotal = totals[j];
                    totals[j] = totals[j + 1];
                    totals[j + 1] = tempTotal;
                    
                    // Swap partner IDs to maintain alignment
                    int tempId = partnerIds[j];
                    partnerIds[j] = partnerIds[j + 1];
                    partnerIds[j + 1] = tempId;
                }
            }
        }
    }
}