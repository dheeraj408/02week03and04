import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":$" + fee + "@" + timestamp;
    }
}

public class TransactionFeeSort {

   
    static int bubbleSort(ArrayList<Transaction> arr) {
        int n = arr.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - 1 - i; j++) {
                if (arr.get(j).fee > arr.get(j + 1).fee) {
                    Transaction temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }

            if (!swapped) break; // early termination
        }

        System.out.println("BubbleSort passes: " + passes + ", swaps: " + swaps);
        return swaps;
    }

   
    static void insertionSort(ArrayList<Transaction> arr) {
        int n = arr.size();
        int shifts = 0;

        for (int i = 1; i < n; i++) {
            Transaction key = arr.get(i);
            int j = i - 1;

            while (j >= 0 && compareFeeThenTime(arr.get(j), key) > 0) {
                arr.set(j + 1, arr.get(j));
                j--;
                shifts++;
            }
            arr.set(j + 1, key);
        }

        System.out.println("InsertionSort shifts: " + shifts);
    }


    static int compareFeeThenTime(Transaction a, Transaction b) {
        if (Double.compare(a.fee, b.fee) != 0)
            return Double.compare(a.fee, b.fee);
        return a.timestamp.compareTo(b.timestamp);
    }


    static void flagOutliers(ArrayList<Transaction> arr) {
        System.out.print("High-fee outliers (>$50): ");
        boolean found = false;
        for (Transaction t : arr) {
            if (t.fee > 50) {
                System.out.print(t + " ");
                found = true;
            }
        }
        if (!found) System.out.print("none");
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample transactions
        ArrayList<Transaction> txList = new ArrayList<>(Arrays.asList(
            new Transaction("id1", 10.5, "10:00"),
            new Transaction("id2", 25.0, "09:30"),
            new Transaction("id3",  5.0, "10:15"),
            new Transaction("id4", 75.0, "08:45"),
            new Transaction("id5", 55.5, "07:00")
        ));

        int n = txList.size();

      
        ArrayList<Transaction> bubbleList = new ArrayList<>(txList);
        System.out.println("=== Bubble Sort (by fee) ===");
        bubbleSort(bubbleList);
        System.out.print("Sorted: " + bubbleList);
        System.out.println();

       
        ArrayList<Transaction> insertList = new ArrayList<>(txList);
        System.out.println("\n=== Insertion Sort (by fee + timestamp) ===");
        insertionSort(insertList);
        System.out.print("Sorted: " + insertList);
        System.out.println();

        System.out.println("\n=== Outlier Detection ===");
        flagOutliers(bubbleList);
    }
}
