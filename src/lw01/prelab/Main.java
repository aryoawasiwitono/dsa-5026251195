import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(Main.class.getResourceAsStream("jobs.txt"));
        List<PrintJob> jobs = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String type = input.next();
            String id = input.next();
            int pages = input.nextInt();

            if (type.equals("MONO")) {
                jobs.add(new MonoPrint(id, pages));
            } else {
                jobs.add(new ColourPrint(id, pages));
            }
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}