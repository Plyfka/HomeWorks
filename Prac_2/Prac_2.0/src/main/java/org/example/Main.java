package org.example;
import org.example.service.TransactionReportGenerator;
import org.example.model.Transaction;
import org.example.service.TransactionAnalyzer;
import org.example.service.TransactionCSVReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        String month = "01-2024";
        String startDate = "01-01-2024";
        String endDate = "31-01-2024";

        int count = TransactionAnalyzer.countTransactionsByMonth(transactions, month);
        TransactionReportGenerator.printTransactionsCountByMonth(month, count);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        Transaction maxExpense = TransactionAnalyzer.findMaxExpenseByPeriod(transactions, startDate, endDate);
        Transaction minExpense = TransactionAnalyzer.findMinExpenseByPeriod(transactions, startDate, endDate);
        TransactionReportGenerator.printMaxMinExpenses(maxExpense, minExpense);

        System.out.println("\nВитрати по місяцях і категоріях:");
        TransactionReportGenerator.printMonthlyCategoryReport(transactions);
    }
}