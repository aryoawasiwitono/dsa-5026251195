import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main (String[] args){
        Set<String> registeredStudents = new HashSet<>();
        Set<String> checkedInStudents = new HashSet<>();
        List<String> checkInResults = new ArrayList<>();

        int reject = 0;
        Scanner registrationScanner = new Scanner(Main.class.getResourceAsStream("registrations.txt"));

        while (registrationScanner.hasNext()) {
            String studentId = registrationScanner.next();
            registeredStudents.add(studentId);
        }
        registrationScanner.close();

        Scanner checkInScanner = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (checkInScanner.hasNext()) {
            String Id = checkInScanner.next();

            if (!registeredStudents.contains(Id)) {
                reject++;
                checkInResults.add(Id + ": Rejected (not registered)");
            } else if (checkedInStudents.contains(Id)) {
                reject++;
                checkInResults.add(Id + ": Rejected (already checked in)");
            } else {
                checkedInStudents.add(Id);
                checkInResults.add(Id + ": Checked in");
            }
        }
        checkInScanner.close();

        System.out.println("===== Event Check-In Results =====");
        for (String result : checkInResults) {
            System.out.println(result);
        }

        int registeredCount = registeredStudents.size();
        int successfullCheckIns = checkedInStudents.size();
        int absentStudents = registeredCount - successfullCheckIns;

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students : " + registeredCount);
        System.out.println("Successful check-ins: " + successfullCheckIns);

        System.out.println("Absent students     : " + absentStudents);
        System.out.println("Rejected attempts   : " + reject);
    }    
}
    
    

