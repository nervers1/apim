package kr.mydata.apim.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.telecom.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TelecomServiceImpl implements TelecomService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public TelecomServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 통신업권 - 통신 계약 목록 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResTelecom001 listTelecom(ReqTelecom001 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(res, ResTelecom001.class);
    }

    /**
     * 통신업권 - 청구 정보 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResTelecom002 telecomBills(ReqTelecom002 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResTelecom002 resVo = mapper.readValue(res, ResTelecom002.class);
        List<ResTelecom002Sub> bill_list = resVo.getBill_list();

        bill_list = bill_list.stream()
                .filter(obj -> obj.getCharge_date().substring(0, 6).equals(req.getCharge_month()))
                .collect(Collectors.toList());

        resVo.setBill_list(bill_list);

        resVo.setBill_cnt(String.valueOf(bill_list.size()));

        return resVo;
    }

    /**
     * 통신업권 - 통신 거래내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResTelecom003 telecomTransactions(ReqTelecom003 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getMgmt_id());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResTelecom003 resVo = mapper.readValue(res, ResTelecom003.class);
        List<ResTelecom003Sub> trans_list = resVo.getTrans_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(trans_list);

        // 거래일자 조건 처리
        trans_list = trans_list.stream()
                .filter(obj -> obj.getTrans_month().compareTo(req.getFrom_month()) >= 0)
                .filter(obj -> obj.getTrans_month().compareTo(req.getTo_month()) <= 0)
                .collect(Collectors.toList());

        resVo.setTrans_list(trans_list);
        resVo.setTrans_cnt(String.valueOf(trans_list.size()));

        return resVo;
    }

    /**
     * 통신업권 - 통신 결제내역 조회
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @return
     * @throws Exception
     */
    @Override
    public ResTelecom004 telecomPaidTransactions(ReqTelecom004 req, String api_id, String own_org_cd) throws Exception {

        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getMgmt_id());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResTelecom004 resVo = mapper.readValue(res, ResTelecom004.class);
        List<ResTelecom004Sub> trans_list = resVo.getTrans_list();

        // Comparable 구현하여 내림차순 정렬
        Collections.sort(trans_list);

        // 거래일자 조건 처리
        trans_list = trans_list.stream()
                .filter(obj -> obj.getTrans_date().compareTo(req.getFrom_date()) >= 0)
                .filter(obj -> obj.getTrans_date().compareTo(req.getTo_date()) <= 0)
                .collect(Collectors.toList());

        // next_page 는 조회 마지막 row_num 으로 들어옴.
        int page = Util.getPage(req.getNext_page());

        // limit + 1 개 조회처리
        trans_list = trans_list.stream()
                .skip(page)
                .limit(Integer.valueOf(req.getLimit()) + 1)
                .collect(Collectors.toList());

        // limit + 1 개 만큼 조회된거면 next_page 셋팅
        resVo.setNext_page(Util.getNextPage(trans_list.size(), page, Integer.valueOf(req.getLimit())));

        // next_page 가 존재하면 limit + 1개 만큼 조회된 것이므로 cnt 는 -1 처리
        resVo.setTrans_cnt((StringUtils.hasLength(resVo.getNext_page()))
                ? String.valueOf(trans_list.size() - 1) : String.valueOf(trans_list.size()));

        // next_page 가 존재하면 limit + 1개만큼 조회된 것이므로 list 의 마지막 원소 제거
        resVo.setTrans_list((StringUtils.hasLength(resVo.getNext_page()))
                ? trans_list.subList(0, trans_list.size() - 1) : trans_list);

        return resVo;
    }
}