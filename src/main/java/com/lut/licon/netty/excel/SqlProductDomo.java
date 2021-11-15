package com.lut.licon.netty.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/8/17 13:51
 */
public class SqlProductDomo {
	public static void main(String[] args) {
		String sourcePath = "C:\\Users\\lvshaowei\\Desktop\\1.xlsx";
		try(FileOutputStream fo = new FileOutputStream("C:\\Users\\lvshaowei\\Desktop\\test.sql")) {
			XSSMethod(sourcePath,fo);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void HSSMethod(String sourcePath,FileOutputStream fo) throws IOException{
		HSSFSheet sheet = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(sourcePath);
			HSSFWorkbook sheets = new HSSFWorkbook(fileInputStream);
			//XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
			//获取sheet
			sheet = sheets.getSheetAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//获取行数
		int rows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < rows; i++) {
			HSSFRow row = sheet.getRow(i);
			String rawValue1 = row.getCell(0).getStringCellValue();
			String rawValue2 = row.getCell(1).getStringCellValue();
			System.out.println(rawValue1+":"+rawValue2);
		}
	}

	/*public static void XSSMethod(String sourcePath,FileOutputStream fo) throws IOException {
		XSSFSheet sheet = null;

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(sourcePath);
			XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
			//XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
			//获取sheet
			sheet = sheets.getSheetAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//获取行数
		int rows = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < rows; i++) {
			XSSFRow row = sheet.getRow(i);
			String rawValue1 = row.getCell(0).getStringCellValue();
			String rawValue2 = row.getCell(1).getRawValue();
			String sql = "update goods set PRODUCT_TYPE = '"+rawValue2+"' where SKU = '"+rawValue1+"';\r\n";
			byte[] bytes = sql.getBytes();
			fo.write(bytes);
		}
	}*/

	public static void XSSMethod(String sourcePath,FileOutputStream fo) throws IOException {
		XSSFSheet sheet = null;

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(sourcePath);
			XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
			sheet = sheets.getSheetAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//获取行数
		int rows = sheet.getPhysicalNumberOfRows();
		StringBuffer sb = new StringBuffer();
		sb.append("update goods set PRODUCT_TYPE = '1' where SKU in (");
		for (int i = 1; i < rows; i++) {
			XSSFRow row = sheet.getRow(i);
			String rawValue1 = row.getCell(0).getStringCellValue();
			sb.append("'");
			sb.append(rawValue1);
			sb.append("',");
		}
		String substring = sb.substring(0, sb.length() - 1);
		fo.write(substring.getBytes());
	}
}
