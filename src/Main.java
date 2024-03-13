import java.io.File;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        FileReader reader = new FileReader();
            for (String strings : reader.readAllMonthReports()) {
                System.out.println(strings);
            }

    }
}