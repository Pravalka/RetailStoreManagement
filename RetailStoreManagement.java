import java.util.*;

class Product {
    private String name;
    private String description;
    private double price;
    private int quantity;

    public Product(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Description: " + description + " | Price: $" + price + " | Quantity: " + quantity;
    }
}

class Inventory {
    private Map<String, Product> products;
    private static final int RESTOCK_THRESHOLD = 10;

    public Inventory() {
        products = new HashMap<>();
    }

    public void addProduct(String productName, Product product) {
        products.put(productName, product);
        System.out.println("Product added successfully.");
    }

    public void updateProductDetails(String productName) {
        Product product = products.get(productName);
        if (product != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter new product description: ");
            String newDescription = scanner.nextLine();

            product.setDescription(newDescription);
            System.out.println("Product details updated successfully.");
        } else {
            System.out.println("Product not found!");
        }
    }

    public void recordSale(String productName, int quantitySold) {
        Product product = products.get(productName);
        if (product != null) {
            int updatedQuantity = product.getQuantity() - quantitySold;
            if (updatedQuantity >= 0) {
                product.setQuantity(updatedQuantity);
                products.put(productName, product);
                System.out.println("Sale recorded successfully.");
            } else {
                System.out.println("Insufficient stock!");
            }
        } else {
            System.out.println("Product not found!");
        }
    }

    public void checkStockAndNotify() {
        System.out.println("Low Stock Report:");
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            Product product = entry.getValue();
            if (product.getQuantity() < RESTOCK_THRESHOLD) {
                System.out.println("Low stock for: " + product.getName() + ". Quantity: " + product.getQuantity());
                System.out.println("Notify to restock " + product.getName() + "!");
            }
        }
    }

   public void generateSalesReport() {
    System.out.println("Sales Report:");
    for (Map.Entry<String, Product> entry : products.entrySet()) {
        Product product = entry.getValue();
        int initialQuantity = product.getQuantity();
        int quantitySold = initialQuantity - RESTOCK_THRESHOLD;
        if (quantitySold < 0) {
            quantitySold = 0; // Ensure negative values are treated as 0
        }
        System.out.println("Product: " + product.getName() + " | Quantity sold: " + quantitySold);
    }
}
}


public class RetailStoreManagement {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product Details");
            System.out.println("3. Record Sale");
            System.out.println("4. Generate Sales Report");
            System.out.println("5. Generate Low Stock Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            scanner.nextLine(); // Consume newline left after nextInt()

            switch (choice) {
                case 1:
                    System.out.println("\nAdd Product:");
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter product description: ");
                    String productDescription = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double productPrice = scanner.nextDouble();
                    System.out.print("Enter initial quantity: ");
                    int initialQuantity = scanner.nextInt();

                    Product product = new Product(productName, productDescription, productPrice, initialQuantity);
                    inventory.addProduct(productName, product);
                    break;

                case 2:
                    System.out.println("\nUpdate Product Details:");
                    System.out.print("Enter product name to update details: ");
                    String productNameToUpdate = scanner.nextLine();
                    inventory.updateProductDetails(productNameToUpdate);
                    break;

                case 3:
                    System.out.println("\nRecord Sale:");
                    System.out.print("Enter product name for sale: ");
                    String soldProductName = scanner.nextLine();
                    System.out.print("Enter quantity sold: ");
                    int quantitySold = scanner.nextInt();

                    inventory.recordSale(soldProductName, quantitySold);
                    break;

                case 4:
                    inventory.generateSalesReport();
                    break;

                case 5:
                    inventory.checkStockAndNotify();
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
