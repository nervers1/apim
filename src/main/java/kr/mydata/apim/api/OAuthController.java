package kr.mydata.apim.api;

import kr.mydata.apim.service.AuthService;
import kr.mydata.apim.vo.auth.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping(value = "/oauth/2.0")
@RestController
public class OAuthController {

  private final AuthService service;

  public OAuthController(AuthService service) {
    this.service = service;
  }

  @GetMapping(value = "/authorize", produces = "application/json; charset=UTF-8")
  public ResponseEntity authorize(@RequestHeader(value = "x-api-id") String api_id,
                                  @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                  ReqAuth001 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResAuth001 res = service.authorize(req, api_id, own_org_cd);
      return new ResponseEntity<>(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 접근토큰 발급 요청
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @PostMapping(value = "/token", produces = "application/json; charset=UTF-8")
  public ResponseEntity token(@RequestHeader(value = "x-api-id") String api_id,
                              @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                              ReqAuth002 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResAuth002 res = service.token(req, api_id, own_org_cd);
      return new ResponseEntity<>(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 접근토큰 갱신
   * @param api_id
   * @param own_org_cd
   * @param req
   * @return
   */
  @GetMapping(value = "/token", produces = "application/json; charset=UTF-8")
  public ResponseEntity token(@RequestHeader(value = "x-api-id") String api_id,
                              @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                              ReqAuth003 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      ResAuth003 res = service.token(req, api_id, own_org_cd);
      return new ResponseEntity<>(res, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /**
   * 접근토큰 폐기
   * @param api_id
   * @param own_org_cd
   * @param req
   */
  @GetMapping(value = "/revoke", produces = "application/json; charset=UTF-8")
  public void revoke(@RequestHeader(value = "x-api-id") String api_id,
                     @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                     ReqAuth004 req) {

    log.info("api_id : {}", api_id);
    log.info("own_org_cd : {}", own_org_cd);
    log.info("req : {}", req);

    try {
      service.revoke(req, api_id, own_org_cd);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
