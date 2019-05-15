package com.seis602.possystem.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seis602.possystem.service.ReportService;

@RestController
public class ApiReportController {
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(method=RequestMethod.GET, value="/api/reports/inventory")
	public String getInventoryReport() throws IOException {
		return reportService.generateInventoryReport();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/api/reports/shifts")
	public String getShiftReport() throws IOException {
		return reportService.generateShiftsReport();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/api/reports/sales")
	public String getSaleReport() throws IOException {
		return reportService.generateSalesReport();
	}

}