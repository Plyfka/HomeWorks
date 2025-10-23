package org.example.service;

import org.example.model.Transaction;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction t : topExpenses) {
            System.out.println(t.getDescription() + " | " + t.getAmount());
        }
    }

    public static void printMaxMinExpenses(Transaction maxExpense, Transaction minExpense) {
        if (maxExpense != null) {
            System.out.println("Найбільша витрата: " + maxExpense.getDescription() + " | " + maxExpense.getAmount());
        }
        if (minExpense != null) {
            System.out.println("Найменша витрата: " + minExpense.getDescription() + " | " + minExpense.getAmount());
        }
    }

    public static void printMonthlyCategoryReport(List<Transaction> transactions) {
        Map<String, Map<String, Double>> report = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> t.getDate().substring(3, 10),
                        Collectors.groupingBy(Transaction::getDescription,
                                Collectors.summingDouble(Transaction::getAmount))
                ));

        for (String month : report.keySet()) {
            System.out.println("Місяц: " + month);
            for (String category : report.get(month).keySet()) {
                double sum = Math.abs(report.get(month).get(category));
                int stars = (int) (sum / 1000);
                System.out.printf("  %s: %.2f %s%n", category, sum, "*".repeat(Math.max(stars, 1)));
            }
        }
    }
}
