package kr.mydata.apim.vo.irp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResIRP001Sub {
    private String seqno;
    private String prod_name;
    private String account_num;
    private String is_consent;
    private String account_status;
}
