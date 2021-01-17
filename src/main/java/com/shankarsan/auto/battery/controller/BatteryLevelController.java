package com.shankarsan.auto.battery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shankarsan.auto.battery.dto.BatteryLevelResponseDTO;
import com.shankarsan.auto.battery.exception.BusinessException;
import com.shankarsan.auto.battery.service.BatteryLevelService;

@CrossOrigin
@RestController
public class BatteryLevelController {
	
	@Autowired
	private BatteryLevelService batteryLevelService;
	
	@PostMapping(path = "/v1/batterylevel/{level_id}/{passcode}/{level}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BatteryLevelResponseDTO saveBatteryLevel(@PathVariable("level_id") String level_id, @PathVariable("passcode") String passcode, @PathVariable("level") int level) throws BusinessException {
		return batteryLevelService.saveBatteryLevel(level_id, passcode, level);
	}
	
	@GetMapping(path = "/v1/batterylevel/{level_id}/{passcode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BatteryLevelResponseDTO getBatteryLevel(@PathVariable("level_id") String level_id, @PathVariable("passcode") String passcode) throws BusinessException {
		return batteryLevelService.getBatteryLevel(level_id, passcode);
	}
}
