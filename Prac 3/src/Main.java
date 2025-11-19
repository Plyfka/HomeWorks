import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль заборонено!");
        }
        return a / b;
    }

    public double squareRoot(double a) throws InvalidInputException {
        if (a < 0) {
            throw new InvalidInputException("Неможливо взяти квадратний корінь з від'ємного числа.");
        }
        return Math.sqrt(a);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.println("--- Калькулятор ---");
            System.out.println("Виберіть операцію:");
            System.out.println("1: Додавання (+)");
            System.out.println("2: Віднімання (-)");
            System.out.println("3: Множення (*)");
            System.out.println("4: Ділення (/)");
            System.out.println("5: Квадратний корінь (sqrt)");
            System.out.print("Ваш вибір (1-5): ");

            int choice = scanner.nextInt();

            double num1, num2, result;

            if (choice >= 1 && choice <= 4) {
                System.out.print("Введіть перше число: ");
                num1 = scanner.nextDouble();
                System.out.print("Введіть друге число: ");
                num2 = scanner.nextDouble();

                switch (choice) {
                    case 1:
                        result = calculator.add(num1, num2);
                        System.out.println("Результат: " + num1 + " + " + num2 + " = " + result);
                        break;
                    case 2:
                        result = calculator.subtract(num1, num2);
                        System.out.println("Результат: " + num1 + " - " + num2 + " = " + result);
                        break;
                    case 3:
                        result = calculator.multiply(num1, num2);
                        System.out.println("Результат: " + num1 + " * " + num2 + " = " + result);
                        break;
                    case 4:
                        result = calculator.divide(num1, num2);
                        System.out.println("Результат: " + num1 + " / " + num2 + " = " + result);
                        break;
                }
            } else if (choice == 5) {
                System.out.print("Введіть число: ");
                num1 = scanner.nextDouble();

                result = calculator.squareRoot(num1);
                System.out.println("Результат: " + result);
            } else {
                System.out.println("Невірний вибір операції.");
            }

        } catch (InputMismatchException e) {
            System.err.println("Помилка вводу: Ви ввели не число. Будь ласка, введіть коректне числове значення.");
        } catch (ArithmeticException e) {
            System.err.println("Арифметична помилка: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.err.println("Помилка вхідних даних: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Сталася непередбачена помилка: " + e.getMessage());
        } finally {
            System.out.println("\nОбробка запиту завершена.");
        }
    }
}