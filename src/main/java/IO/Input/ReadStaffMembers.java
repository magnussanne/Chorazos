package IO.Input;

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
    private static final String FilePath = "src/main/resources/input/staff_input.xlsx";

    public static void main(String[] args) throws IOException {
        List<Staff> staffList = getStaffDetails();
        Staff s = staffList.get(2);
        System.out.println(s.toString());
    }

    public static List getStaffDetails() throws IOException {
        List<Staff> staffList = new ArrayList();
        FileInputStream input = null;

        try {
            input = new FileInputStream(FilePath);
            //creating a workbook containing the input file to read from
            Workbook workbook = new XSSFWorkbook(input);
            //making sure the program reads from each sheet inside the workbook
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                Iterator rowIterator = sheet.iterator();

                while (rowIterator.hasNext()) {
                    Staff staff;
                    String name = "";
                    String activity = "";
                    String area = "";
                    Focus focus = null;

                    Row row = (Row) rowIterator.next();
                    Iterator cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = (Cell) cellIterator.next();

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
                            String f = cell.getStringCellValue().trim();

                            if (f.equals("Dagon Studies"))
                                focus = Focus.DS;
                        }
                    }

                    if(focus == null)
                        focus = Focus.CS;
                    staff = new Staff(name, activity, area, focus);
                    System.out.println(staff.toString());
                    staffList.add(staff);
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: operation failed");
            e.printStackTrace();
        }

        return staffList;
    }
}