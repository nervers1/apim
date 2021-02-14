package kr.mydata.apim.service;

import java.time.LocalDateTime;

public interface APIService {

  int insertJSONBObject() throws Exception;

   LocalDateTime getDate() throws Exception;
}
