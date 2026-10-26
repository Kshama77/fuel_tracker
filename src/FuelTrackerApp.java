import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuelTrackerApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<FuelRecord> records = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("==================================");
        System.out.println("       Welcome to FuelTracker");
        System.out.println("    Track fuel purchases & miles");
        System.out.println("==================================");

        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1: addFuelRecord(); break;
                case 2: viewRecords(); break;
                case 3: showSummary(); break;
                case 4:
                    running = false;
                    System.out.println("Thanks for using FuelTracker. Drive safely!");
                    break;
                default: System.out.println("Please choose an option from 1 to 4.");
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n========== FuelTracker ==========");
        System.out.println("1. Add fuel purchase");
        System.out.println("2. View all fuel records");
        System.out.println("3. View fuel summary");
        System.out.println("4. Exit");
    }

    private static void addFuelRecord() {
        System.out.println("\n--- Add Fuel Purchase ---");
        LocalDate date = readDate("Purchase date (YYYY-MM-DD): ");
        double odometer = readNonNegativeDouble("Odometer reading (miles or km): ");
        double quantity = readPositiveDouble("Fuel quantity (gallons or liters): ");
        double cost = readNonNegativeDouble("Total cost ($): ");
        records.add(new FuelRecord(date, odometer, quantity, cost));
        System.out.println("Fuel purchase added successfully!");
    }

    private static void viewRecords() {
        System.out.println("\n--- Fuel Records ---");
        if (records.isEmpty()) {
            System.out.println("No fuel purchases recorded yet.");
            return;
        }
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i));
        }
    }

    private static void showSummary() {
        if (records.isEmpty()) {
            System.out.println("\nNo fuel records available to summarize.");
            return;
        }
        double totalCost = 0;
        double totalQuantity = 0;
        for (FuelRecord record : records) {
            totalCost += record.getCost();
            totalQuantity += record.getQuantity();
        }
        System.out.println("\n--- Fuel Summary ---");
        System.out.println("Number of purchases: " + records.size());
        System.out.printf("Total fuel purchased: %.2f units%n", totalQuantity);
        System.out.printf("Total fuel spending: $%.2f%n", totalCost);
        System.out.printf("Average cost per unit: $%.2f%n",
                totalQuantity == 0 ? 0 : totalCost / totalQuantity);

        if (records.size() >= 2) {
            double first = records.get(0).getOdometer();
            double latest = records.get(records.size() - 1).getOdometer();
            if (latest >= first) {
                System.out.printf("Odometer change between first and latest record: %.1f%n",
                        latest - first);
            } else {
                System.out.println("Odometer change unavailable: latest record is lower than the first record.");
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (Double.isFinite(value) && value >= 0) return value;
            } catch (NumberFormatException ignored) { }
            System.out.println("Enter a valid number that is zero or greater.");
        }
    }

    private static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (Double.isFinite(value) && value > 0) return value;
            } catch (NumberFormatException ignored) { }
            System.out.println("Enter a valid number greater than zero.");
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("Enter a valid date in YYYY-MM-DD format.");
            }
        }
    }
}
