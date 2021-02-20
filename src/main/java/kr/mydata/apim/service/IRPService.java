package kr.mydata.apim.service;

import kr.mydata.apim.vo.irp.*;

public interface IRPService {
    /**
     * 개인형 IRP - 계좌 목록 조회(은행, 금투, 보험 공통)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @return
     */
    ResIRP001 listAccount(ReqIRP001 req, String api_id, String own_org_cd, String industry) throws Exception;

    /**
     * 개인형 IRP - 계좌 기본정보 조회(은행, 금투, 보험 공통)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @return
     */
    ResIRP002 irpBasic(ReqIRP002 req, String api_id, String own_org_cd, String industry) throws Exception;

    /**
     * 개인형 IRP - 계좌 추가정보 조회(은행, 금투, 보험 공통)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @return
     */
    ResIRP003 irpDetail(ReqIRP003 req, String api_id, String own_org_cd, String industry) throws Exception;

    /**
     * 개인형 IRP - 계좌 거래내역 조회(은행, 금투, 보험 공통)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @return
     */
    ResIRP004 irpTransactions(ReqIRP004 req, String api_id, String own_org_cd, String industry) throws Exception;
}
