package com.cb.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

	private static String CallClassname;

	protected ExcelDataProvider() {
		StackTraceElement[] trace = new Throwable().getStackTrace();
		this.CallClassname = trace[2].getFileName().trim();
	}

	@DataProvider(name = "ExcelData")
	public static Object[][] ExcelData() throws IOException {

		// fileName="C:/Users/niranjan.deka/workspace/myappproj/src/test/resources/TestData/TestData.xlsx";
		// String testexcelname = "JiRaTest";
		String testexcelname = CallClassname.trim().toString().replace(".", "_").split("_")[0];
		String fileName = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\" + testexcelname
				+ ".xlsx";
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(0).getLastCellNum();

		Object[][] obj = new Object[rowcount][1];

		for (int i = 0; i < rowcount; i++) {
			Map<Object, Object> datamap = new HashMap<Object, Object>();

			for (int j = 0; j < colcount; j++) {

				String cellvalue = sheet.getRow(i + 1).getCell(j).toString();
				if (cellvalue.contains(".")) {
					cellvalue = cellvalue.replace(".", "_");
					cellvalue = cellvalue.split("_")[0].trim();
				}

				datamap.put(sheet.getRow(0).getCell(j).toString(), cellvalue);
			}

			obj[i][0] = datamap;

		}

		return obj;

	}

	static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
