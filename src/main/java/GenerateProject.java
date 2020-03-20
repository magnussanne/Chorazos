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
    private static final String FilePath = "src/main/resources/projects.xlsx";
    private static final GenerateProject SINGLE = new GenerateProject();

    public static GenerateProject getSingle() {
        return SINGLE;
    }

    private GenerateProject(){}

    public static void main(String args[]) throws IOException {}
    
    public static void writeProjectsToSpreadsheet(List<Staff> staffList){
        Workbook workbook = new XSSFWorkbook();
        Sheet staffSheet = workbook.createSheet("Staff");

        int rowIndex = 0;
        for(Staff staff: staffList){
            Row row = staffSheet.createRow(rowIndex++);
            int cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(staff.getName());
        }
        try {
            FileOutputStream output = new FileOutputStream(FilePath);
            workbook.write(output);
            output.close();

            System.out.println(FilePath + " successful");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
