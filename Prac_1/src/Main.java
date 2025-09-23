import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        List<Product> allProducts = Arrays.asList(
                new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics),
                new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones),
                new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories)
        );

        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>(); 

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Видалити товар з кошика");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    for (Product p : allProducts) System.out.println(p);
                    break;

                case 2:
                    System.out.println("Введіть ID товару для додавання:");
                    int addId = scanner.nextInt();
                    Product productToAdd = findProductById(allProducts, addId);
                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println("Товар додано до кошика!");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;

                case 3:
                    System.out.println(cart);
                    break;

                case 4:
                    System.out.println(cart);
                    System.out.println("Введіть ID товару для видалення:");
                    int removeId = scanner.nextInt();
                    Product productToRemove = findProductById(cart.getProducts(), removeId);
                    if (productToRemove != null) {
                        cart.removeProduct(productToRemove);
                        System.out.println("Товар видалено з кошика!");
                    } else {
                        System.out.println("Товар з таким ID відсутній у кошику.");
                    }
                    break;

                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;

                case 6:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("Історія замовлень:");
                        for (Order order : orderHistory) {
                            System.out.println(order);
                            System.out.println("-------------------");
                        }
                    }
                    break;

                case 7:
                    System.out.println("Введіть ключове слово для пошуку (назва або категорія):");
                    String keyword = scanner.nextLine().toLowerCase();
                    boolean found = false;
                    for (Product p : allProducts) {
                        if (p.getName().toLowerCase().contains(keyword) ||
                                p.getCategory().getName().toLowerCase().contains(keyword)) {
                            System.out.println(p);
                            found = true;
                        }
                    }
                    if (!found) System.out.println("Товарів не знайдено.");
                    break;

                case 0:
                    System.out.println("Дякуємо, що використали наш магазин!");
                    return;

                default:
                    System.out.println("Невідома опція.");
            }
        }
    }

    private static Product findProductById(List<Product> products, int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}
