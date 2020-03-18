package Chorazos;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadStaffMembers {
	private static final String File_Path = "StaffMembers.xlsx";
	 public static void main(String[] args){
		 	List staffList = getStaffDetails();
		 	System.out.println(staffList);
	    }
	 private static List getStaffDetails() {
		 List staffList = new ArrayList();
		 FileInputStream fis = null;
		 Workbook workbook = new XSSFWorkbook(fis);
		 
         int numberOfSheets = workbook.getNumberOfSheets();

         for (int i = 0; i < numberOfSheets; i++) {
             Sheet sheet = workbook.getSheetAt(i);
             Iterator rowIterator = sheet.iterator();

             while (rowIterator.hasNext()) {

                 Staff staff = new Staff();
                 Row row = rowIterator.next();
                 Iterator cellIterator = row.cellIterator();

                 while (cellIterator.hasNext()) {

                     Cell cell = cellIterator.next();
                     if (Cell.CELL_TYPE_STRING == cell.getCellType()) {

                         if (cell.getColumnIndex() == 0) {
                        	 staff.setStaffMember(cell.getStringCellValue());
                         }
                         else if (cell.getColumnIndex() == 1) {
                        	 staff.setResearchActivity(cell.getStringCellValue());
                         }
                         else if (cell.getColumnIndex() == 2) {
                        	 staff.setResearchArea(cell.getStringCellValue());
                         }
                         else if (cell.getColumnIndex() == 3) {
                        	 staff.setSpecialFocus(cell.getStringCellValue());
                         }
                     }
                 }
                 staffList.add(staff);
             }
         }
         fis.close();
         return staffList;
	 }
}
