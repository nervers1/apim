package kr.mydata.apim.api;

import kr.mydata.apim.service.IRPService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class IRPController {

  public IRPController(IRPService service) {
    this.service = service;
  }

  private final IRPService service;




}
