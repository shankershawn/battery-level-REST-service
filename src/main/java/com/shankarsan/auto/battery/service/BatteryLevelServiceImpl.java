package com.shankarsan.auto.battery.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.shankarsan.auto.battery.dao.BatteryLevelRepository;
import com.shankarsan.auto.battery.dto.BatteryLevelResponseDTO;
import com.shankarsan.auto.battery.enums.ErrorMessageEnum;
import com.shankarsan.auto.battery.exception.BusinessException;

@Service
public class BatteryLevelServiceImpl implements BatteryLevelService {
	
	@Autowired
	private BatteryLevelRepository batteryLevelRepository;

	@Override
	public BatteryLevelResponseDTO getBatteryLevel(String level_id, String passcode) throws BusinessException {
		// TODO Auto-generated method stub
		BatteryLevelResponseDTO batteryLevelResponseDTO = null;
		FindIterable<Document> batteryIterable = null;
		try {
			batteryIterable = batteryLevelRepository.getBatteryLevel(level_id, passcode);
			if(null == batteryIterable || null == batteryIterable.first()) {
				throw new BusinessException(ErrorMessageEnum.NO_DATA_RECEIVED.getErrorCode(), ErrorMessageEnum.NO_DATA_RECEIVED.getErrorMessage());
			}
			batteryLevelResponseDTO = new BatteryLevelResponseDTO();
			batteryLevelResponseDTO.setLevel(batteryIterable.first().getInteger("level"));
		}catch(BusinessException b) {
			throw b;
		}catch(Throwable t) {
			throw new BusinessException(t);
		}
		return batteryLevelResponseDTO;
	}

	@Override
	public BatteryLevelResponseDTO saveBatteryLevel(String level_id, String passcode, int level) throws BusinessException {
		Document document = null;
		BatteryLevelResponseDTO batteryLevelResponseDTO = null;
		if(level >=0 && level <= 100) {
			document = batteryLevelRepository.saveBatteryLevel(level_id, passcode, level);
			if(null == document || !document.containsKey("level_id")) {
				throw new BusinessException(ErrorMessageEnum.NO_DATA_RECEIVED.getErrorCode(), ErrorMessageEnum.NO_DATA_RECEIVED.getErrorMessage());
			}
			batteryLevelResponseDTO = new BatteryLevelResponseDTO();
		}else {
			throw new BusinessException(ErrorMessageEnum.INVALID_INPUT.getErrorCode(), ErrorMessageEnum.INVALID_INPUT.getErrorMessage());
		}
		return batteryLevelResponseDTO;
	}

	
}
