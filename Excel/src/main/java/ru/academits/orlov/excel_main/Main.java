package ru.academits.orlov.excel_main;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.academits.orlov.excel.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Person> personsList = List.of(
                new Person("Бердымухамедова", "Аполлинария", 34, "+7(923)111-22-33"),
                new Person("Коптева", "Елена", 31, "+7(923)222-55-00"),
                new Person("Иванов", "Павел", 22, "+7(923)000-66-99"),
                new Person("Петров", "Антон", 55, "+7(923)111-44-55"),
                new Person("Орлов", "Евгений", 17, "+7(913)898-12-34")
        );

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream("Excel/persons.xlsx")) {
            Sheet personsSheet = workbook.createSheet("Persons");
            Row headerRow = personsSheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();

            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setRightBorderColor(IndexedColors.PLUM.getIndex());
            headerCellStyle.setBorderRight(BorderStyle.THIN);
            headerCellStyle.setBottomBorderColor(IndexedColors.PLUM.getIndex());
            headerCellStyle.setBorderBottom(BorderStyle.THIN);

            XSSFFont headerFont = (XSSFFont) workbook.createFont();
            headerFont.setFontName("Helvetica");
            headerFont.setFontHeightInPoints((short) 14);
            headerCellStyle.setFont(headerFont);

            List<String> tableHeaders = List.of("Фамилия", "Имя", "Телефон", "Возраст");
            int tableHeaderCount = tableHeaders.size();

            for (int i = 0; i < tableHeaderCount; i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(tableHeaders.get(i));
                headerCell.setCellStyle(headerCellStyle);
            }

            CellStyle regularCellStyle = workbook.createCellStyle();
            regularCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            regularCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            regularCellStyle.setRightBorderColor(IndexedColors.PLUM.getIndex());
            regularCellStyle.setBorderRight(BorderStyle.DOTTED);
            regularCellStyle.setBottomBorderColor(IndexedColors.PLUM.getIndex());
            regularCellStyle.setBorderBottom(BorderStyle.DOTTED);

            for (int i = 0; i < personsList.size(); i++) {
                Person currentPerson = personsList.get(i);
                Row currentPersonRow = personsSheet.createRow(i + 1);

                Cell surnameCell = currentPersonRow.createCell(0);
                surnameCell.setCellValue(currentPerson.surname());
                surnameCell.setCellStyle(regularCellStyle);

                Cell nameCell = currentPersonRow.createCell(1);
                nameCell.setCellValue(currentPerson.name());
                nameCell.setCellStyle(regularCellStyle);

                Cell phoneNumberCell = currentPersonRow.createCell(2);
                phoneNumberCell.setCellValue(currentPerson.phoneNumber());
                phoneNumberCell.setCellStyle(regularCellStyle);

                Cell ageCell = currentPersonRow.createCell(3);
                ageCell.setCellValue(currentPerson.age());
                ageCell.setCellStyle(regularCellStyle);
            }

            IntStream.range(0, 4).forEach(personsSheet::autoSizeColumn);

            workbook.write(outputStream);
        } catch (IOException e) {
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}