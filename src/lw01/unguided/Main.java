import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(Main.class.getResourceAsStream("Washes.txt"));
        int n = input.nextInt();
        WashService[] services = new WashService[n];
        int[] units = new int[n];

        for (int i = 0; i < n; i++) {
            String type = input.next();
            String id = input.next();
            int days = input.nextInt();
            int unit = input.nextInt();

            units[i] = unit;

            if (type.equals("MOTORCYCLE")) {
                services[i] = new MotorcycleWash(id, days);
            } else {
                services[i] = new CarWash(id, days);
            }
        }

        for (int i = 0; i < n; i++) {
            int totalCharge = services[i].calculateCharge(units[i]);
            System.out.println(services[i].getId() + " | " + services[i].label() + " | " + totalCharge);
        }
    }
}