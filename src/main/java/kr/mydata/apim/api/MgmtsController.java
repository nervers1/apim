package kr.mydata.apim.api;

import kr.mydata.apim.service.MgmtService;
import kr.mydata.apim.vo.mgmts.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping(value = "/mgmts")
@RestController
public class MgmtsController {
    private final MgmtService service;

    public MgmtsController(MgmtService service) {
        this.service = service;
    }

    /**
     * 지원 API - 기관정보 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/orgs", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts002> orgs(@RequestHeader(value = "x-api-id") String api_id,
                                            @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                            ReqMgmts002 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResMgmts002 res = service.orgs(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * 지원 API - 서비스정보 조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/services", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts003> services(@RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                ReqMgmts003 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResMgmts003 res = service.services(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * 지원 API - 통계자료 전송
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @PostMapping(value = "/statistics", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts004> statistics(@RequestHeader(value = "x-api-id") String api_id,
                                                  @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                  @RequestBody ReqMgmts004 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResMgmts004 res = service.statistics(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * 지원 API - 데이터보유자 상태조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/status", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts006> status(@RequestHeader(value = "x-api-id") String api_id,
                                              @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                              ReqMgmts006 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResMgmts006 res = service.status(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * 지원 API - 정보주체 별 전송요구 내역조회
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/consents", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts007> consents(@RequestHeader(value = "x-api-id") String api_id,
                                                @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                ReqMgmts007 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResMgmts007 res = service.consents(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * 지원 API - 통계자료 재전송 요청
     *
     * @param api_id
     * @param own_org_cd
     * @param req
     * @return
     */
    @GetMapping(value = "/req-statistics", produces = "application/json; charset=UTF-8")
    public ResponseEntity<ResMgmts008> reqStatistics(@RequestHeader(value = "x-api-id") String api_id,
                                                     @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                                     ReqMgmts008 req) {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        try {
            ResMgmts008 res = service.reqStatistics(req, api_id, own_org_cd);
            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
