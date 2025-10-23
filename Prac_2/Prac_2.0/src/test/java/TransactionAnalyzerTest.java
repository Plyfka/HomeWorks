import org.example.model.Transaction;
import org.example.service.TransactionAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-01-2023", 100, "Доход"),
                new Transaction("02-01-2023", -50, "Витрата"),
                new Transaction("03-01-2023", 150, "Доход")
        );
        double result = TransactionAnalyzer.calculateTotalBalance(transactions);
        Assertions.assertEquals(200, result);
    }

    @Test
    public void testCountTransactionsByMonth() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-02-2023", 50, "Дохід"),
                new Transaction("15-02-2023", -20, "Витрата"),
                new Transaction("05-03-2023", 100, "Дохід")
        );

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        Assertions.assertEquals(2, countFeb);
        Assertions.assertEquals(1, countMar);
    }
}
