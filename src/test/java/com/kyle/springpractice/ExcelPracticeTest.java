package com.kyle.springpractice;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@SpringBootTest
public class ExcelPracticeTest {

    @Test
    public void makeExcelFileTest() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("첫번째 시트");

        byte[] gray = {(byte) 233, (byte) 236, (byte) 239};
        byte[] pink = {(byte) 225, (byte) 173, (byte) 187};
        byte[] yellow = {(byte) 255, (byte) 235, (byte) 168};
        byte[] green = {(byte) 190, (byte) 213, (byte) 192};
        byte[] orange = {(byte) 255, (byte) 172, (byte) 134};

        XSSFCellStyle style = workbook.createCellStyle();

        int startRow = 3;
        int startCell = 3;


        Row row = sheet.createRow(startRow++);

        Cell cell = row.createCell(startCell++);
        cell.setCellValue("데이터입력 시작");
        for (int i = startRow; i < startRow+10; i++) {
            row= sheet.createRow(i);
            for (int j = startCell; j < startCell+5; j++) {
                cell = row.createCell(j);
                cell.setCellValue(i +"  "+j);
            }

        }

        LocalDate localDate = LocalDate.now();
        String filename = String.format("%s_%s.xlsx","test",localDate );

        File file = new File("/Users/kyle/IdeaProjects/spring-practice/out", filename);
        FileOutputStream fileOutput = new FileOutputStream(file);
        workbook.write(fileOutput);
        fileOutput.close();
        workbook.close ();


    }

}
