import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


//Здесь разбивка файла
public class FileParser {
    FileReader reader = new FileReader();


    public void parseMonth() {
        MonthlyReport month = new MonthlyReport();
        String[] monthReport = reader.readAllMonthReports();
        HashMap<Integer, ArrayList<String>> item_name = month.item_name;
        HashMap<Integer, ArrayList<Boolean>> is_expense = month.is_expense;
        HashMap<Integer, ArrayList<Integer>> quantity = month.quantity;
        HashMap<Integer, ArrayList<Double>> sum_of_one = month.sum_of_one;
        for (int i = 1; i <= 3; i++){
            String[] lines = monthReport[i].split("\\n");
            System.out.println(lines[0]);
        }

    }

    public void parseYear() {
        YearReport year = new YearReport();
        String[] lines = reader.readYearReports().split("\\n");
        for (int i = 1; i <= 3 * 2 ; i++) {
            String[] lineContents = lines[i].split(",");
            year.month.add(Integer.valueOf(lineContents[0]));
            year.amount.add(Double.valueOf(lineContents[1]));
            year.is_expense.add(Boolean.valueOf(lineContents[2]));
        }
        System.out.println(year.month);
        System.out.println(year.amount);
        System.out.println(year.is_expense);
    }


}

