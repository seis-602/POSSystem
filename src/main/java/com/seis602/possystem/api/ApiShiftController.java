package com.seis602.possystem.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seis602.possystem.model.Shift;
import com.seis602.possystem.model.User;
import com.seis602.possystem.service.ShiftService;
import com.seis602.possystem.service.UserService;

@RestController
public class ApiShiftController {
	
	@Autowired
	private ShiftService shiftService;
	
	@RequestMapping(method=RequestMethod.GET, value="/api/shifts")
	public List<Shift> getAllShifts() {
		return shiftService.getAllShifts();	
	}
	
}
