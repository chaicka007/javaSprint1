import java.util.ArrayList;


//Здесь разбивка файла
public class DataManager {
    private final String[] monthName = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
    private final FileReader reader = new FileReader();
    private final MonthlyReport month = new MonthlyReport();
    private final YearReport year = new YearReport();

    private int monthCount;
    private int yearName;

    public int getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public int getYearName() {
        return yearName;
    }

    public void setYearName(int yearName) {
        this.yearName = yearName;
    }

    public void parseMonth() {

        String[] monthReport = reader.readAllMonthReports(monthCount);
        year.setYear(yearName);

        for (int i = 1; i <= 3; i++) {
            String[] lines = monthReport[i - 1].split("\\n");
            ArrayList<String> item_name = new ArrayList<>();
            ArrayList<Boolean> is_expense = new ArrayList<>();
            ArrayList<Integer> quantity = new ArrayList<>();
            ArrayList<Double> sum_of_one = new ArrayList<>();

            for (int j = 1; j < lines.length; j++) {
                String[] lineContents = lines[j].split(",");
                item_name.add(lineContents[0]);
                is_expense.add(Boolean.valueOf(lineContents[1]));
                quantity.add(Integer.valueOf(lineContents[2]));
                sum_of_one.add(Double.valueOf(lineContents[3]));
            }
            month.putItemName(i, item_name);
            month.putIsExpense(i, is_expense);
            month.putQuantity(i, quantity);
            month.putSumOfOne(i, sum_of_one);
        }
        month.setRead(true); // переменная, что отчет прочитан
        System.out.println("Успешное чтение месячных отчетов");
    }

    public void parseYear() {

        String[] lines = reader.readYearReports().split("\\n");
        for (int i = 1; i <= 3 * 2; i++) {
            String[] lineContents = lines[i].split(",");
            year.addMonth(Integer.valueOf(lineContents[0]));
            year.addAmount(Double.valueOf(lineContents[1]));
            year.addIsExpense(Boolean.valueOf(lineContents[2].trim())); //trim() чтобы убрать пробел, если его оставить, то всегда false

        }
        year.setRead(true);
        System.out.println("Успешное чтение годового отчета");
    }

    public void infoMonthly() {
        if (!month.isRead()) {
            System.out.println("Месячные отчеты еще не прочитаны!"); //Проврка на чтение отчетов
            return;
        }
        System.out.print("Информация о месячных отчетах\n");
        for (int i = 1; i <= 3; i++) {
            System.out.println(monthName[i - 1] + ":");
            ArrayList<Boolean> is_expenses = month.getIsExpense().get(i);
            ArrayList<String> item_name = month.getItemName().get(i);
            ArrayList<Integer> quantity = month.getQuantity().get(i);
            ArrayList<Double> sum_of_one = month.getSumOfOne().get(i);

            double maxRevenue = 0;
            int maxRevenueID = 0;
            double maxExpense = 0;
            int maxExpenseID = 0;
            for (int j = 0; j < is_expenses.size(); j++) {
                double finalValue = quantity.get(j) * sum_of_one.get(j);
                if (!is_expenses.get(j)) { //проверяем что это не трата и ищем максимальную прибыль, а если трата то ищем максимальную трату
                    if (finalValue > maxRevenue) {
                        maxRevenue = finalValue;
                        maxRevenueID = j;
                    }
                } else {
                    if (finalValue > maxExpense) {
                        maxExpense = finalValue;
                        maxExpenseID = j;
                    }

                }
            }
            System.out.println("Максимальная продажа: " + item_name.get(maxRevenueID) + " на сумму: " + maxRevenue);
            System.out.println("Максимальная потеря деняг: " + item_name.get(maxExpenseID) + " на сумму: " + maxExpense);
        }
    }

    public void infoYear() {
        if (!year.isRead()) {
            System.out.println("Годовые отчеты еще не прочитаны!");
            return; //Проврка на чтение отчетов
        }
        System.out.println("Информация о годовом отчете за " + year.getYear() + ":");
        double allMonthExpense = 0;
        double allMonthRevenue = 0;

        for (int i = 0; i < year.getMonth().size(); i++) {

            if (!year.getIsExpense().get(i)) {
                allMonthRevenue += year.getAmount().get(i);
            } else {
                allMonthExpense += year.getAmount().get(i);
            }
        }
        double averageRevenue = allMonthRevenue / (year.getMonth().size() / 2);
        double averageExpense = allMonthExpense / (year.getMonth().size() / 2);
        ArrayList<Double> monthRevenue = new ArrayList<>();
        for (int i = 0; i < year.getMonth().size(); i += 2) {
            if (!year.getIsExpense().get(i)) {
                monthRevenue.add(year.getAmount().get(i) - year.getAmount().get(i + 1));//если доход в 1 ячейке, то отнимаем 2
            } else {
                monthRevenue.add(year.getAmount().get(i + 1) - year.getAmount().get(i)); //если во 2, то 1
            }
        }
        System.out.println("Средний расход за все месяцы: " + averageExpense);
        System.out.println("Средний доход за все месяцы: " + averageRevenue);
        System.out.println("Прибыль по каждому месяцу");
        int i = 0; //для вывода имени
        for (Double Revenue : monthRevenue) {
            System.out.println(monthName[i]);
            System.out.println(Revenue);
            i++;
        }
    }

    public void compareReports() {
        if (!month.isRead()) {
            System.out.println("Месячные отчеты еще не прочитаны!");
            return;
        }
        if (!year.isRead()) {
            System.out.println("Годовые отчеты еще не прочитаны!");
            return;
        }
        System.out.println("Сверка отчетов:");
        for (int i = 1; i <= 3; i++) {
            ArrayList<Boolean> is_expenses = month.getIsExpense().get(i);
            ArrayList<Integer> quantity = month.getQuantity().get(i);
            ArrayList<Double> sum_of_one = month.getSumOfOne().get(i);
            double monthExpenses = 0;
            double monthRevenue = 0;
            for (int j = 0; j < is_expenses.size(); j++) {
                if (!is_expenses.get(j)) { //проверяем что это не трата и записываем в доход,
                    monthRevenue += quantity.get(j) * sum_of_one.get(j);
                } else {
                    monthExpenses += quantity.get(j) * sum_of_one.get(j);
                }


            }

            int yearIterator = (i - 1) * 2;
            if (!year.getIsExpense().get(yearIterator)) {
                if (monthRevenue == year.getAmount().get(yearIterator) && monthExpenses == year.getAmount().get(yearIterator + 1)) { //сверка отяетов, если доход 1
                    continue;
                } else System.out.println("Ошибка в отчете за " + monthName[i - 1]);
            } else {
                if (monthExpenses == year.getAmount().get(yearIterator) && monthRevenue == year.getAmount().get(yearIterator + 1)) { //сверка, если расход 1
                    continue;
                } else System.out.println("Ошибка в отчете за " + monthName[i - 1]);
            }

        }
        System.out.println("Сверка завершена");
    }
}




