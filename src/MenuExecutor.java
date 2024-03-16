import java.util.Scanner;

//Здесь будет вывод меню
public class MenuExecutor {
    DataManager manager = new DataManager();

    public void executeMenu() {
        Scanner scanner = new Scanner(System.in);
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
            } else if (command == 0) {
                System.out.println("До свидания");
                break;
            } else {
                System.out.println("Введите правильный номер!");
            }
        }
    }

    private void printMenu() {
        System.out.printf("1. Считать все месячные отчёты\n" +
                "2. Считать годовой отчёт\n" +
                "3. Сверить отчёты\n" +
                "4. Вывести информацию о всех месячных отчётах\n" +
                "5. Вывести информацию о годовом отчёте\n" +
                "0. Выход\n");
    }
}
