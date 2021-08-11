package com.shankarsan.auto.battery.service;

import com.shankarsan.auto.battery.dto.BatteryResponseDTO;
import com.shankarsan.auto.battery.exception.BusinessException;

public interface BatteryLevelService {
	
	BatteryResponseDTO getBatteryLevel(String levelId, String passcode) throws BusinessException;
	BatteryResponseDTO saveBatteryLevel(String levelId, String passcode, int status, int level) throws BusinessException;

}
