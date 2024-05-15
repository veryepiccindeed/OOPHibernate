package com.example.hibernatetest;

import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static Scanner myScanner = new Scanner(System.in);

    public static String getInput(String message) {
        try {
            System.out.print(message);
            String input = myScanner.nextLine().trim();

            if (input.matches("\\d+")) {
                throw new IllegalArgumentException("Input tidak boleh hanya berisi angka.");
            }

            else if (!Pattern.matches("[\\p{Alnum}]+", input)) {
                System.out.println("Input mengandung simbol. Harap masukkan bilangan bulat.");
                return getInput(message);
            }

            else if (input.isEmpty()) {
                System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
                return getInput(message);
            }

            return input;
        } catch (IllegalArgumentException ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return getInput(message);
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return getInput(message);
        }
    }

    public static int getInputInt(String message) {
        try {
            System.out.print(message);
            String inputStr = myScanner.nextLine().trim();
            if (inputStr.isEmpty()) {
                System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
                return getInputInt(message);
            }

            if (inputStr.contains(".") || inputStr.contains(",")) {
                System.out.println("Input tidak valid. Harap masukkan bilangan bulat.");
                return getInputInt(message);
            }

            Integer input = Integer.parseInt(inputStr);

            if (input < 0) {
                System.out.println("Input tidak boleh kurang dari atau sama dengan 0. Silakan coba lagi.");
                return getInputInt(message);
            }

            return input;
        } catch (NumberFormatException ex) {
            System.out.println("Input tidak valid. Harap masukkan bilangan bulat.");
            return getInputInt(message);
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return getInputInt(message);
        }
    }

    

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;
        try {
            boolean exit = false;
            while (!exit) {
                List<Student> result = session.createQuery("from Student", Student.class).list();
                System.out.println("== Student List ==");
                for (Student student : result) {
                    System.out.println(student.toString());
                }

                System.out.println("Select the operation you want to perform:");
                System.out.println("1. Insert");
                System.out.println("2. Update");
                System.out.println("3. Delete");
                System.out.println("4. Exit");
                // Perform database operations
                int choice = getInputInt("Insert your choice: ");
                switch (choice) {
                    case 1:
                        tx = session.beginTransaction();
                        Student newStudent = new Student();
                        newStudent.setName(getInput("Insert student's name: "));
                        newStudent.setAge(getInputInt("Insert student's age: "));
                        newStudent.setMajor(getInput("Insert student's major: "));
                        session.save(newStudent);
                        tx.commit();
                        break;
                    case 2:
                        Long updateId = Long.parseLong(String.valueOf(getInputInt("Enter the student's ID you want to update: ")));
                        Student studentToUpdate = session.get(Student.class, updateId);
                        if (studentToUpdate != null) {
                            tx = session.beginTransaction();
                            System.out.println("Data siswa yang ingin diupdate: " + studentToUpdate);
                            studentToUpdate.setName(getInput("Insert new student's name: "));
                            studentToUpdate.setAge(getInputInt("Insert new student's age: "));
                            studentToUpdate.setMajor(getInput("Insert new student's major: "));
                            session.update(studentToUpdate);
                            tx.commit();
                        } else {
                            System.out.println("Couldn't find the entered ID.");
                        }
                        break;
                    case 3:
                        Long deleteId = Long.parseLong(String.valueOf(getInputInt("Enter the student's ID you want to delete: ")));
                        Student studentToDelete = session.get(Student.class, deleteId);
                        if (studentToDelete != null) {
                            tx = session.beginTransaction();
                            System.out.println("Deleted student's data: " + studentToDelete);
                            session.delete(studentToDelete);
                            tx.commit();
                        } else {
                            System.out.println("Couldn't find the entered ID.");
                        }
                        break;
                    case 4:
                    System.out.println("Adios.");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid Option.");
                }
            }

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}