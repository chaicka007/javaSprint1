import java.util.ArrayList;


//Здесь разбивка файла
public class DataManager {
    String[] monthName = {"январь", "февраль", "март"};
    FileReader reader = new FileReader();
    MonthlyReport month = new MonthlyReport();
    YearReport year = new YearReport();

    public void parseMonth() {

        String[] monthReport = reader.readAllMonthReports();
        year.year = 2021;

        for (int i = 1; i <= 3; i++) {
            String[] lines = monthReport[i - 1].split("\\n");
            ArrayList<String> item_name = new ArrayList<>();
            ArrayList<Boolean> is_expense = new ArrayList<>();
            ArrayList<Integer> quantity = new ArrayList<>();
            ArrayList<Double> sum_of_one = new ArrayList<>();
            int j = 0; //нужна для пропуска первой итерации цикла
            for (String line : lines) {
                if (j == 0) {
                    j++;
                    continue;
                }
                String[] lineContents = line.split(",");
                item_name.add(lineContents[0]);
                is_expense.add(Boolean.valueOf(lineContents[1]));
                quantity.add(Integer.valueOf(lineContents[2]));
                sum_of_one.add(Double.valueOf(lineContents[3]));
            }
            month.item_name.put(i, item_name);
            month.is_expense.put(i, is_expense);
            month.quantity.put(i, quantity);
            month.sum_of_one.put(i, sum_of_one);
        }
        month.isReaded = true; // переменная, что отчет прочитан
        System.out.println("Успешное чтение месячных отчетов");
    }

    public void parseYear() {

        String[] lines = reader.readYearReports().split("\\n");
        for (int i = 1; i <= 3 * 2; i++) {
            String[] lineContents = lines[i].split(",");
            year.month.add(Integer.valueOf(lineContents[0]));
            year.amount.add(Double.valueOf(lineContents[1]));
            year.is_expense.add(Boolean.valueOf(lineContents[2].trim())); //trim() чтобы убрать пробел, если его оставить, то всегда false

        }
        year.isReaded = true;
        System.out.println("Успешное чтение годового отчета");
    }

    public void infoMonthly() {
        if (!month.isReaded) {
            System.out.println("Месячные отчеты еще не прочитаны!"); //Проврка на чтение отчетов
            return;
        }
        System.out.printf("Информация о месячных отчетах\n");
        for (int i = 1; i <= 3; i++) {
            System.out.println(monthName[i - 1] + ":");
            ArrayList<Boolean> is_expenses = month.is_expense.get(i);
            ArrayList<String> item_name = month.item_name.get(i);
            ArrayList<Integer> quantity = month.quantity.get(i);
            ArrayList<Double> sum_of_one = month.sum_of_one.get(i);

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
        if (!year.isReaded) {
            System.out.println("Годовые отчеты еще не прочитаны!");
            return; //Проврка на чтение отчетов
        }
        System.out.println("Информация о годовом отчете за " + year.year + ":");
        double allMonthExpense = 0;
        double allMonthRevenue = 0;

        for (int i = 0; i < year.month.size(); i++) {

            if (!year.is_expense.get(i)) {
                allMonthRevenue += year.amount.get(i);
            } else {
                allMonthExpense += year.amount.get(i);
            }
        }
        double averageRevenue = allMonthRevenue / (year.month.size() / 2);
        double averageExpense = allMonthExpense / (year.month.size() / 2);
        ArrayList<Double> monthRevenue = new ArrayList<>();
        for (int i = 0; i < year.month.size(); i += 2) {
            if (!year.is_expense.get(i)) {
                monthRevenue.add(year.amount.get(i) - year.amount.get(i + 1));//если доход в 1 ячейке, то отнимаем 2
            } else {
                monthRevenue.add(year.amount.get(i + 1) - year.amount.get(i)); //если во 2, то 1
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
        if (!month.isReaded) {
            System.out.println("Месячные отчеты еще не прочитаны!");
            return;
        }
        if (!year.isReaded) {
            System.out.println("Годовые отчеты еще не прочитаны!");
            return;
        }
        System.out.println("Сверка отчетов:");
        for (int i = 1; i <= 3; i++) {
            ArrayList<Boolean> is_expenses = month.is_expense.get(i);
            ArrayList<Integer> quantity = month.quantity.get(i);
            ArrayList<Double> sum_of_one = month.sum_of_one.get(i);
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
            if (!year.is_expense.get(yearIterator)) {
                if (monthRevenue == year.amount.get(yearIterator) && monthExpenses == year.amount.get(yearIterator + 1)) { //сверка отяетов, если доход 1
                    continue;
                } else System.out.println("Ошибка в отчете за " + monthName[i - 1]);
            } else {
                if (monthExpenses == year.amount.get(yearIterator) && monthRevenue == year.amount.get(yearIterator + 1)) { //сверка, если расход 1
                    continue;
                } else System.out.println("Ошибка в отчете за " + monthName[i - 1]);
            }

        }
        System.out.println("Сверка завершена");
    }
}




