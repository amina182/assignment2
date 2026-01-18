package hospital;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Person> people = new ArrayList<>();

        people.add(new Patient("Amina", "Auyeskhan", 17));
        people.add(new Patient("Adelya", "Baysalova", 20));
        people.add(new Doctor("Aidyn", "Arkhatov", "Cardiologist"));
        people.add(new Doctor("Zhuldyz", "Akhmetovna", "Dentist"));


        System.out.print("Enter new patient name: ");
        String name = scanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        people.add(new Patient( name, surname, age));

        System.out.println("\nAll people:");
        for (Person p : people) {
            p.displayInfo();
        }

        System.out.println("\nServices:");
        for (Person p : people) {
            if (p instanceof HospitalService) {
                ((HospitalService) p).provideService();
            }
        }

        System.out.println("\nPatients list:");
        for (Person p : people) {
            if (p instanceof Patient) {
                p.displayInfo();
            }
        }

        System.out.println("\nSearch by name: Amina ");
        for (Person p : people) {
            if (p.getName().equalsIgnoreCase("Amina")) {
                p.displayInfo();
            }
        }

        people.sort(Comparator.comparing(Person::getName));

        System.out.println("\nSorted by name:");
        for (Person p : people) {
            System.out.println(p);
        }
    }
}
