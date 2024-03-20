import java.util.Scanner;

//Здесь будет вывод меню
public class MenuExecutor {
    DataManager manager = new DataManager();
    Scanner scanner = new Scanner(System.in);
    public void executeMenu() {
        checkYear();
        checkMonth();
        while (true) {
            printMenu();
            System.out.print("Введите цифру, чтобы продолжить: ");
            int command = scanner.nextInt();
            if (command == 1) {
                manager.parseMonth();
            } else if (command == 2) {
                manager.parseYear();
            } else if (command == 3) {
                manager.compareReports();
            } else if (command == 4) {
                manager.infoMonthly();
            } else if (command == 5) {
                manager.infoYear();
            }else if (command == 6){
                checkYear();
                checkMonth();
            } else if (command == 0) {
                System.out.println("До свидания");
                System.exit(0);
            } else {
                System.out.println("Введите правильный номер!");
            }
        }
    }

    private void printMenu() {
        System.out.print("1. Считать все месячные отчёты\n" +
                "2. Считать годовой отчёт\n" +
                "3. Сверить отчёты\n" +
                "4. Вывести информацию о всех месячных отчётах\n" +
                "5. Вывести информацию о годовом отчёте\n" +
                "6. Изменить год отчета и/или кол-во месяцев\n" +
                "0. Выход\n");
    }
    private void checkYear(){
        System.out.println("Введите год ваших отчетов: ");
        manager.setYearName(scanner.nextInt());

    }
    private void checkMonth(){
        System.out.println("Введите количество месяцев, за которые готовы отчеты:");
        int monthCount = (scanner.nextInt());
        if (monthCount < 1 || monthCount > 12){
            System.out.println("Месяцы должны быть от 1 до 12!");
            checkMonth();
        } else{
            manager.setMonthCount(monthCount);
        }

    }
}
