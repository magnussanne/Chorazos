package IO.Output.Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Interface.Focus;
import Objects.Student;
import Objects.Project;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenerateStudentPref {
    private static final String FilePath = "src/main/resources/output/studentPref.xlsx";

    public static void writeStudentPrefToSpreadsheet(List<Student> studentList, int limit){
        //creating a workbook to write to
        Workbook workbook = new XSSFWorkbook();
        //creating a sheet inside the workbook
        Sheet studentSheet = workbook.createSheet("Student");

        int rowIndex = 0;
        for(Student student: studentList){
            Row row = studentSheet.createRow(rowIndex++);
            int cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(student.getName());
            row.createCell(cellIndex++).setCellValue(student.getNumber());
            row.createCell(cellIndex++).setCellValue(student.getFocus().toString());
            row.createCell(cellIndex++).setCellValue(student.getPreference(0).getTitle());
            row.createCell(cellIndex++).setCellValue(student.getPreference(1).getTitle());
            row.createCell(cellIndex++).setCellValue(student.getPreference(2).getTitle());
            row.createCell(cellIndex++).setCellValue(student.getPreference(3).getTitle());
            row.createCell(cellIndex++).setCellValue(student.getPreference(4).getTitle());
            row.createCell(cellIndex++).setCellValue(student.getPreference(5).getTitle());
            row.createCell(cellIndex++).setCellValue(student.getPreference(6).getTitle());
            row.createCell(cellIndex++).setCellValue(student.getPreference(7).getTitle());
            row.createCell(cellIndex++).setCellValue(student.getPreference(8).getTitle());
            row.createCell(cellIndex).setCellValue(student.getPreference(9).getTitle());
            if(rowIndex == limit){
                break;
            }
        }
        try {
            FileOutputStream output = new FileOutputStream(FilePath);
            workbook.write(output);
            output.close();

            System.out.println(FilePath + " successful");
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: operation failed");
            e.printStackTrace();
        }
    }
}
