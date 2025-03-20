package ru.academits.orlov.excel_main;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
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

        try (HSSFWorkbook workbook = new HSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream("Excel/persons.xls")) {
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

            HSSFFont headerFont = workbook.createFont();
            headerFont.setFontName("Helvetica");
            headerFont.setFontHeightInPoints((short) 14);
            headerCellStyle.setFont(headerFont);

            Cell headerCell = headerRow.createCell(0);
            headerCell.setCellValue("Фамилия");
            headerCell.setCellStyle(headerCellStyle);

            headerCell = headerRow.createCell(1);
            headerCell.setCellValue("Имя");
            headerCell.setCellStyle(headerCellStyle);

            headerCell = headerRow.createCell(2);
            headerCell.setCellValue("Телефон");
            headerCell.setCellStyle(headerCellStyle);

            headerCell = headerRow.createCell(3);
            headerCell.setCellValue("Возраст");
            headerCell.setCellStyle(headerCellStyle);

            CellStyle regularCellStyle = workbook.createCellStyle();
            regularCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            regularCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            regularCellStyle.setRightBorderColor(IndexedColors.PLUM.getIndex());
            regularCellStyle.setBorderRight(BorderStyle.DOTTED);
            regularCellStyle.setBottomBorderColor(IndexedColors.PLUM.getIndex());
            regularCellStyle.setBorderBottom(BorderStyle.DOTTED);

            for (int i = 0; i < personsList.size(); i++) {
                Row personRow = personsSheet.createRow(i + 1);

                Cell surnameCell = personRow.createCell(0);
                surnameCell.setCellValue(personsList.get(i).getSurname());
                surnameCell.setCellStyle(regularCellStyle);

                Cell nameCell = personRow.createCell(1);
                nameCell.setCellValue(personsList.get(i).getName());
                nameCell.setCellStyle(regularCellStyle);

                Cell phoneNumberCell = personRow.createCell(2);
                phoneNumberCell.setCellValue(personsList.get(i).getPhoneNumber());
                phoneNumberCell.setCellStyle(regularCellStyle);

                Cell ageCell = personRow.createCell(3);
                ageCell.setCellValue(personsList.get(i).getAge());
                ageCell.setCellStyle(regularCellStyle);
            }

            IntStream.range(0, 4).forEach(personsSheet::autoSizeColumn);

            workbook.write(outputStream);
        } catch (IOException e) {
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }
}