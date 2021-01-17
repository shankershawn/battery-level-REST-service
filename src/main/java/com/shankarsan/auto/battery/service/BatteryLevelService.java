package com.shankarsan.auto.battery.service;

import com.shankarsan.auto.battery.dto.BatteryLevelResponseDTO;
import com.shankarsan.auto.battery.exception.BusinessException;

public interface BatteryLevelService {
	
	BatteryLevelResponseDTO getBatteryLevel(String level_id, String passcode) throws BusinessException;
	BatteryLevelResponseDTO saveBatteryLevel(String level_id, String passcode, int level) throws BusinessException;

}
