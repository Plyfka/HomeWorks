import org.example.model.Transaction;
import org.example.service.TransactionAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTopExpensesTest {

    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-01-2023", -100, "Витрата1"),
                new Transaction("02-01-2023", -200, "Витрата2"),
                new Transaction("03-01-2023", 50, "Доход")
        );
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        Assertions.assertEquals(2, topExpenses.size());
        Assertions.assertEquals(-200, topExpenses.get(0).getAmount());
    }
}
