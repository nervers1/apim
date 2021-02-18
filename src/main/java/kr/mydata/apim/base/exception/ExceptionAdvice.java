package kr.mydata.apim.base.exception;

import kr.mydata.apim.base.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {
    /**
     * 요청메시지 valid 중 에러발생 시 응답메시지 처리
     * @param e 요청메시지 @Valid 시 발생한 Exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> processValidationError(BindException e) {
        log.error(e.getBindingResult().getFieldError().getDefaultMessage());

        ErrorResponse er = new ErrorResponse();
        er.setRsp_code("90000");
        er.setRsp_msg(e.getFieldError().getDefaultMessage());

        return new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);
    }
}
