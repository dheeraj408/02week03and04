import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + "(risk=" + riskScore + ", bal=$" + String.format("%.1f", accountBalance) + ")";
    }
}

public class ClientRiskRanking {

    static int bubbleSort(Client[] arr) {
        int n = arr.length;
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    swaps++;
                    System.out.println("  Swap: " + arr[j].name + " <-> " + arr[j+1].name);
                }
            }

            if (!swapped) {
                System.out.println("  Early termination at pass " + passes);
                break;
            }
        }

        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
        return swaps;
    }

  
    static void insertionSort(Client[] arr) {
        int n = arr.length;
        int shifts = 0;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compareDescRiskAscBal(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
                shifts++;
            }
            arr[j + 1] = key;
        }

        System.out.println("Shifts: " + shifts);
    }


    static int compareDescRiskAscBal(Client a, Client b) {
        if (b.riskScore != a.riskScore)
            return b.riskScore - a.riskScore;           // DESC risk
        return Double.compare(a.accountBalance, b.accountBalance); // ASC balance
    }

    static void printTopK(Client[] arr, int k) {
        System.out.print("Top " + k + " risks: ");
        for (int i = 0; i < Math.min(k, arr.length); i++) {
            System.out.print(arr[i].name + "(" + arr[i].riskScore + ")");
            if (i < k - 1) System.out.print(", ");
        }
        System.out.println();
    }

    static void printArray(Client[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        Client[] clients = {
            new Client("clientC", 80, 15000.0),
            new Client("clientA", 20, 50000.0),
            new Client("clientB", 50, 30000.0),
            new Client("clientF", 95, 5000.0),
            new Client("clientD", 65, 20000.0),
            new Client("clientE", 40, 10000.0)
        };


        Client[] bubbleArr = Arrays.copyOf(clients, clients.length);
        System.out.println("=== Bubble Sort (riskScore ASC) ===");
        bubbleSort(bubbleArr);
        System.out.print("Result: ");
        printArray(bubbleArr);

        Client[] insertArr = Arrays.copyOf(clients, clients.length);
        System.out.println("\n=== Insertion Sort (riskScore DESC + balance ASC) ===");
        insertionSort(insertArr);
        System.out.print("Result: ");
        printArray(insertArr);

        System.out.println("\n=== Top 3 Highest Risk Clients ===");
        printTopK(insertArr, 3);
    }
}
