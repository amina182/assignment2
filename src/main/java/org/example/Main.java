package org.example;

import dao.PatientDao;
import dao.DoctorDao;
import dao.HospitalDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                1. Add patient
                2. Show all patients
                3. Update patient age
                4. Delete patient
                5. Add doctor
                6. Show all doctors
                7. Add hospital
                """);

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {

                    case 1 -> {
                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Surname: ");
                        String surname = sc.nextLine();

                        System.out.print("Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Gender: ");
                        String gender = sc.nextLine();

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        PatientDao.addPatient(name, surname, age, gender,email);
                        System.out.println("Patient added");
                    }
                    case 2 -> {
                        List<String> patients = PatientDao.getAllPatients();
                        for (String p : patients) {
                            System.out.println(p);
                        }
                    }

                    case 3 -> {
                        System.out.print("Patient ID: ");
                        int id = sc.nextInt();

                        System.out.print("New age: ");
                        int age = sc.nextInt();

                        PatientDao.updatePatient(id, age);
                        System.out.println("Patient updated");
                    }

                    case 4 -> {
                        System.out.print("Patient ID: ");
                        int id = sc.nextInt();

                        PatientDao.deletePatient(id);
                        System.out.println("Patient deleted");
                    }

                    case 5 -> {
                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Surname: ");
                        String surname = sc.nextLine();

                        System.out.print("Specialization: ");
                        String spec = sc.nextLine();

                        System.out.print("Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        DoctorDao.addDoctor(name, surname, spec,age);
                        System.out.println("Doctor added");
                    }
                    case 6 -> {
                        List<String> doctors = DoctorDao.getAllDoctors();
                        for (String d : doctors) {
                            System.out.println(d);
                        }
                    }

                    case 7 -> {
                        System.out.print("Hospital name: ");
                        String name = sc.nextLine();

                        System.out.print("Address: ");
                        String address = sc.nextLine();

                        HospitalDao.addHospital(name, address);
                        System.out.println("Hospital added");
                    }
                    case 0 -> {
                        System.out.println("Bye");
                        return;
                    }

                    default -> System.out.println("Wrong option");
                }

            } catch (SQLException e) {
                System.out.println("Database error");
            }
        }
    }
}
