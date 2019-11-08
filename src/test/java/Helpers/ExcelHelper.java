package Helpers;


import Utilities.RegisterForm;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelHelper {

    public static Object[][] readRegisterData() throws IOException {
        int rowNumbers;
        Row row;
        InputStream inputStream = new FileInputStream(new File("src//main//resources//RegisterForm.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet  = workbook.getSheetAt(0);
        rowNumbers = sheet.getLastRowNum();

        RegisterForm[][] formData = new RegisterForm[rowNumbers][1];
        for (int i = 0; i <rowNumbers ; i++) {
            RegisterForm registerForm = new RegisterForm();
            row = sheet.getRow(i+1);
            registerForm.setTitle(row.getCell(0).getStringCellValue());
            registerForm.setFirstName(row.getCell(1).getStringCellValue());
            registerForm.setLastName(row.getCell(2).getStringCellValue());
            registerForm.setEmail(row.getCell(3).getStringCellValue());
            registerForm.setPassword(row.getCell(4).getStringCellValue());
            registerForm.setDayOfBirth((int)row.getCell(5).getNumericCellValue());
            registerForm.setMonthOfBirth((int)row.getCell(6).getNumericCellValue());
            registerForm.setYearOfBirth((int)row.getCell(7).getNumericCellValue());
            registerForm.setNewsletter(row.getCell(8).getBooleanCellValue());
            registerForm.setSpecialOffers(row.getCell(9).getBooleanCellValue());
            registerForm.setAddressFirstName(row.getCell(10).getStringCellValue());
            registerForm.setAddressLastName(row.getCell(11).getStringCellValue());
            registerForm.setCompany(row.getCell(12).getStringCellValue());
            registerForm.setAddress(row.getCell(13).getStringCellValue());
            registerForm.setCity(row.getCell(14).getStringCellValue());
            registerForm.setState(row.getCell(15).getStringCellValue());
            registerForm.setPostalCode((int)row.getCell(16).getNumericCellValue());
            registerForm.setCountry(row.getCell(17).getStringCellValue());
            registerForm.setInformations(row.getCell(18).getStringCellValue());
            registerForm.setHomePhone((int)row.getCell(19).getNumericCellValue());
            registerForm.setMobilePhone((int)row.getCell(20).getNumericCellValue());
            registerForm.setAddressAlias(row.getCell(21).getStringCellValue());
            formData[i][0] = registerForm;

        }



    return formData;
    }
    public static Object[][] readExcelFile(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNumbers = sheet.getPhysicalNumberOfRows();
        int colNumbers = sheet.getRow(0).getPhysicalNumberOfCells();
        String [][] data = new String[rowNumbers-1][colNumbers];
        DataFormatter formatter= new DataFormatter();


        for (int i = 0; i <rowNumbers-1 ; i++) {
            for (int j = 0; j <colNumbers ; j++) {
                String value;
                Cell cell = sheet.getRow(i+1).getCell(j);
                if (cell==null){
                    value="";
                }
                else {
                    value=formatter.formatCellValue(cell);
                }
                data[i][j] = value;

            }
        }



        return data;
    }



    /*public static void main(String[] args) {
        try {
            Object [][] dane = readExcelFile(new File("src//main//resources//RegisterForm.xlsx"));
            for (int i = 0; i <21 ; i++) {
                System.out.println(dane[0][i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/


}


