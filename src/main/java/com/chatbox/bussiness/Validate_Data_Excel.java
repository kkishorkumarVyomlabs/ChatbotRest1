package com.chatbox.bussiness;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Validate_Data_Excel
{
	public String getBalance(int c_no,int otp)throws IOException
	{
		System.out.println(c_no);
		String balance="";
		boolean matchFound = false;
		boolean cf=false,of=false;
		InputStream stream = Validate_Data_Excel.class.getResourceAsStream("/BankData.xls");

		HSSFWorkbook wb = new HSSFWorkbook(stream);
		HSSFSheet ws = wb.getSheetAt(0);

		int rowNum = ws.getLastRowNum() + 1;

		int colNum = ws.getRow(0).getLastCellNum();

		int cardnumHeaderIndex = -1, balanceHeaderIndex = -1,otpnumHeaderIndex = -1;
		HSSFRow rowHeader = ws.getRow(0);
		
		for (int j = 0; j < colNum; j++) {
			HSSFCell cell = rowHeader.getCell(j);

			String cellValue = cellToString(cell);
			if ("CARD NO".equalsIgnoreCase(cellValue)) {
				cardnumHeaderIndex = j;

			} else if ("BALANCE".equalsIgnoreCase(cellValue)) {
				balanceHeaderIndex = j;

			}
			else if ("OTP".equalsIgnoreCase(cellValue)) {
				otpnumHeaderIndex = j;

			} 
		}      		
		for (int i = 1; i < rowNum; i++)
		{
			HSSFRow row = ws.getRow(i);
			String cardNumber = cellToString(row.getCell(cardnumHeaderIndex));
			String otpNumber=cellToString(row.getCell(otpnumHeaderIndex));
			int cardnumber = Integer.valueOf((String) cardNumber);
			int otpnumber=Integer.valueOf((String)otpNumber);

			if(cardnumber==c_no)
			{
				cf=true;
				if(otpnumber==otp)
				{
					of=true;
				balance = cellToString(row.getCell(balanceHeaderIndex));
				matchFound = true;
				balance="Thank You...! You are Authorised Your balance is "+balance;
			   }
				
			}

		}
		if(cf==false){
			balance="Sorry...!! You Entered wrong card number";

		}else if(of==false){
			balance="Sorry...!! You Entered wrong OTP number";
		}

		System.out.println("Balance :="+balance);
		/*if (!matchFound) {
			System.out.println("Sorry...!! You Entered wrong card number");
			balance="Sorry...!! You Entered wrong card number";
		}*/
		return balance;
	}
	public static String cellToString(HSSFCell cell) {

		int type;
		Object result = null;
		type = cell.getCellType();

		switch (type) {

		case HSSFCell.CELL_TYPE_NUMERIC:
			result = Integer.valueOf((int) cell.getNumericCellValue())
			.toString();
			break;

		case HSSFCell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;

		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
			break;

		case HSSFCell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();
		}
		return result.toString();
	}
}
