package cn.bosch.test;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Test {
    public static void main(String[] args) {
        try {
            Reader csvFile = new FileReader("src/main/resources/csv/test.csv");
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(csvFile);
            StringBuilder stringBuilder = new StringBuilder();
            for (CSVRecord record : records) {
                for (String item : record) {
                    if (stringBuilder.length() == 0) {
                        stringBuilder.append(item);
                    } else {
                        stringBuilder.append(";");
                        stringBuilder.append(item);
                    }
                }
                System.out.println(stringBuilder);
                stringBuilder.setLength(0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
