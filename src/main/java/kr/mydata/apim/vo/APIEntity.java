package kr.mydata.apim.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * "seq_no" INTEGER NOT NULL DEFAULT 'nextval(''seq_tb_test_data''::regclass)',
 * "busr" INTEGER NOT NULL,
 * "api_id" INTEGER NOT NULL,
 * "org_cd" VARCHAR(10) NOT NULL,
 * "own_org_cd" VARCHAR(10) NULL DEFAULT NULL,
 * "ast_id" VARCHAR(50) NULL DEFAULT NULL,
 * "scope" VARCHAR(50) NULL DEFAULT NULL,
 * "res_data" JSONB NULL DEFAULT NULL,
 * "api_ver" INTEGER NULL DEFAULT NULL,
 * "data_nm" VARCHAR(50) NULL DEFAULT NULL,
 * "reg_dt" TIMESTAMP NULL DEFAULT NULL,
 * "mod_dt" TIMESTAMP NULL DEFAULT NULL,
 * "mod_id" VARCHAR(50) NULL DEFAULT NULL,
 * "reg_id" VARCHAR(50) NULL DEFAULT NULL,
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIEntity implements Serializable {
    private int seq_no;
    private int busr;
    private int api_id;
    private String org_cd;
    private String own_org_cd;
    private String ast_id;
    private String scope;
    private String res_data;
    private int api_ver;
    private String data_nm;
    private String reg_dt;
    private String mod_dt;
    private String mod_id;
    private String reg_id;
}
