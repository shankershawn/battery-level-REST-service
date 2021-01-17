package com.shankarsan.auto.battery.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.shankarsan.auto.battery.dto.BaseResponseDTO;
import com.shankarsan.auto.battery.enums.ErrorMessageEnum;
import com.shankarsan.auto.battery.exception.BusinessException;

@Configuration
@Aspect
public class BatteryLevelServiceInterceptor {
	
	private static final Logger _logger = LoggerFactory.getLogger(BatteryLevelServiceInterceptor.class);
	
	@Around(value = "execution(* com.shankarsan.auto.battery.service.*.*(..))")
	public BaseResponseDTO intercept(ProceedingJoinPoint proceedingJoinPoint) {
		BaseResponseDTO baseResponseDTO = null;
		try {
			_logger.info("Entering {} method of {}", proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getThis().getClass().getName());
			baseResponseDTO = (BaseResponseDTO)((MethodSignature)proceedingJoinPoint.getSignature()).getReturnType().newInstance();
			baseResponseDTO = (BaseResponseDTO)proceedingJoinPoint.proceed();
			if(null == baseResponseDTO) {
				baseResponseDTO = (BaseResponseDTO)((MethodSignature)proceedingJoinPoint.getSignature()).getReturnType().newInstance();
				baseResponseDTO.setErrorCode(ErrorMessageEnum.SERVICE_DOWN.getErrorCode());
				baseResponseDTO.setErrorMessage(ErrorMessageEnum.SERVICE_DOWN.getErrorMessage());
			}else {
				baseResponseDTO.setErrorMessage(ErrorMessageEnum.SUCCESS.getErrorMessage());
			}
			_logger.info("Exiting {} method of {}", proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getThis().getClass().getName());
		}catch(BusinessException b) {
			_logger.error("Error encountered - {}.", b.getErrorMessage());
			baseResponseDTO.setErrorCode(b.getErrorCode());
			baseResponseDTO.setErrorMessage(b.getErrorMessage());
		}catch(Throwable t) {
			_logger.error("Error encountered.", t.getMessage());
			baseResponseDTO.setErrorCode(ErrorMessageEnum.SERVICE_DOWN.getErrorCode());
			baseResponseDTO.setErrorCode(ErrorMessageEnum.SERVICE_DOWN.getErrorMessage());
		}
		return baseResponseDTO;
	}
}
