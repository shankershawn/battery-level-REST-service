package com.shankarsan.auto.battery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shankarsan.auto.battery.dto.BatteryResponseDTO;
import com.shankarsan.auto.battery.exception.BusinessException;
import com.shankarsan.auto.battery.service.BatteryLevelService;

@CrossOrigin
@RestController
public class BatteryLevelController {
	
	@Autowired
	private BatteryLevelService batteryLevelService;
	
	@PostMapping(path = "/v1/batterylevel/{level_id}/{passcode}/{status}/{level}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BatteryResponseDTO saveBatteryLevel(@PathVariable("level_id") String levelId, @PathVariable("passcode") String passcode, @PathVariable("status") int status, @PathVariable("level") int level) throws BusinessException {
		return batteryLevelService.saveBatteryLevel(levelId, passcode, status, level);
	}
	
	@GetMapping(path = "/v1/batterylevel/{level_id}/{passcode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BatteryResponseDTO getBatteryLevel(@PathVariable("level_id") String levelId, @PathVariable("passcode") String passcode) throws BusinessException {
		return batteryLevelService.getBatteryLevel(levelId, passcode);
	}
}
