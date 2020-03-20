import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Interface.Focus;
import Objects.Staff;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadStaffMembers {
    private static final String FilePath = "src/main/resources/staff_input.xlsx";

	public static void main(String[] args) throws IOException {
        List staffList = getStaffDetails();
        System.out.println(staffList);
	}

	 public static List getStaffDetails() throws IOException {
		 List staffList = new ArrayList();
		 FileInputStream input = null;

		 try {
             input = new FileInputStream(FilePath);
             Workbook workbook = new XSSFWorkbook(input);

             int numberOfSheets = workbook.getNumberOfSheets();

             for (int i = 0; i < numberOfSheets; i++) {
                 Sheet sheet = workbook.getSheetAt(i);
                 Iterator rowIterator = sheet.iterator();

                 while (rowIterator.hasNext()) {


                     Row row = (Row) rowIterator.next();
                     Iterator cellIterator = row.cellIterator();

                     while (cellIterator.hasNext()) {
                         Staff staff;
                         Cell cell = (Cell) cellIterator.next();

                         String name = "";
                         String activity = "";
                         String area = "";
                         Focus focus = null;
                             if (cell.getColumnIndex() == 0) {
                                 name = cell.getStringCellValue();
                             }
                             else if (cell.getColumnIndex() == 1) {
                                 activity = cell.getStringCellValue();
                             }
                             else if (cell.getColumnIndex() == 2) {
                                 area = cell.getStringCellValue();
                             }
                             else if (cell.getColumnIndex() == 3) {
                                 String f = cell.getStringCellValue();

                                 if (f.equals("Dagon Studies"))
                                     focus = Focus.DS;
                                 else {
                                     focus = Focus.CS;
                                 }
                             }

                         staff = new Staff(name, activity, area, focus);
                         staffList.add(staff);
                     }
                 }
             }

             input.close();
		 } catch (FileNotFoundException e) {
            e.printStackTrace();
		 } catch (IOException e) {
            e.printStackTrace();
    }

         return staffList;
	 }
}
