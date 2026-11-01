package lw04.prelab;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    private static final int TOTAL_PASS = 3;

    public static void main(String[] args) {
        InputStream stream = Main.class.getResourceAsStream("branches.txt");

        int[] stability;
        int[] queries;
        try (Scanner sc = new Scanner(stream)) {
            int n = sc.nextInt();
            stability = new int[n];
            for (int i = 0; i < n; i++) {
                stability[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            queries = new int[m];
            for (int i = 0; i < m; i++) {
                queries[i] = sc.nextInt();
            }
        }

        sortPartially(stability, TOTAL_PASS);
        display(stability);

        for (int q : queries) {
            int pos = findIndex(stability, q);
            if (pos != -1) {
                System.out.println(q + ": FOUND at index " + pos);
            } else {
                System.out.println(q + ": NOT FOUND");
            }
        }
    }

    private static void sortPartially(int[] arr, int passes) {
        for (int p = 1; p <= passes; p++) {
            int current = arr[p];
            int pos = p - 1;
            while (pos >= 0 && arr[pos] < current) {
                arr[pos + 1] = arr[pos];
                pos--;
            }
            arr[pos + 1] = current;
        }
    }

    private static int findIndex(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private static void display(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }
}