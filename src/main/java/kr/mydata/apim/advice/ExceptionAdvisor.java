package kr.mydata.apim.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.mydata.apim.vo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionAdvisor {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorResponse> processValidationError(BindException e) {
		log.error(e.getBindingResult().getFieldError().getDefaultMessage());
		ErrorResponse er = new ErrorResponse();
		er.setRsp_code("90000");
		er.setRsp_msg(e.getFieldError().getDefaultMessage());
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);
	}

}
