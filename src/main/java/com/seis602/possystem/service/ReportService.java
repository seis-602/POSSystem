package com.seis602.possystem.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seis602.possystem.model.Product;
import com.seis602.possystem.model.Sale;
import com.seis602.possystem.model.Shift;

@Service
public class ReportService {
	
	private File resourcesDirectory = new File("src/main/resources/static/documents");
	private final String PATH_TO_PROJECT = resourcesDirectory.getAbsolutePath();
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ShiftService shiftService;
	
	@Autowired
	SaleService saleService;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	public String generateInventoryReport() {
		
		long uniqueId =  java.lang.System.currentTimeMillis();
		String fileName = uniqueId + "_inventory_report.xlsx";
		
		List<Product> products = productService.getAllProducts();
	    
	    String[] titles = {"Name", "Category", "Quantity", "Supplier", "Pending Order", "Threshold"};
	    
	    XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Inventory Report");
        
        final Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        final CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        headerStyle.setFont (font);
        
        int rowNum = 0;
        System.out.println("Creating excel");
        
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.setHeightInPoints(12.75f);
        for (int i = 0; i < titles.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(headerStyle);
        }
        
        for (Product product : products) {
        	
        	Row row = sheet.createRow(rowNum++);
        	
        	// "Name", "Category", "Quantity", "Supplier", "Pending Order", "Threshold"
        	Cell cell_1 = row.createCell(0);
        	cell_1.setCellValue(product.getName());
        	Cell cell_2 = row.createCell(1);
        	cell_2.setCellValue(product.getCategory());
        	Cell cell_3 = row.createCell(2);
        	cell_3.setCellValue(product.getRemaining());
        	Cell cell_4 = row.createCell(3);
        	cell_4.setCellValue(product.getSupplier());
        	Cell cell_5 = row.createCell(4);
        	cell_5.setCellValue(product.getRequestedAmount());
        	Cell cell_6 = row.createCell(5);
        	cell_6.setCellValue(product.getThreshold());
        }
        
        // This sets the width of the columns to match the length of the text
        for(int i =0; i < titles.length; i++) {
        	sheet.autoSizeColumn(i);
        }
        // Freeze just one row
        sheet.createFreezePane(0, 1);

        try {
        	FileOutputStream outputStream = new FileOutputStream(PATH_TO_PROJECT + "/" + fileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        
        return fileName;
		
	}
	
	
	public String generateShiftsReport() {
		
		long uniqueId =  java.lang.System.currentTimeMillis();
		String fileName = uniqueId + "_shifts_report.xlsx";
		
		List<Shift> shifts = shiftService.getAllShifts();
		
	    String[] titles = {"Shift Id", "First Name", "Last Name", "Drawer Amount", "Start Time", "Shift Duration"};
	    
	    XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Cashier Report");
        
        final Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        final CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        headerStyle.setFont (font);
        
        int rowNum = 0;
        System.out.println("Creating excel");
        
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.setHeightInPoints(12.75f);
        for (int i = 0; i < titles.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(headerStyle);
        }
        
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        for (Shift shift : shifts) {
        	
        	Row row = sheet.createRow(rowNum++);
        	
        	// "First Name", "Last Name", "Shift Id", "Drawer Amount", "Shift Duration"
        	Cell cell_1 = row.createCell(0);
        	cell_1.setCellValue(shift.getUser().getFirstName());
        	Cell cell_2 = row.createCell(1);
        	cell_2.setCellValue(shift.getUser().getLastName());
        	Cell cell_3 = row.createCell(2);
        	cell_3.setCellValue(shift.getId());
        	Cell cell_4 = row.createCell(3);
        	cell_4.setCellValue(this.centsToDollar(shift.getDrawerAmount()));
        	Cell cell_5 = row.createCell(4);
        	cell_5.setCellValue(simpleDateFormat.format(shift.getStartTime()));
        	Cell cell_6 = row.createCell(5);
        	cell_6.setCellValue(shift.getDuration());
        }
        
        // This sets the width of the columns to match the length of the text
        for(int i =0; i < titles.length; i++) {
        	sheet.autoSizeColumn(i);
        }
        // Freeze just one row
        sheet.createFreezePane(0, 1);

        try {
            FileOutputStream outputStream = new FileOutputStream(PATH_TO_PROJECT + "/" + fileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        
        return fileName;
		
	}
	
	
	public String generateSalesReport() {
		long uniqueId =  java.lang.System.currentTimeMillis();
		String fileName = uniqueId + "_sales_report.xlsx";
		
		List<Sale> sales = saleService.getAllSales();
		
	    String[] titles = {"Sale Id", "Register ID", "Cashier", "Sale Amount", "Date"};
	    
	    XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Cashier Report");
        
        final Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        final CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        headerStyle.setFont (font);
        
        int rowNum = 0;
        System.out.println("Creating excel");
        
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.setHeightInPoints(12.75f);
        for (int i = 0; i < titles.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(headerStyle);
        }
        
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        for (Sale sale : sales) {
        	
        	Row row = sheet.createRow(rowNum++);
        	
        	// "Sale Id", "Register ID", "Cashier", "Sale Amount"
        	Cell cell_1 = row.createCell(0);
        	cell_1.setCellValue(sale.getId());
        	Cell cell_2 = row.createCell(1);
        	cell_2.setCellValue(sale.getCashRegister().getId() + " (" + sale.getCashRegister().getName() + ")");
        	Cell cell_3 = row.createCell(2);
        	cell_3.setCellValue(sale.getUser().getFirstName() + " " + sale.getUser().getLastName());
        	Cell cell_4 = row.createCell(3);
        	cell_4.setCellValue(this.centsToDollar(sale.getSaleTotal()));
        	Cell cell_5 = row.createCell(4);
        	cell_5.setCellValue(simpleDateFormat.format(sale.getDateOfSale()));
        }
        
        // This sets the width of the columns to match the length of the text
        for(int i =0; i < titles.length; i++) {
        	sheet.autoSizeColumn(i);
        }
        // Freeze just one row
        sheet.createFreezePane(0, 1);

        try {
            FileOutputStream outputStream = new FileOutputStream(PATH_TO_PROJECT + "/" + fileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        
        return fileName;
	}
	
	private String centsToDollar(int cents) {
		DecimalFormat d = new DecimalFormat("'$'0.00");
		return d.format((double) cents/100);
	}
	
}
