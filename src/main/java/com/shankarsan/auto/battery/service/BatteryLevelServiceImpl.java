package com.shankarsan.auto.battery.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.shankarsan.auto.battery.dao.BatteryLevelRepository;
import com.shankarsan.auto.battery.dto.BatteryResponseDTO;
import com.shankarsan.auto.battery.enums.ErrorMessageEnum;
import com.shankarsan.auto.battery.exception.BusinessException;

@Service
public class BatteryLevelServiceImpl implements BatteryLevelService {
	
	@Autowired
	private BatteryLevelRepository batteryLevelRepository;

	@Override
	public BatteryResponseDTO getBatteryLevel(String levelId, String passcode) throws BusinessException {
		// TODO Auto-generated method stub
		BatteryResponseDTO batteryResponseDTO = null;
		FindIterable<Document> batteryIterable = null;
		Document batteryDocument = null;
		try {
			batteryIterable = batteryLevelRepository.getBatteryLevel(levelId, passcode);
			if(null == batteryIterable || null == batteryIterable.first()) {
				throw new BusinessException(ErrorMessageEnum.NO_DATA_RECEIVED.getErrorCode(), ErrorMessageEnum.NO_DATA_RECEIVED.getErrorMessage());
			}
			batteryResponseDTO = new BatteryResponseDTO();
			batteryDocument = batteryIterable.first();
			batteryResponseDTO.setLevel(batteryDocument.getInteger("level", 0));
			batteryResponseDTO.setStatus(batteryDocument.getInteger("status", 1));
		}catch(BusinessException b) {
			throw b;
		}catch(Throwable t) {
			throw new BusinessException(t);
		}
		return batteryResponseDTO;
	}

	@Override
	public BatteryResponseDTO saveBatteryLevel(String levelId, String passcode, int status, int level) throws BusinessException {
		Document document = null;
		BatteryResponseDTO batteryResponseDTO = null;
		if(level >=0 && level <= 100) {
			document = batteryLevelRepository.saveBatteryLevel(levelId, passcode, status, level);
			if(null == document || !document.containsKey("level_id")) {
				throw new BusinessException(ErrorMessageEnum.NO_DATA_RECEIVED.getErrorCode(), ErrorMessageEnum.NO_DATA_RECEIVED.getErrorMessage());
			}
			batteryResponseDTO = new BatteryResponseDTO();
		}else {
			throw new BusinessException(ErrorMessageEnum.INVALID_INPUT.getErrorCode(), ErrorMessageEnum.INVALID_INPUT.getErrorMessage());
		}
		return batteryResponseDTO;
	}

	
}
