package kr.mydata.apim.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.mydata.apim.api.util.Util;
import kr.mydata.apim.vo.irp.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class IRPServiceImpl implements IRPService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final JdbcTemplate jdbcTemplate;

    public IRPServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 개인형 IRP - 계좌 목록 조회(은행, 금투, 보험 공통)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @return
     */
    @Override
    public ResIRP001 listAccount(ReqIRP001 req, String api_id, String own_org_cd, String industry) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResIRP001.class);
    }

    /**
     * 개인형 IRP - 계좌 기본정보 조회(은행, 금투, 보험 공통)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @return
     */
    @Override
    public ResIRP002 irpBasic(ReqIRP002 req, String api_id, String own_org_cd, String industry) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResIRP002.class);
    }

    /**
     * 개인형 IRP - 계좌 추가정보 조회(은행, 금투, 보험 공통)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @return
     */
    @Override
    public ResIRP003 irpDetail(ReqIRP003 req, String api_id, String own_org_cd, String industry) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return mapper.readValue(res, ResIRP003.class);
    }

    /**
     * 개인형 IRP - 계좌 거래내역 조회(은행, 금투, 보험 공통)
     *
     * @param req
     * @param api_id
     * @param own_org_cd
     * @param industry
     * @return
     */
    @Override
    public ResIRP004 irpTransactions(ReqIRP004 req, String api_id, String own_org_cd, String industry) throws Exception {
        String sql = "SELECT res_data FROM tb_test_data WHERE api_id = ? and own_org_cd = ? and org_cd = ? and ast_id = ?";
        String res = jdbcTemplate.queryForObject(sql, String.class, Integer.valueOf(api_id), own_org_cd, req.getOrg_code(), req.getAccount_num());
        // to JSON
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        ResIRP004 resVo = mapper.readValue(res, ResIRP004.class);
        List<ResIRP004Sub> trans_list = resVo.getTrans_list();


        // Comparable 구현하여 내림차순 정렬
        Collections.sort(trans_list);

        // 거래일자 조건 처리
        trans_list = trans_list.stream()
                               .filter(obj -> obj.getTrans_dtime().substring(0, 8).compareTo(req.getFrom_date()) >= 0)
                               .filter(obj -> obj.getTrans_dtime().substring(0, 8).compareTo(req.getTo_date()) <= 0)
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
