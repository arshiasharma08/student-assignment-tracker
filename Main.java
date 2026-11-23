import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Assignment> assignments = new ArrayList<>();

        boolean running = true;

        while (running) {
            System.out.println("\nStudent Assignment Tracker");
            System.out.println("1. Add Assignment");
            System.out.println("2. View Assignments");
            System.out.println("3. Mark Assignment Completed");
            System.out.println("4. Save Assignments");
            System.out.println("5. Load Assignments");
            System.out.println("6. Delete Assignment");
            System.out.println("7. Exit");
            System.out.println("Choose an option");

            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                String name = "";

                while (name.isEmpty()) {
                    System.out.println("Assignment name: ");
                    name = scanner.nextLine().trim();

                    if (name.isEmpty()) {
                        System.out.println("Assignment name cannot be empty.");
                    }
                }

                String course = "";

                while (course.isEmpty()) {
                    System.out.println("Course: ");
                    course = scanner.nextLine().trim();

                    if (course.isEmpty()) {
                        System.out.println("Course cannot be empty.");
                    }
                }

                String dueDate = "";
                boolean validDate = false;

                while (!validDate) {
                    System.out.println("Due date (YYYY-MM-DD): ");
                    dueDate = scanner.nextLine();

                    try {
                        LocalDate.parse(dueDate);
                        validDate = true;
                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("Invalid date. Use YYYY-MM-DD.");
                    }
                }

                Assignment assignment = new Assignment(name, course, dueDate);
                assignments.add(assignment);

                System.out.println("Assignment added!");

            } else if (choice.equals("2")) {
                if (assignments.isEmpty()) {
                    System.out.println("No assignments found.");
                } else {
                    for (int i = 0; i < assignments.size(); i++) {
                        System.out.println((i + 1) + ". " + assignments.get(i));
                    }
                }

            } else if (choice.equals(("3"))) {
                for (int i = 0; i < assignments.size(); i++) {
                    System.out.println((i + 1) + ". " + assignments.get(i));
                }

                if (assignments.isEmpty()) {
                    System.out.println("No assignments to complete.");
                } else {
                    System.out.println("Enter assignment number: ");
                    String input = scanner.nextLine();

                    try {
                        int number = Integer.parseInt(input);

                        if (number >= 1 && number <= assignments.size()) {
                            assignments.get(number - 1).markCompleted();
                            System.out.print("Assignment completed");
                        } else {
                            System.out.println("Invalid assignment number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                }

            } else if (choice.equals("4")) {
                try {
                    PrintWriter writer = new PrintWriter("assignments.csv");

                    for (int i = 0; i < assignments.size(); i++) {
                        Assignment a = assignments.get(i);

                        writer.println(a.getName() + ","
                                + a.getCourse() + ","
                                + a.getDueDate() + ","
                                + a.isCompleted());
                    }

                    writer.close();
                    System.out.println("Assignments saved");

                } catch (FileNotFoundException e) {
                    System.out.println("Error saving assignments.");
                }

            } else if (choice.equals("5")) {
                try {
                    File file = new File("assignments.csv");
                    Scanner fileScanner = new Scanner(file);

                    assignments.clear();

                    while (fileScanner.hasNextLine()) {
                        String line = fileScanner.nextLine();
                        String[] data = line.split(",");

                        if (data.length == 4) {
                            Assignment a = new Assignment(
                                    data[0], data[1], data[2]);

                            if (data[3].equals("true")) {
                                a.markCompleted();
                            }

                            assignments.add(a);
                        }
                    }

                    fileScanner.close();
                    System.out.println("Assignments loaded!");

                } catch (FileNotFoundException e) {
                    System.out.println("No saved assignments found.");
                }

            } else if (choice.equals("6")) {
                if (assignments.isEmpty()) {
                    System.out.println("No assignments to delete.");
                } else {
                    for (int i = 0; i < assignments.size(); i++) {
                        System.out.println((i + 1) + ". " + assignments.get(i));
                    }

                    System.out.println("Enter assignment number to delete: ");
                    String input = scanner.nextLine();

                    try {
                        int number = Integer.parseInt(input);

                        if (number >= 1 && number <= assignments.size()) {
                            assignments.remove(number - 1);
                            System.out.println("Assignment deleted!");
                        } else {
                            System.out.println("Invalid assignment number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }

                }

            } else if (choice.equals("7")) {
                running = false;
                System.out.println("Goodbye!");
            }

            else {
                System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
