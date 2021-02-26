package kr.mydata.apim.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.mydata.apim.service.CommonService;
import kr.mydata.apim.vo.common.ReqCmn001;
import kr.mydata.apim.vo.common.ReqCmn002;
import kr.mydata.apim.vo.common.ResCmn001;
import kr.mydata.apim.vo.common.ResCmn002;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Log4j2
public class CommonController {

    private final CommonService common;

    public CommonController(CommonService common) {
        this.common = common;
    }

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 공통 API - API 목록 조회 (공통)
     *
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @param req
     * @return
     * @thorws Exception
     */
    @GetMapping(value = "/{industry}/apis", produces = "application/json; charset=UTF-8")
    public ResponseEntity listAccount(@RequestHeader(value = "x-api-id") String api_id,
                                      @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                      @PathVariable String industry,
                                      @Valid ReqCmn001 req) throws Exception {
        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCmn001 resCmn001 = common.listAPI(req, api_id, own_org_cd, industry);
        return new ResponseEntity<>(resCmn001, HttpStatus.OK);
    }

    /**
     * 공통 API - 전송요구내역 조회 (공통)
     *
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @param req
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/{industry}/consents", produces = "application/json; charset=UTF-8")
    public ResponseEntity listConsents(@RequestHeader(value = "x-api-id") String api_id,
                                       @RequestHeader(value = "x-own-org-cd") String own_org_cd,
                                       @PathVariable String industry,
                                       @Valid ReqCmn002 req) throws Exception {

        log.info("api_id : {}", api_id);
        log.info("own_org_cd : {}", own_org_cd);
        log.info("req : {}", req);

        ResCmn002 resCmn002 = common.listConsents(req, api_id, own_org_cd, industry);
        return new ResponseEntity<>(resCmn002, HttpStatus.OK);

    }


}

