import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(
                Main.class.getResourceAsStream("parcels.txt"));

        int parcelCount = sc.nextInt();
        int[] parcels = new int[parcelCount];
        for (int i = 0; i < parcelCount; i++) {
            parcels[i] = sc.nextInt();
        }

        int targetCount = sc.nextInt();
        int[] targets = new int[targetCount];
        for (int i = 0; i < targetCount; i++) {
            targets[i] = sc.nextInt();
        }

        sc.close();

        bubbleSort(parcels, 0, 2);
        int[] afterTwoPasses = copyArray(parcels);
        bubbleSort(parcels, 2, parcels.length - 1);

        System.out.println("==== After 2 Passes ====");
        printArray(afterTwoPasses);

        System.out.println("==== Fully Sorted ====");
        printArray(parcels);

        System.out.println("==== Search Results ====");
        for (int target : targets) {
            SearchResult result = binarySearchDescending(parcels, target);
            if (result.index >= 0) {
                System.out.println(target + ": FOUND at index " + result.index
                        + " (steps: " + result.steps + ")");
            } else {
                System.out.println(target + ": NOT FOUND (steps: " + result.steps + ")");
            }
        }
    }

    private static void bubbleSort(int[] values, int firstPass, int endPass) {
        for (int pass = firstPass; pass < endPass; pass++) {
            for (int j = 0; j < values.length - 1 - pass; j++) {
                if (values[j] < values[j + 1]) {
                    int temporary = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temporary;
                }
            }
        }
    }

    private static int[] copyArray(int[] values) {
        int[] copy = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            copy[i] = values[i];
        }
        return copy;
    }

    private static void printArray(int[] values) {
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(values[i]);
        }
        System.out.println();
    }

    private static SearchResult binarySearchDescending(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int steps = 0;

        while (low <= high) {
            int middle = (low + high) / 2;
            steps++;

            if (values[middle] == target) {
                return new SearchResult(middle, steps);
            } else if (values[middle] < target) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }

        return new SearchResult(-1, steps);
    }

    private static class SearchResult {
        private final int index;
        private final int steps;

        private SearchResult(int index, int steps) {
            this.index = index;
            this.steps = steps;
        }
    }
}