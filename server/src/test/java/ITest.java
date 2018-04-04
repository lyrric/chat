import java.util.HashMap;
import java.util.Map;

public class ITest {

    public static void main(String[] args) {
        Map<Integer, Student> map = new HashMap<>();

    }
}
class Student{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}