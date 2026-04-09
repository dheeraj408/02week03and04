import java.util.*;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class TradeVolumeAnalysis {

 
    static void mergeSort(Trade[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // <= preserves stability (left-side wins on tie)
            if (arr[i].volume <= arr[j].volume)
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        while (i <= mid)  temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
    }

  
    static void quickSort(Trade[] arr, int low, int high) {
        if (low >= high) return;
        int pivotIdx = medianOfThreePivot(arr, low, high);
        int p = lomutoPartition(arr, low, high, pivotIdx);
        quickSort(arr, low, p - 1);
        quickSort(arr, p + 1, high);
    }

  
    static int medianOfThreePivot(Trade[] arr, int low, int high) {
        int mid = (low + high) / 2;
        if (arr[low].volume < arr[mid].volume)  swap(arr, low, mid);
        if (arr[low].volume < arr[high].volume) swap(arr, low, high);
        if (arr[mid].volume < arr[high].volume) swap(arr, mid, high);
        return mid; // median now at mid
    }

    static int lomutoPartition(Trade[] arr, int low, int high, int pivotIdx) {
        swap(arr, pivotIdx, high);          // move pivot to end
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot) {   // DESC: >= keeps higher volumes left
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);             // place pivot in final position
        return i + 1;
    }

    static void swap(Trade[] arr, int a, int b) {
        Trade t = arr[a]; arr[a] = arr[b]; arr[b] = t;
    }

    
    static Trade[] mergeSortedLists(Trade[] morning, Trade[] afternoon) {
        Trade[] result = new Trade[morning.length + afternoon.length];
        int i = 0, j = 0, k = 0;

        while (i < morning.length && j < afternoon.length) {
            if (morning[i].volume <= afternoon[j].volume)
                result[k++] = morning[i++];
            else
                result[k++] = afternoon[j++];
        }
        while (i < morning.length)  result[k++] = morning[i++];
        while (j < afternoon.length) result[k++] = afternoon[j++];

        return result;
    }

  
    static long totalVolume(Trade[] arr) {
        long sum = 0;
        for (Trade t : arr) sum += t.volume;
        return sum;
    }

    static void printArray(Trade[] arr) {
        System.out.println(Arrays.toString(arr));
    }

   
    public static void main(String[] args) {

        Trade[] trades = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300),
            new Trade("trade5", 800),
            new Trade("trade4", 150)
        };

        Trade[] mergeArr = Arrays.copyOf(trades, trades.length);
        System.out.println("=== Merge Sort (volume ASC) ===");
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.print("Result: ");
        printArray(mergeArr);
        System.out.println("Total volume: " + totalVolume(mergeArr));

       
        Trade[] quickArr = Arrays.copyOf(trades, trades.length);
        System.out.println("\n=== Quick Sort (volume DESC) ===");
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.print("Result: ");
        printArray(quickArr);

    
        Trade[] morning = {
            new Trade("m1", 200), new Trade("m2", 400), new Trade("m3", 600)
        };
        Trade[] afternoon = {
            new Trade("a1", 100), new Trade("a2", 350), new Trade("a3", 700)
        };

        System.out.println("\n=== Merge Morning + Afternoon Sessions ===");
        System.out.print("Morning:   "); printArray(morning);
        System.out.print("Afternoon: "); printArray(afternoon);

        Trade[] merged = mergeSortedLists(morning, afternoon);
        System.out.print("Merged:    "); printArray(merged);
        System.out.println("Total volume: " + totalVolume(merged));
    }
}
