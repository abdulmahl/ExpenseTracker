import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseTracker {

  // Inner class to represent a product with name, price, and category
  public static class Product {
    String name;
    double price;
    String category;

    // Constructor to initialize product details
    public Product(String name, double price, String category) {
      this.name = name;
      this.price = price;
      this.category = category;
    }

    // Getters for the product attributes
    public String getName() {
      return name;
    }

    public double getPrice() {
      return price;
    }

    public String getCategory() {
      return category;
    }

    // Method to display the details of the product
    public void displayDetails() {
      System.out.println("--------------------------------------------");
      System.out.println("Product Details:");
      System.out.println("--------------------------------------------");
      System.out.println("Product: " + name);
      System.out.println("Price: R" + String.format("%.2f", price));
      System.out.println("Category: " + category);
      System.out.println("--------------------------------------------");
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Product> expenseList = new ArrayList<>(); // List to store all expenses
    double totalSpend = 0; // Tracks total spending
    boolean keepAdding = true; // Controls whether to continue adding expenses

    System.out.println("Welcome to the Expense Tracker.");

    while (keepAdding) {
      System.out.print("Enter the name of the product: ");
      String productName = scanner.nextLine();

      // Ensure product name is not empty
      if (productName.trim().isEmpty()) {
        System.out.println("Product name cannot be empty. Please try again.");
        continue;
      }

      System.out.print("Enter the expense for " + productName + " (or press 0 to quit): ");

      if (scanner.hasNextDouble()) {
        double expense = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        if (expense == 0) {
          keepAdding = false;
        } else if (expense > 0) {
          System.out.print("Enter the category for " + productName + ": ");
          String category = scanner.nextLine();

          // Create a new Product object
          Product product = new Product(productName, expense, category);

          // Add product to the list
          expenseList.add(product);

          // Display product details
          product.displayDetails();

          // Update total spending
          totalSpend += product.getPrice();

          System.out.println("Total spend so far: R" + String.format("%.2f", totalSpend));
          System.out.println("--------------------------------------------");
          System.out.println();

        } else {
          System.out.println("Invalid input. Please enter a positive number.");
        }
      } else {
        System.out.println("Invalid input. Please enter a valid number.");
        scanner.next(); // Clear invalid input
      }
    }

    // Display summary of all expenses
    System.out.println("---------------------------------------------------------------------");
    System.out.println("\nThank you for using the Expense Tracker.");
    System.out.println("---------------------------------------------------------------------");
    System.out.println("\nExpense Summary by Category:");
    for (Product product : expenseList) {
      System.out.println("Category: " + product.getCategory() + ", Name: " + product.getName() + ", Price: R"
          + String.format("%.2f", product.getPrice()));
    }

    System.out.println("Total spend for this session is: R" + String.format("%.2f", totalSpend));

    // Provide feedback based on total spending
    if (totalSpend > 1000) {
      System.out.println("You have spent more than R1000. Please be mindful of your expenses.");
    } else {
      System.out.println("You are within your budget. Well done!");
    }
    System.out.println("---------------------------------------------------------------------");

    scanner.close(); // Close the scanner to release resources
  }
}
