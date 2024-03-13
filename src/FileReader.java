//Чтение файла будет здесь

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
     String[] monthReports = new String[12];
     String yearReports;


    public String[] readAllMonthReports(){
        for (int i = 0; i <= 11; i++){
            monthReports[i] = readFile("m.20210" + (i + 1) + ".csv"); //Читаем файл и пишем его содержимое в массив
        }
        return monthReports;
    }
    private String readFile(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
