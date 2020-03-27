package IO.Output.Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Interface.Focus;
import Objects.Staff;
import Objects.Project;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenerateProject {
    private static final String FilePath = "src/main/resources/input/projects.xlsx";

    public static void writeProjectsToSpreadsheet(List<Staff> staffList){
        //creating a workbook to write too
        Workbook workbook = new XSSFWorkbook();
        //creating a sheet inside the workbook
        Sheet staffSheet = workbook.createSheet("Staff");

        int rowIndex = 0;
        for(Staff staff: staffList){
            Row row = staffSheet.createRow(rowIndex++);
            int cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(staff.getName());
            row.createCell(cellIndex++).setCellValue(staff.getProject(0).getTitle());
            row.createCell(cellIndex).setCellValue(staff.getProject(0).getFocus().toString());
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
