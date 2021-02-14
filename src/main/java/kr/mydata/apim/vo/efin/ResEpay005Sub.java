package kr.mydata.apim.vo.efin;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 결제내역 조회 - 상세
 */
@Data
public class ResEpay005Sub {
  private String trans_type;
  private String fob_name;
  private String trans_num;
  private String trans_dtime;
  private BigDecimal trans_amt;
  private String trans_org_code;
  private String trans_id;
  private int total_install_cnt;
  private String merchant_name;
  private String trans_title;
  private String pay_method;
}
