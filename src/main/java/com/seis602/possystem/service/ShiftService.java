package com.seis602.possystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seis602.possystem.model.Shift;
import com.seis602.possystem.model.User;
import com.seis602.possystem.repository.ShiftRepository;

@Service
public class ShiftService {

	@Autowired
	private ShiftRepository shiftRepository;

	public List<Shift> getAllShifts() {
		List<Shift> shifts = new ArrayList<>();
		shiftRepository.findAll().forEach(shifts::add);
		return shifts;
	}
	
	public Shift saveShift(Shift shift) {
		return shiftRepository.save(shift);
	}
	
	public Shift getActiveShiftByUser(User user) {
		return shiftRepository.findByUserAndEndTimeIsNull(user).orElse(null);
	}
	
}
