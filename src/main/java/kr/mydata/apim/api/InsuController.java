package kr.mydata.apim.api;

import kr.mydata.apim.service.InsuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping(value = "/v1/insu")
public class InsuController {

  private final InsuService service;

  public InsuController(InsuService service) {
    this.service = service;
  }


}
