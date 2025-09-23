import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // генерує геттери, сеттери, toString, equals, hashCode
@NoArgsConstructor      // конструктор без параметрів
@AllArgsConstructor     // конструктор з усіма параметрами
public class Category {
    private int id;
    private String name;
}
