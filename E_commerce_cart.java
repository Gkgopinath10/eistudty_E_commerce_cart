import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private boolean available;

    public Product(String name, double price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }
}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getaddQuantity(){
        return quantity+=quantity;
    }
}

class ShoppingCart {
    private ArrayList<CartItem> cart;
    private ArrayList<Product> products;

    public ShoppingCart(ArrayList<Product> products) {
        cart = new ArrayList<>();
        this.products = products;
    }

    public void addProduct(String productName, int quantity) {
        Product product = findProductByName(productName);
        if (product != null) {
            CartItem existingCartItem = findCartItemByName(productName);
            if (existingCartItem != null) {
                existingCartItem.getaddQuantity() ;
               
            } else {
                cart.add(new CartItem(product, quantity));
            }
            System.out.println(quantity + " " + productName + "(s) added to your cart.");
        } else {
            System.out.println("Product \"" + productName + "\" not found.");
        }
    }

    public void removeProduct(String productName) {
        CartItem cartItem = findCartItemByName(productName);
        if (cartItem != null) {
            cart.remove(cartItem);
            System.out.println(productName + " removed from your cart.");
        } else {
            System.out.println("Product \"" + productName + "\" not found in your cart.");
        }
    }

    public void viewCart() {
        System.out.println("Cart Items:");
        
        for (CartItem cartItem : cart) {
            System.out.println(cartItem.getProduct().getName() + " x" + cartItem.getQuantity());
        }
        
    }

    public double calculateTotalBill() {
        double total = 0;
        for (CartItem cartItem : cart) {
            total += cartItem.getTotalPrice();
        }
        return total;
    }

    public Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName) && product.isAvailable()) {
                return product;
            }
        }
        return null;
    }

    public CartItem findCartItemByName(String productName) {
        for (CartItem cartItem : cart) {
            if (cartItem.getProduct().getName().equals(productName)) {
                return cartItem;
            }
        }
        return null;
    }
}

public class E_commerce_cart {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 10000, true));
        products.add(new Product("Headphones", 50, true));
        products.add(new Product("SmartPhone", 3000, true));
        

        ShoppingCart cart = new ShoppingCart(products);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the E-commerce Cart System!");
        System.out.println("[{ Name:Laptop     , Price:10000 , Available:true}");
        System.out.println("{  Name:Headphones , Price:50    , Available:true}");
        System.out.println("{  Name:SmartPhone , Price:3000  , Available:true}]");

        while (true) {

            
            System.out.println();
            System.out.println(" -Add item in cart");
            System.out.println(" -Remove items in cart ");
            System.out.println(" -View cartitems");
            System.out.println(" -Checkout ");
            System.out.println(" -Exit ");
            System.out.println();
            System.out.print("Enter action : ");
            String action = scanner.nextLine();

            if (action.equals("Add item in cart")) {
                promptAddProduct(cart, scanner);
            } else if (action.equals("Remove items in cart")) {
                promptRemoveProduct(cart, scanner);
            } else if (action.equals("View cartitems")) {
                cart.viewCart();
            } else if (action.equals("Checkout")) {
                double totalBill = cart.calculateTotalBill();
                System.out.println("Your total bill is $" + totalBill);
            } else if (action.equals("Exit")) {
                break;
            } else {
                System.out.println();
                System.out.println("Invalid action. Please enter :");
                System.out.println();
                
            }
        }

        scanner.close();
    }

    private static void promptAddProduct(ShoppingCart cart, Scanner scanner) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); 

        cart.addProduct(productName, quantity);
    }

    private static void promptRemoveProduct(ShoppingCart cart, Scanner scanner) {
        System.out.print("Enter product name to remove: ");
        String productName = scanner.nextLine();

        cart.removeProduct(productName);
    }
}
