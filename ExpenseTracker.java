import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

// Expense class
class Expense {
    String category;
    double amount;
    LocalDate date;

    Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
        this.date = LocalDate.now();
    }
}

public class ExpenseTracker {

    static ArrayList<Expense> expenses = new ArrayList<>();

    // Add Expense
    public static void addExpense(Scanner sc) {
        System.out.print("Enter category: ");
        sc.nextLine(); // clear buffer
        String category = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        while (amount <= 0) {
            System.out.println("Amount must be positive!");
            System.out.print("Enter amount again: ");
            amount = sc.nextDouble();
        }

        expenses.add(new Expense(category, amount));
        System.out.println("Expense added successfully!");
    }

    // Show Expenses
    public static void showExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        double total = 0;

        System.out.println("\nCategory\tAmount\tDate");
        for (Expense e : expenses) {
            System.out.println(e.category + "\t" + e.amount + "\t" + e.date);
            total += e.amount;
        }

        System.out.println("-------------------------------");
        System.out.println("Total Expenses: " + total);

        Map<String, Double> summary = new HashMap<>();
        for (Expense e : expenses) {
            summary.put(e.category,
                    summary.getOrDefault(e.category, 0.0) + e.amount);
        }

        System.out.println("\nCategory-wise Summary:");
        for (String cat : summary.keySet()) {
            System.out.println(cat + ": " + summary.get(cat));
        }
    }

    // Delete Expense (NEW)
    public static void deleteExpense(Scanner sc) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to delete.");
            return;
        }

        System.out.print("Enter category to delete: ");
        sc.nextLine(); // clear buffer
        String category = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).category.equalsIgnoreCase(category)) {
                expenses.remove(i);
                found = true;
                i--; // adjust index after removal
            }
        }

        if (found) {
            System.out.println("Expense(s) deleted successfully!");
        } else {
            System.out.println("No matching category found.");
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n---- Expense Tracker ----");
            System.out.println("1. Add Expense");
            System.out.println("2. Show Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addExpense(sc);
                    break;
                case 2:
                    showExpenses();
                    break;
                case 3:
                    deleteExpense(sc);
                    break;
                case 4:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid option");
            }

        } while (choice != 4);

        sc.close();
    }
}
