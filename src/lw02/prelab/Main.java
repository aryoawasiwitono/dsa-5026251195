import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    
    public static LinkedList<String[]> readTransactions(String filename) throws FileNotFoundException {
        LinkedList<String[]> transactionList = new LinkedList<>();
        Scanner fileScanner = new Scanner(new File(filename));
        
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(" ");
            if (parts.length == 3) {
                transactionList.add(parts);
            }
        }
        fileScanner.close();
        
        return transactionList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String[]> transactionList = readTransactions("transactions.txt");

        LinkedList<String[]> customerList = new LinkedList<>();
        for (String[] tx : transactionList) {
            String name = tx[0];
            boolean exists = false;
            
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    exists = true;
                    break;
                }
            }
            
            if (!exists) {
                customerList.add(new String[]{name, "0"});
            }
        }

        Queue<String[]> transactionQueue = new LinkedList<>(transactionList);

        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] tx = transactionQueue.poll();
            String name = tx[0];
            String type = tx[1];
            int amount = Integer.parseInt(tx[2]);

            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    int currentBalance = Integer.parseInt(cust[1]);
                    
                    if (type.equals("DEPOSIT")) {
                        currentBalance += amount;
                        cust[1] = String.valueOf(currentBalance);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > currentBalance) {
                            failedTransactions.push(tx);
                        } else {
                            currentBalance -= amount;
                            cust[1] = String.valueOf(currentBalance);
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failedTx = failedTransactions.pop();
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}