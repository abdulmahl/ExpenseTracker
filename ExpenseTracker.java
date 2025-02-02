import java.util.*;

public class ExpenseTracker {

  // Product class to represent an expense item
  public static class Product {
    private final String name; // Name of the product
    private final double price; // Price of the product
    private final String category; // Category of the expense

    // Constructor to initialize the product details
    public Product(String name, double price, String category) {
      this.name = name;
      this.price = price;
      this.category = category;
    }

    // Getter methods to access product properties
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
      System.out.printf("%n--------------------------------------------%n");
      System.out.printf("Product Added:");
      System.out.printf("\nProduct: %s%nPrice: R%.2f%nCategory: %s%n", name, price, category);
      System.out.printf("--------------------------------------------%n");
    }
  }

  // Main method that runs the expense tracker
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // Scanner for user input
    List<Product> expenseList = new ArrayList<>(); // List to store products (expenses)
    Map<String, Double> categoryTotals = new HashMap<>(); // Map to store total expenses by category
    double totalSpend = 0; // Variable to track total spending

    System.out.println("Welcome to the Expense Tracker.");

    // Main loop to keep the program running until the user chooses to exit
    while (true) {
      System.out.println("Choose an option: 1) Add Expense  2) View Summary  3) Exit");
      String choice = scanner.nextLine().trim(); // Get user choice

      // Exit the program if choice is '3'
      if (choice.equals("3")) {
        break;
      }

      // Add Expense if choice is '1'
      else if (choice.equals("1")) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine().trim();

        // Ensure the product name is not empty
        while (productName.isEmpty()) {
          System.out.println("Product name cannot be empty.");
          System.out.print("Enter product name: ");
          productName = scanner.nextLine().trim();
        }

        double expense = 0;

        // Ensure a valid positive expense amount is entered
        while (true) {
          System.out.print("Enter expense for " + productName + ": ");
          if (scanner.hasNextDouble()) {
            expense = scanner.nextDouble();
            scanner.nextLine();
            if (expense > 0)
              break; // Exit loop if expense is valid
            else
              System.out.println("Expense must be positive.");
          } else {
            System.out.println("Invalid input. Enter a number.");
            scanner.nextLine(); // Consume invalid input
          }
        }

        // Ensure valid category input
        System.out.print("Enter category for " + productName + ": ");
        String category = scanner.nextLine().trim();

        // Validate category input: Check for empty or invalid category (only letters
        // and spaces allowed)
        if (category.isEmpty()) {
          System.out.println("Category cannot be empty.");
          System.out.print("Enter product category: ");
          category = scanner.nextLine().trim();
        } else if (!category.matches("[a-zA-Z\\s]+")) {
          System.out.print("Enter a valid product category: ");
          category = scanner.nextLine().trim();
        }

        // Create product object and add it to the expense list
        Product product = new Product(productName, expense, category);
        expenseList.add(product);

        // Update category totals and overall total spend
        categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + expense);
        totalSpend += product.getPrice();

        // Display product details and total spend so far
        product.displayDetails();
        System.out.printf("Total spend so far: R%.2f%n--------------------------------------------%n", totalSpend);
      }

      // Show summary if the user selects option '2'
      else if (choice.equals("2")) {
        displaySummary(expenseList, categoryTotals, totalSpend);
      }

      // Handle invalid choices
      else {
        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
      }
    }

    // Display summary on exit
    displaySummary(expenseList, categoryTotals, totalSpend);
    System.out.println("Thank you for using the Expense Tracker!");
    scanner.close();
  }

  // Method to display the summary of expenses
  private static void displaySummary(List<Product> expenseList, Map<String, Double> categoryTotals, double totalSpend) {
    System.out.println("\n---------------------------------------------------------------------");
    System.out.println("Expense Summary by Category:");

    // If the expense list is empty, display a message
    if (expenseList.isEmpty()) {
      System.out.println("Your Expense List is Empty");
    } else {
      // Loop through the categories and display the total spending in each category
      categoryTotals
          .forEach((category, total) -> System.out.printf("Category: %s, Total Spend: R%.2f%n", category, total));
      System.out.println("---------------------------------------------------------------------");

      // Display detailed list of expenses
      System.out.println("\nDetailed Expense List:");
      expenseList.forEach(
          p -> System.out.printf("Category: %s, Name: %s, Price: R%.2f%n", p.getCategory(), p.getName(), p.getPrice()));
    }

    // Display the total spending for the session
    System.out.printf("Total spend for this session: R%.2f%n", totalSpend);

    // Check if the user has exceeded R1000 in spending and provide feedback
    System.out.println(
        totalSpend > 1000 ? "You have spent over R1000. Be mindful of expenses." : "You are within budget. Well done!");
    System.out.println("---------------------------------------------------------------------");
  }
}
