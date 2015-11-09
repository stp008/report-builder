package ru.mmsv.report.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	
	private static Log logger = LogFactory.getLog(ExcelHelper.class);
	
	public static Workbook createWorkbook(String shName) {
		Workbook workbook = new XSSFWorkbook();
		workbook.createSheet(shName);		
		logger.info("Created workbook with name: " + shName);
		return workbook;
	}
	
	public static Workbook openWorkbook(String path) throws IOException {
		Workbook workbook = new XSSFWorkbook(new FileInputStream(path));
		logger.info("Workbook with name " + path + " opened");
		return workbook;
	}
	
	public static FileOutputStream writeToWorkbook(Workbook wb, String fileName) throws Exception {
		FileOutputStream fileOut = null;
		fileOut = new FileOutputStream(fileName);
		wb.write(fileOut);
		fileOut.close();	
		logger.info("Written to workbook with name:  " + fileName);
		return fileOut;
	}
	
	public static void insertCell(Sheet sheet, int colNumber, int rowNumber, String value) {
		logger.info("Inserting value " + value + " into row: " + rowNumber + " and column " + colNumber);
		Row row = sheet.getRow(rowNumber);
		if (row == null)
			row = sheet.createRow(rowNumber);
		Cell cell = row.createCell(colNumber);
		cell.setCellValue(value);
	}
	
	public static void insertCell(Workbook wb, Sheet sheet, int colNumber, int rowNumber, String value, String fontName, int fontHeight) {
		logger.info("Inserting value " + value + " into row: " + rowNumber + " and column " + colNumber);
		Row row = sheet.getRow(rowNumber);
		if (row == null)
			row = sheet.createRow(rowNumber);
		Cell cell = row.createCell(colNumber);
		setFont(wb, cell, fontName, fontHeight);
		cell.setCellValue(value);
	}
	
	public static void setFont(Workbook wb, Cell cell, String fontName, int fontHeight) {
		    Font font =  wb.createFont();
		    font.setFontName(fontName);
		    font.setFontHeightInPoints((short)fontHeight);
		    CellStyle cellStyle = wb.createCellStyle();
		    cellStyle.setFont(font);
		    cellStyle.setWrapText(true);
		    cell.setCellStyle(cellStyle);
	}
}
