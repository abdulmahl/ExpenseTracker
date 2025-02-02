import java.util.*;

public class ExpenseTracker {
  public static class Product {
    private final String name;
    private final double price;
    private final String category;

    public Product(String name, double price, String category) {
      this.name = name;
      this.price = price;
      this.category = category;
    }

    public String getName() {
      return name;
    }

    public double getPrice() {
      return price;
    }

    public String getCategory() {
      return category;
    }

    public void displayDetails() {
      System.out.printf("%n--------------------------------------------%n");
      System.out.printf("Product Added:");

      System.out.printf("\nProduct: %s%nPrice: R%.2f%nCategory: %s%n", name, price, category);
      System.out.printf("--------------------------------------------%n");
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Product> expenseList = new ArrayList<>();
    Map<String, Double> categoryTotals = new HashMap<>();
    double totalSpend = 0;

    System.out.println("Welcome to the Expense Tracker.");
    while (true) {
      System.out.println("Choose an option: 1) Add Expense  2) View Summary  3) Exit");
      String choice = scanner.nextLine().trim();

      if (choice.equals("3")) {
        break;
      } else if (choice.equals("1")) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine().trim();
        while (productName.isEmpty()) {
          System.out.println("Product name cannot be empty.");
          System.out.print("Enter product name: ");
          productName = scanner.nextLine().trim();
        }

        double expense = 0;
        while (true) {
          System.out.print("Enter expense for " + productName + ": ");
          if (scanner.hasNextDouble()) {
            expense = scanner.nextDouble();
            scanner.nextLine();
            if (expense > 0)
              break;
            else
              System.out.println("Expense must be positive.");
          } else {
            System.out.println("Invalid input. Enter a number.");
            scanner.nextLine();
          }
        }

        System.out.print("Enter category for " + productName + ": ");
        String category = scanner.nextLine().trim();

        // Check if the category is empty
        if (category.isEmpty()) {
          System.out.println("Category cannot be empty.");
          System.out.print("Enter product category: ");
          category = scanner.nextLine().trim();
        } else if (!category.matches("[a-zA-Z\\s]+")) { // Regex to allow only alphabets and spaces
          System.out.print("Enter a valid product category: ");
          category = scanner.nextLine().trim();
        }

        Product product = new Product(productName, expense, category);
        expenseList.add(product);
        categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + expense);
        totalSpend += product.getPrice();

        product.displayDetails();
        System.out.printf("Total spend so far: R%.2f%n--------------------------------------------%n", totalSpend);
      } else if (choice.equals("2")) {
        displaySummary(expenseList, categoryTotals, totalSpend);
      } else {
        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
      }
    }

    // Display summary on exit
    displaySummary(expenseList, categoryTotals, totalSpend);
    System.out.println("Thank you for using the Expense Tracker!");
    scanner.close();
  }

  private static void displaySummary(List<Product> expenseList, Map<String, Double> categoryTotals, double totalSpend) {
    System.out.println("\n---------------------------------------------------------------------");
    System.out.println("Expense Summary by Category:");
    if (expenseList.isEmpty()) {
      System.out.println("Your Expense List is Empty");
    } else {
      categoryTotals
          .forEach((category, total) -> System.out.printf("Category: %s, Total Spend: R%.2f%n", category, total));
      System.out.println("---------------------------------------------------------------------");
      System.out.println("\nDetailed Expense List:");
      expenseList.forEach(
          p -> System.out.printf("Category: %s, Name: %s, Price: R%.2f%n", p.getCategory(), p.getName(), p.getPrice()));
    }
    System.out.printf("Total spend for this session: R%.2f%n", totalSpend);
    System.out.println(
        totalSpend > 1000 ? "You have spent over R1000. Be mindful of expenses." : "You are within budget. Well done!");
    System.out.println("---------------------------------------------------------------------");
  }
}
