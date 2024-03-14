import java.util.ArrayList;



//Здесь разбивка файла
public class FileParser {
    FileReader reader = new FileReader();


    public void parseMonth() {
        MonthlyReport month = new MonthlyReport();


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

