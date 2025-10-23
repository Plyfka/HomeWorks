import org.example.service.TransactionCSVReader;
import org.junit.jupiter.api.Assertions;
import org.example.model.Transaction;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TransactionCSVReaderTest {

    @Test
    public void testReadTransactions() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);
        Assertions.assertFalse(transactions.isEmpty(), "Список транзакцій не повинен бути порожнім");
        Assertions.assertEquals(3, transactions.get(0).getClass().getDeclaredFields().length, "Кожна транзакція повинна мати 3 поля");
    }
}
