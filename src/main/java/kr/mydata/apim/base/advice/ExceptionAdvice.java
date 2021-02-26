package kr.mydata.apim.base.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import kr.mydata.apim.base.exception.UnsupportedIndustryException;
import kr.mydata.apim.base.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {
    /**
     * 요청메시지 valid 중 에러발생 시 응답메시지 처리
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> processValidationError(BindException e) {
        e.printStackTrace();

        ErrorResponse er = new ErrorResponse();
        er.setRsp_code("40001");
        er.setRsp_msg(e.getFieldError().getDefaultMessage());

        return new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);
    }

    /**
     * Database 조회 중 발생한 Empty Result Exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResponse> processDataAccessError(EmptyResultDataAccessException e) {
        e.printStackTrace();

        ErrorResponse er = new ErrorResponse();
        er.setRsp_code("40402");
        er.setRsp_msg("요청한 자산에 대한 정보는 존재하지 않습니다.");

        return new ResponseEntity<ErrorResponse>(er, HttpStatus.NOT_FOUND);
    }

    /**
     * 조회결과 Json 을 객체로 mapping 중 발생한 Exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler({JsonProcessingException.class, UnrecognizedPropertyException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> processJsonParsingError(Exception e) {
        e.printStackTrace();

        ErrorResponse er = new ErrorResponse();
        er.setRsp_code("50001");
        er.setRsp_msg("시스템 장애가 발생하였습니다. 관리자에게 문의해주세요.");

        return new ResponseEntity<ErrorResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 공통API 중 제공되지 않는 업권의 공통API 호출 시 발생한 Exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnsupportedIndustryException.class)
    public ResponseEntity<ErrorResponse> processUnsupportedIndustryError(UnsupportedIndustryException e) {
        e.printStackTrace();

        ErrorResponse er = new ErrorResponse();
        er.setRsp_code("40301");
        er.setRsp_msg("올바르지 않은 API를 호출하였습니다.");

        return new ResponseEntity<ErrorResponse>(er, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> processHttpRequestMethodNotSupportedError(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();

        ErrorResponse er = new ErrorResponse();
        er.setRsp_code("40301");
        er.setRsp_msg("지원하지않는 HttpRequestMethod로 API를 호출하였습니다.");

        return new ResponseEntity<ErrorResponse>(er, HttpStatus.FORBIDDEN);
    }
}
